package rest.models;

import java.util.Date;
import java.util.List;

public class DailyCurrencyRateViewModel {

    private final Date date;
    private final List<CurrencyRateViewModel> rates;

    public DailyCurrencyRateViewModel(Date date, List<CurrencyRateViewModel> rates) {
        this.date = date;
        this.rates = rates;
    }

    public Date getDate() {
        return date;
    }

    public List<CurrencyRateViewModel> getRates() {
        return rates;
    }
}
