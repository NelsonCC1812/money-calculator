package moneyCalculator.view;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.BorderLayout;

import moneyCalculator.control.Controller;
import moneyCalculator.model.Currency;
import moneyCalculator.model.Money;
import moneyCalculator.view.dialog.Dialog;
import moneyCalculator.view.display.Display;

public class SwingDialog extends JPanel implements Dialog, Display {

    private final String LBL_MONEY_BUTTON = "Calculate";

    private final String LBL_MONEY = "Money";
    private final String LBL_CURRENCY_FROM = "Currency from";
    private final String LBL_CURRENCY_TO = "Currency to";
    private final String LBL_RESULT = "Result = ";

    private final int MONEY_WIDTH = 40;
    private final int RESULT_WIDTH = 40;

    private JLabel lblMoney, lblCurrencyFrom, lblCurrencyTo, lblResult;
    private JTextField fldMoney, fldResult;

    private JComboBox<Currency> cbxCurrencyFrom, cbxCurrencyTo;
    private JButton btnCalculate;

    private List<Currency> currencies;

    public SwingDialog(List<Currency> currencies) {
        this.currencies = currencies;
        initComponents();
    }

    @Override
    public Money getMoney() {
        return new Money(this.getCurrencyFrom(), Double.parseDouble(this.fldMoney.getText()));
    }

    @Override
    public Currency getCurrencyTo() {
        return (Currency) this.cbxCurrencyTo.getSelectedItem();
    }

    private Currency getCurrencyFrom() {
        return (Currency) this.cbxCurrencyFrom.getSelectedItem();
    }

    private void initComponents() {

        this.btnCalculate = new JButton(this.LBL_MONEY_BUTTON);

        this.lblMoney = new JLabel(this.LBL_MONEY);
        this.fldMoney = new JTextField(this.MONEY_WIDTH);

        this.lblCurrencyFrom = new JLabel(this.LBL_CURRENCY_FROM);
        this.lblCurrencyTo = new JLabel(this.LBL_CURRENCY_TO);

        this.lblResult = new JLabel(this.LBL_RESULT);
        this.fldResult = new JTextField(this.RESULT_WIDTH);
        this.fldResult.setEditable(false);

        this.cbxCurrencyFrom = new JComboBox<Currency>();
        this.cbxCurrencyTo = new JComboBox<Currency>();

        for (Currency currency : this.currencies) {
            this.cbxCurrencyFrom.addItem(currency);
            this.cbxCurrencyTo.addItem(currency);
        }

        // dlgPanel

        JPanel dlgPanel = new JPanel();
        dlgPanel.setLayout(new BoxLayout(dlgPanel, BoxLayout.X_AXIS));

        JPanel lblPanel = new JPanel();
        lblPanel.setLayout(new GridLayout(0, 1));

        lblPanel.add(this.lblMoney);
        lblPanel.add(this.lblCurrencyFrom);
        lblPanel.add(this.lblCurrencyTo);
        lblPanel.add(this.lblResult);

        dlgPanel.add(lblPanel);

        // fldPanel

        JPanel fldPanel = new JPanel();
        fldPanel.setLayout(new GridLayout(0, 1));

        fldPanel.add(this.fldMoney);
        fldPanel.add(this.cbxCurrencyFrom);
        fldPanel.add(this.cbxCurrencyTo);
        fldPanel.add(this.fldResult);

        dlgPanel.add(fldPanel);

        // ctrlPanel

        JPanel ctrlPanel = new JPanel();

        ctrlPanel.add(this.btnCalculate);

        setLayout((new BorderLayout()));
        add(dlgPanel, BorderLayout.CENTER);
        add(ctrlPanel, BorderLayout.SOUTH);

        this.refreshMoney(new Money(currencies.get(0), 0.0));
    }

    public void registerController(Controller controller) {
        this.btnCalculate.addActionListener(controller);
    }

    public void refreshMoney(Money money) {
        this.fldResult.setText(String.format(" %f %s", money.getAmount(), money.getCurrency().getSymbol()));
    }
}
