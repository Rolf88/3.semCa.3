package services.currency;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class CurrencyFetcher {

    private static final String Url = "http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en";

    public CurrencyFetchResult fetch() throws IOException, SAXException {

        CurrencyContentHandler contentHandler = new CurrencyContentHandler();

        XMLReader xr = XMLReaderFactory.createXMLReader();
        xr.setContentHandler(contentHandler);
        URL url = new URL(Url);

        try (InputStream stream = url.openStream()) {
            xr.parse(new InputSource(stream));
        }

        return new CurrencyFetchResult(contentHandler.getDate(), contentHandler.getCurrencies());
    }

    public class CurrencyFetchResult {

        private final Date date;
        private final HashMap<String, Float> currencies;

        public CurrencyFetchResult(Date date, HashMap<String, Float> currencies) {
            this.date = date;
            this.currencies = currencies;
        }

        public Date getDate() {
            return date;
        }

        public HashMap<String, Float> getCurrencies() {
            return currencies;
        }
    }

    class CurrencyContentHandler extends DefaultHandler {

        private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        private final HashMap<String, Float> currencies = new HashMap<>();
        private Date date;

        public HashMap<String, Float> getCurrencies() {
            return currencies;
        }

        public Date getDate() {
            return date;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if ("currency".equalsIgnoreCase(localName)) {
                try {
                    currencies.put(attributes.getValue("code"), Float.parseFloat(attributes.getValue("rate")));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(CurrencyFetcher.class.getName()).log(Level.INFO, "{0} could not parse: ''{1}'' to float", new Object[]{attributes.getValue("code"), attributes.getValue("rate")});
                }
            }

            if ("dailyrates".equalsIgnoreCase(localName)) {
                try {
                    date = dateFormat.parse(attributes.getValue("id"));
                } catch (ParseException ex) {
                    Logger.getLogger(CurrencyFetcher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
