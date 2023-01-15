package moneyCalculator.persistence.local;

import java.util.List;

import moneyCalculator.model.Currency;;

public interface CurrencyLoader {
    public List<Currency> loadCurrencies();
}
