package view;

import javax.swing.*;
import java.awt.*;

public class SeasonalSpecialsPage extends JFrame {

    public SeasonalSpecialsPage() {
        // Set up frame
        setTitle("Seasonal Specials & Promotions");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background

        // Title Label
        JLabel lblTitle = new JLabel("Seasonal Specials & Promotions!");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(new Color(0, 102, 204)); // Blue color
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(50, 20, 600, 50);
        add(lblTitle);

        // Promotions area
        JTextArea promotionsArea = new JTextArea();
        promotionsArea.setEditable(false);
        promotionsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        promotionsArea.setText(getPromotionsText());
        promotionsArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        promotionsArea.setBackground(new Color(255, 255, 255)); // White background
        JScrollPane scrollPane = new JScrollPane(promotionsArea);
        scrollPane.setBounds(50, 100, 600, 250);
        add(scrollPane);

        // Back button
        JButton btnBack = new JButton("Back to Main Menu");
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(new Color(204, 0, 0)); // Red color
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(250, 380, 200, 50);
        add(btnBack);

        btnBack.addActionListener(e -> {
            new MainPage().setVisible(true);
            dispose();
        });
    }

    private String getPromotionsText() {
        // Example promotions
        return """
                🍕 **Winter Special**: Get 20% off on all Cheese-Stuffed Crust pizzas! 
                   Valid until: December 31, 2024
                
                🍕 **Holiday Delight**: Free Topping of your choice with every Veggie pizza order!
                   Valid until: January 15, 2025
                
                🍕 **Family Fest**: Buy 2 Large Pepperoni pizzas and get a Medium Margherita FREE!
                   Valid until: February 1, 2025
               
                🍕 **New Year Blast**: Flat $5 off on orders above $30!
                   Valid until: January 10, 2025
                """;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SeasonalSpecialsPage().setVisible(true));
    }
}
