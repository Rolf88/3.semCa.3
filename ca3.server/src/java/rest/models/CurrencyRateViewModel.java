package rest.models;

public class CurrencyRateViewModel {

    private final String currencyCode;
    private final Float rate;

    public CurrencyRateViewModel(String currencyCode, Float rate) {
        this.currencyCode = currencyCode;
        this.rate = rate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Float getRate() {
        return rate;
    }
}
