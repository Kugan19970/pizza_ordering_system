package view;

import user.User;
import user.UserManager;
import model.Pizza;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FavoritesPage extends JFrame {
    public FavoritesPage() {
        // Set up frame
        setTitle("Favorites");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        UserManager userManager = UserManager.getInstance();
        User loggedInUser = userManager.getLoggedInUser();

        if (loggedInUser == null) {
            JOptionPane.showMessageDialog(this, "You must log in to view favorites!", "Error", JOptionPane.ERROR_MESSAGE);
            new LoginPage().setVisible(true);
            dispose();
            return;
        }

        // Display favorites
        JLabel lblTitle = new JLabel("Favorites for " + loggedInUser.getUsername() + ":");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setBounds(20, 20, 300, 30);
        add(lblTitle);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Pizza pizza : loggedInUser.getFavoritePizzas()) {
            listModel.addElement(pizza.toString());
        }

        JList<String> lstFavorites = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(lstFavorites);
        scrollPane.setBounds(20, 60, 440, 200);
        add(scrollPane);

        // Back button
        JButton btnBack = new JButton("Back to Main Menu");
        btnBack.setBounds(180, 300, 150, 30);
        add(btnBack);

        btnBack.addActionListener(e -> {
            new MainPage().setVisible(true);
            dispose();
        });
    }
}
