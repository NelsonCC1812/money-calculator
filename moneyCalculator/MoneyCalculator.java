package moneyCalculator;

import java.util.List;

import javax.swing.SwingUtilities;

import moneyCalculator.control.Controller;
import moneyCalculator.model.Currency;
import moneyCalculator.persistence.local.CurrencyLoaderFromFile;
import moneyCalculator.persistence.online.ExchangeRateLoaderFromWebService;
import moneyCalculator.view.SwingDialog;
import moneyCalculator.view.SwingMoneyCalculator;

public class MoneyCalculator {

    public static void main(String[] args) {

        CurrencyLoaderFromFile currencyLoaderFromFile = new CurrencyLoaderFromFile("currencies");
        List<Currency> currencies = currencyLoaderFromFile.loadCurrencies();

        ExchangeRateLoaderFromWebService exchangeRateLoaderFromWebService = new ExchangeRateLoaderFromWebService();

        SwingDialog dialog = new SwingDialog(currencies);

        new Controller(dialog, exchangeRateLoaderFromWebService);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SwingMoneyCalculator(dialog, "Money Calculator Display");
            }
        });
    }
}
