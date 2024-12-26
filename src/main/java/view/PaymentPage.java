package view;

import handler.*;
import strategy.CreditCardPayment;
import strategy.DigitalWalletPayment;
import strategy.PaymentContext;

import javax.swing.*;
import java.awt.*;

public class PaymentPage extends JFrame {
    private JComboBox<String> cmbPaymentMethod;
    private JTextField txtCardNumber, txtExpiryDate, txtCVV, txtWalletID;
    private JButton btnPay;
    private PaymentContext paymentContext;

    public PaymentPage() {
        paymentContext = new PaymentContext();

        // Set up frame
        setTitle("Payment");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 240, 240));

        // Header
        JLabel lblHeader = new JLabel("Payment Options");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeader.setBounds(50, 20, 400, 40);
        add(lblHeader);

        // Payment method selection
        JLabel lblPaymentMethod = new JLabel("Select Payment Method:");
        lblPaymentMethod.setBounds(50, 80, 200, 30);
        add(lblPaymentMethod);

        cmbPaymentMethod = new JComboBox<>(new String[]{"Credit Card", "Digital Wallet"});
        cmbPaymentMethod.setBounds(250, 80, 180, 30);
        add(cmbPaymentMethod);

        // Card fields
        JLabel lblCardNumber = new JLabel("Card Number:");
        lblCardNumber.setBounds(50, 130, 150, 30);
        add(lblCardNumber);

        txtCardNumber = new JTextField();
        txtCardNumber.setBounds(250, 130, 180, 30);
        add(txtCardNumber);

        JLabel lblExpiryDate = new JLabel("Expiry Date (MM/YY):");
        lblExpiryDate.setBounds(50, 180, 150, 30);
        add(lblExpiryDate);

        txtExpiryDate = new JTextField();
        txtExpiryDate.setBounds(250, 180, 180, 30);
        add(txtExpiryDate);

        JLabel lblCVV = new JLabel("CVV:");
        lblCVV.setBounds(50, 230, 150, 30);
        add(lblCVV);

        txtCVV = new JTextField();
        txtCVV.setBounds(250, 230, 100, 30);
        add(txtCVV);

        // Wallet fields
        JLabel lblWalletID = new JLabel("Wallet ID:");
        lblWalletID.setBounds(50, 180, 150, 30);
        add(lblWalletID);

        txtWalletID = new JTextField();
        txtWalletID.setBounds(250, 180, 180, 30);
        add(txtWalletID);

        lblWalletID.setVisible(false);
        txtWalletID.setVisible(false);

        cmbPaymentMethod.addActionListener(e -> {
            boolean isCard = cmbPaymentMethod.getSelectedItem().equals("Credit Card");
            lblCardNumber.setVisible(isCard);
            txtCardNumber.setVisible(isCard);
            lblExpiryDate.setVisible(isCard);
            txtExpiryDate.setVisible(isCard);
            lblCVV.setVisible(isCard);
            txtCVV.setVisible(isCard);

            lblWalletID.setVisible(!isCard);
            txtWalletID.setVisible(!isCard);
        });

        // Pay button
        btnPay = new JButton("Pay");
        btnPay.setBounds(200, 300, 100, 40);
        add(btnPay);

        btnPay.addActionListener(e -> {
            String method = (String) cmbPaymentMethod.getSelectedItem();
            double amount = calculateTotalAmount();

            if (method.equals("Credit Card")) {
                PaymentHandler cardHandler = new CardNumberHandler();
                PaymentHandler expiryHandler = new ExpiryDateHandler();
                PaymentHandler cvvHandler = new CVVHandler();

                cardHandler.setNextHandler(expiryHandler);
                expiryHandler.setNextHandler(cvvHandler);

                if (cardHandler.handle(txtCardNumber.getText()) &&
                        expiryHandler.handle(txtExpiryDate.getText()) &&
                        cvvHandler.handle(txtCVV.getText())) {
                    paymentContext.setPaymentStrategy(new CreditCardPayment(txtCardNumber.getText(), txtExpiryDate.getText(), txtCVV.getText()));
                    paymentContext.executePayment(amount);
                }
            } else {
                PaymentHandler walletHandler = new WalletIDHandler();
                if (walletHandler.handle(txtWalletID.getText())) {
                    paymentContext.setPaymentStrategy(new DigitalWalletPayment(txtWalletID.getText()));
                    paymentContext.executePayment(amount);
                }
            }
        });
    }

    private double calculateTotalAmount() {
        return 25.0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentPage().setVisible(true));
    }
}
