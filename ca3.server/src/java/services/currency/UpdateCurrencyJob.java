package services.currency;

import entity.CurrencyRate;
import facades.CurrencyFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.SAXException;

public class UpdateCurrencyJob implements Runnable {

    private final CurrencyFacade currencyFacade;
    private final CurrencyFetcher currencyFetcher;

    public UpdateCurrencyJob(CurrencyFacade currencyFacade, CurrencyFetcher currencyFetcher) {
        this.currencyFacade = currencyFacade;
        this.currencyFetcher = currencyFetcher;
    }

    @Override
    public void run() {
        try {
            CurrencyFetcher.CurrencyFetchResult result = currencyFetcher.fetch();
            List<CurrencyRate> currencies = new ArrayList<>();

            for (Map.Entry<String, Float> entrySet : result.getCurrencies().entrySet()) {
                CurrencyRate currencyRate = new CurrencyRate();
                currencyRate.setCurrencyCode(entrySet.getKey());
                currencyRate.setRate(entrySet.getValue());
                currencyRate.setTimeCreated(result.getDate());

                currencies.add(currencyRate);
            }

            currencyFacade.create(currencies);
        } catch (IOException | SAXException ex) {
            Logger.getLogger(UpdateCurrencyJob.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
