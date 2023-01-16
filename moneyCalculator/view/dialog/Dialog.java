package moneyCalculator.view.dialog;

import moneyCalculator.control.Controller;
import moneyCalculator.model.Currency;
import moneyCalculator.model.Money;

public interface Dialog {
    Money getMoney();

    Currency getCurrencyTo();

    void registerController(Controller controller);

}
