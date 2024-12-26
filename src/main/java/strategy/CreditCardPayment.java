/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

 
import javax.swing.*;
import view.PaymentSuccessPage;

public class CreditCardPayment implements PaymentStrategy {
    private final String cardNumber;
    private final String expiryDate;
    private final String cvv;

    public CreditCardPayment(String cardNumber, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

      
   @Override
public void pay(double amount) {
    if (cardNumber.isEmpty() || expiryDate.isEmpty() || cvv.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill all card details!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    JOptionPane.showMessageDialog(null, "Payment of $" + amount + " successful via Credit Card!", "Success", JOptionPane.INFORMATION_MESSAGE);
    
    // Navigate to the PaymentSuccessPage or OrderTrackingPage
    new PaymentSuccessPage(amount).setVisible(true);
}

}

