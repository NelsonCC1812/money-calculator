package moneyCalculator.control;

import moneyCalculator.model.Currency;
import moneyCalculator.model.ExchangeRate;
import moneyCalculator.model.Money;
import moneyCalculator.persistence.online.ExchangeRateLoaderFromWebService;
import moneyCalculator.view.SwingDialog;
import moneyCalculator.view.dialog.Dialog;
import moneyCalculator.view.display.Display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private final Dialog dialog;
    private final ExchangeRateLoaderFromWebService exchangeRateLoaderFromWebService;

    public Controller(Dialog swingDialog,
            ExchangeRateLoaderFromWebService exchangeRateLoaderFromWebService) {
        this.dialog = swingDialog;
        this.exchangeRateLoaderFromWebService = exchangeRateLoaderFromWebService;
        this.dialog.registerController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Money money = this.dialog.getMoney();
        Currency currencyFrom = money.getCurrency();
        Currency currencyTo = this.dialog.getCurrencyTo();
        ExchangeRate exchangeRate = this.exchangeRateLoaderFromWebService.exchangeRateLoader(currencyFrom, currencyTo);

        ((Display) this.dialog).refreshMoney(new Money(currencyTo, exchangeRate.getRate() * money.getAmount()));
    }
}
