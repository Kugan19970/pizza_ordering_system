package view;

import command.Command;
import command.SubmitFeedbackCommand;
import user.UserManager;

import javax.swing.*;
import java.awt.*;

public class FeedbackPage extends JFrame {
    private JTextArea txtFeedback;
    private JComboBox<Integer> cmbRating;
    private JButton btnSubmit, btnBack;

    public FeedbackPage() {
        // Set up frame
        setTitle("Feedback and Ratings");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background

        // Header label
        JLabel lblHeader = new JLabel("We Value Your Feedback!");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        lblHeader.setForeground(new Color(0, 102, 204)); // Blue color
        lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeader.setBounds(50, 20, 500, 40);
        add(lblHeader);

        // Feedback label and text area
        JLabel lblFeedback = new JLabel("Your Feedback:");
        lblFeedback.setFont(new Font("Arial", Font.PLAIN, 14));
        lblFeedback.setBounds(50, 80, 150, 30);
        add(lblFeedback);

        txtFeedback = new JTextArea();
        txtFeedback.setFont(new Font("Arial", Font.PLAIN, 14));
        txtFeedback.setBounds(50, 120, 500, 100);
        txtFeedback.setLineWrap(true);
        txtFeedback.setWrapStyleWord(true);
        txtFeedback.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(txtFeedback);

        // Rating label and dropdown
        JLabel lblRating = new JLabel("Rate Your Order (1-5):");
        lblRating.setFont(new Font("Arial", Font.PLAIN, 14));
        lblRating.setBounds(50, 240, 150, 30);
        add(lblRating);

        cmbRating = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        cmbRating.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbRating.setBounds(200, 240, 100, 30);
        add(cmbRating);

        // Submit button
        btnSubmit = new JButton("Submit Feedback");
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 14));
        btnSubmit.setBackground(new Color(0, 153, 76)); // Green
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBounds(50, 300, 200, 40);
        add(btnSubmit);

        // Back button
        btnBack = new JButton("Back to Main Menu");
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(new Color(204, 0, 0)); // Red
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(300, 300, 200, 40);
        add(btnBack);

        // Submit feedback action
        btnSubmit.addActionListener(e -> {
            String feedback = txtFeedback.getText();
            Integer rating = (Integer) cmbRating.getSelectedItem();

            if (feedback.isEmpty()) {
                JOptionPane.showMessageDialog(FeedbackPage.this, "Please provide feedback!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Command submitFeedbackCommand = new SubmitFeedbackCommand(feedback, rating);
                submitFeedbackCommand.execute();
                JOptionPane.showMessageDialog(FeedbackPage.this, "Thank you for your feedback!");
                new MainPage().setVisible(true);
                dispose();
            }
        });

        // Back button action
        btnBack.addActionListener(e -> {
            new MainPage().setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FeedbackPage().setVisible(true));
    }
}
