package strategy;

import javax.swing.*;
import view.PaymentSuccessPage;

public class DigitalWalletPayment implements PaymentStrategy {

    private final String walletID;

    public DigitalWalletPayment(String walletID) {
        this.walletID = walletID;
    }

    @Override
    public void pay(double amount) {
        if (walletID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please provide your Wallet ID!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, "Payment of $" + amount + " successful via Digital Wallet!", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Navigate to the PaymentSuccessPage or OrderTrackingPage
        new PaymentSuccessPage(amount).setVisible(true);
    }

}
