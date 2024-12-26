package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.Pizza;
import user.User;
import user.UserManager;

public class MainPage extends JFrame {

    private JButton btnCustomPizza;
    private JButton btnPreMadePizza;

    public MainPage() {
        // Set up frame
        setTitle("Pizza Ordering System");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Set background color
        getContentPane().setBackground(new Color(240, 240, 240));

        // Add header
        JLabel lblHeader = new JLabel("Welcome to Pizza Ordering System");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 22));
        lblHeader.setForeground(new Color(128, 0, 0)); // Dark red
        lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeader.setBounds(50, 20, 400, 40);
        add(lblHeader);

        // Button style
        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        Color buttonBgColor = new Color(200, 200, 255);
        Color buttonFgColor = Color.BLACK;

        // Add buttons
        btnCustomPizza = new JButton("Custom Pizza");
        btnCustomPizza.setFont(buttonFont);
        btnCustomPizza.setBackground(buttonBgColor);
        btnCustomPizza.setForeground(buttonFgColor);
        btnCustomPizza.setBounds(150, 80, 200, 40);
        add(btnCustomPizza);

        btnPreMadePizza = new JButton("Pre-Made Pizza");
        btnPreMadePizza.setFont(buttonFont);
        btnPreMadePizza.setBackground(buttonBgColor);
        btnPreMadePizza.setForeground(buttonFgColor);
        btnPreMadePizza.setBounds(150, 140, 200, 40);
        add(btnPreMadePizza);

        JButton btnFavorites = new JButton("View Favorites");
        btnFavorites.setFont(buttonFont);
        btnFavorites.setBackground(buttonBgColor);
        btnFavorites.setForeground(buttonFgColor);
        btnFavorites.setBounds(150, 200, 200, 40);
        add(btnFavorites);

        JButton btnSpecials = new JButton("Seasonal Specials");
        btnSpecials.setFont(buttonFont);
        btnSpecials.setBackground(buttonBgColor);
        btnSpecials.setForeground(buttonFgColor);
        btnSpecials.setBounds(150, 260, 200, 40);
        add(btnSpecials);

        JButton btnFeedback = new JButton("Feedback & Ratings");
        btnFeedback.setFont(buttonFont);
        btnFeedback.setBackground(buttonBgColor);
        btnFeedback.setForeground(buttonFgColor);
        btnFeedback.setBounds(150, 320, 200, 40);
        add(btnFeedback);

        // Button listeners
        btnCustomPizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomPizzaPage().setVisible(true);
                dispose();
            }
        });

        btnPreMadePizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PreMadePizzaPage().setVisible(true);
                dispose();
            }
        });

        btnFavorites.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserManager userManager = UserManager.getInstance();
                User user = userManager.getLoggedInUser();

                if (user == null) {
                    JOptionPane.showMessageDialog(MainPage.this, "You must log in to view favorites!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                List<Pizza> favorites = user.getFavoritePizzas();
                if (favorites.isEmpty()) {
                    JOptionPane.showMessageDialog(MainPage.this, "No favorite pizzas saved!", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder favoriteList = new StringBuilder("Favorite Pizzas:\n");
                    for (int i = 0; i < favorites.size(); i++) {
                        Pizza pizza = favorites.get(i);
                        favoriteList.append(i + 1).append(". ").append(pizza.toString()).append("\n");
                    }

                    JOptionPane.showMessageDialog(MainPage.this, favoriteList.toString(), "Favorites", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnSpecials.addActionListener(e -> {
            new SeasonalSpecialsPage().setVisible(true);
            dispose();
        });

        btnFeedback.addActionListener(e -> {
            new FeedbackPage().setVisible(true);
            dispose();
        });
        btnFavorites.addActionListener(e -> {
            new FavoritesPage().setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainPage().setVisible(true));
    }
}
