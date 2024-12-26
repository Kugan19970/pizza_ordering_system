package view;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import observer.OrderStatusManager;

public class PaymentSuccessPage extends JFrame {

    public PaymentSuccessPage(double paymentAmount) {
        // Set up frame
        setTitle("Payment Successful");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background

        // Header label
        JLabel lblHeader = new JLabel("Payment Successful!");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 22));
        lblHeader.setForeground(new Color(0, 153, 76)); // Green color
        lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeader.setBounds(50, 30, 400, 40);
        add(lblHeader);

        // Loyalty points
        int loyaltyPoints = calculateLoyaltyPoints(paymentAmount);
        JLabel lblLoyaltyPoints = new JLabel("You earned " + loyaltyPoints + " loyalty points!");
        lblLoyaltyPoints.setFont(new Font("Arial", Font.PLAIN, 16));
        lblLoyaltyPoints.setForeground(new Color(0, 102, 204)); // Blue color
        lblLoyaltyPoints.setHorizontalAlignment(SwingConstants.CENTER);
        lblLoyaltyPoints.setBounds(50, 100, 400, 30);
        add(lblLoyaltyPoints);

        // Back to main page button
        JButton btnBackToMain = new JButton("Track Your Order");
        btnBackToMain.setFont(new Font("Arial", Font.BOLD, 14));
        btnBackToMain.setBackground(new Color(0, 102, 204)); // Blue
        btnBackToMain.setForeground(Color.WHITE);
        btnBackToMain.setBounds(175, 180, 150, 40);
        add(btnBackToMain);

        btnBackToMain.addActionListener(e -> {
            // Notify the OrderStatusManager to start tracking
            OrderStatusManager.getInstance().startOrderStatusUpdates();

            // Transition to order tracking
            new OrderTrackingPage().setVisible(true);
            dispose();
        });

    }

    private int calculateLoyaltyPoints(double paymentAmount) {
        Random random = new Random();
        // Generate random points based on payment amount (e.g., 1 to 10% of the amount)
        return (int) (paymentAmount * (1 + random.nextDouble() * 0.1));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentSuccessPage(50.0).setVisible(true));
    }
}
