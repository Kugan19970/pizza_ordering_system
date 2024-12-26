package view;

import state.OrderContext;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class OrderTrackingPage extends JFrame {
    private JLabel lblStatus;
    private JProgressBar progressBar;
    private int progress = 0;
    private OrderContext orderContext;
    private Timer timer;

    public OrderTrackingPage() {
        // Initialize OrderContext
        orderContext = new OrderContext();

        // Set up frame
        setTitle("Real-Time Order Tracking");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background

        // Header label
        JLabel lblHeader = new JLabel("Track Your Order");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        lblHeader.setBounds(50, 20, 400, 40);
        lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblHeader);

        // Status label
        lblStatus = new JLabel("Order Status: " + orderContext.getStatus(), SwingConstants.CENTER);
        lblStatus.setFont(new Font("Arial", Font.PLAIN, 16));
        lblStatus.setBounds(50, 80, 400, 30);
        add(lblStatus);

        // Progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(progress);
        progressBar.setBounds(50, 120, 400, 30);
        add(progressBar);

        // Back button
        JButton btnBack = new JButton("Back to Main Menu");
        btnBack.setBounds(180, 200, 140, 40);
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(new Color(204, 0, 0)); // Red
        btnBack.setForeground(Color.WHITE);
        add(btnBack);

        btnBack.addActionListener(e -> {
            stopTimer(); // Stop the timer when going back
            new MainPage().setVisible(true);
            dispose();
        });

        // Start automatic status updates
        startAutoUpdates();
    }

    private void startAutoUpdates() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> updateState());
            }
        }, 0, 5000); // Update every 3 seconds
    }

    private void updateState() {
        if (!"Delivered".equals(orderContext.getStatus())) {
            orderContext.nextState(); // Move to the next state
            progress += 33.34; // Increase progress by 25% for each state
            progressBar.setValue(progress);
            lblStatus.setText("Order Status: " + orderContext.getStatus());

            // Optional: Show a notification for each status update
            JOptionPane.showMessageDialog(this, "Order Status Updated: " + orderContext.getStatus(),
                    "Order Update", JOptionPane.INFORMATION_MESSAGE);
        } else {
            stopTimer(); // Stop updates when the order is delivered
        }
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OrderTrackingPage().setVisible(true));
    }
}
