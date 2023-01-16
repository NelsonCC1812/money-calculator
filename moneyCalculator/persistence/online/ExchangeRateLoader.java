package moneyCalculator.persistence.online;

import moneyCalculator.model.Currency;
import moneyCalculator.model.ExchangeRate;

public interface ExchangeRateLoader {
    public ExchangeRate exchangeRateLoader(Currency from, Currency to);
}
