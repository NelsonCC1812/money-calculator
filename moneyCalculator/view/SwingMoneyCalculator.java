package moneyCalculator.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class SwingMoneyCalculator extends JFrame {

    public SwingMoneyCalculator(JPanel pane, String title) {
        super(title);

        this.getContentPane().add(pane);
        addWindowListener(new WindowCloseManager());

        pack();
        setVisible(true);
    }

    private static class WindowCloseManager extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
}
