package moneyCalculator.model;

public class ExchangeRate {
    private final Currency from;
    private final Currency to;
    private final double rate;

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    public double getRate() {
        return rate;
    }

    public ExchangeRate(Currency from2, Currency to2, double rate) {
        this.from = from2;
        this.to = to2;
        this.rate = rate;
    }
}
