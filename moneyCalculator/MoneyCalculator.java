package moneyCalculator;

import java.util.List;

import moneyCalculator.model.Currency;
import moneyCalculator.model.ExchangeRate;
import moneyCalculator.persistence.*;
import moneyCalculator.persistence.local.CurrencyLoaderFromFile;
import moneyCalculator.persistence.online.ExchangeRateLoaderFromWebService;;

public class MoneyCalculator {

    public static void main(String[] args) {
        CurrencyLoaderFromFile currencyLoaderFromFile = new CurrencyLoaderFromFile("currencies");
        List<Currency> list = currencyLoaderFromFile.loadCurrencies();

        for (Currency currency : list) {
            System.out.println(currency.getCode() + " " + currency.getName() + " " + currency.getSymbol());
        }

        ExchangeRateLoaderFromWebService ExchangeRateLoaderFromWebService = new ExchangeRateLoaderFromWebService();

        for (Currency currencyFrom : list) {
            for (Currency currencyTo : list) {
                if (!currencyFrom.getCode().equals((currencyTo.getCode()))) {
                    ExchangeRate exchangeRate = ExchangeRateLoaderFromWebService.exchangeRateLoader(currencyFrom,
                            currencyTo);
                    System.out
                            .println((exchangeRate.getFrom().getCode() + " - " + exchangeRate.getTo().getCode() + " : "
                                    + exchangeRate.getRate()));
                }
            }
        }
    }
}
