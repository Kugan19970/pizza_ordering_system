package view;

import user.User;
import user.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField txtUsername;
    private JButton btnLogin;

    public LoginPage() {
        // Set up frame
        setTitle("User Login");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background

        // Header label
        JLabel lblHeader = new JLabel("Login to Pizza Ordering System");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 18));
        lblHeader.setForeground(new Color(0, 102, 204)); // Blue
        lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeader.setBounds(50, 20, 300, 30);
        add(lblHeader);

        // Username label
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUsername.setBounds(50, 80, 100, 30);
        add(lblUsername);

        // Username text field
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsername.setBounds(150, 80, 180, 30);
        add(txtUsername);

        // Login button
        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setBackground(new Color(0, 153, 76)); // Green
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBounds(150, 130, 180, 40);
        add(btnLogin);

        // Login button action
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginPage.this, "Username cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    User user = new User(username);
                    UserManager.getInstance().login(user);
                    JOptionPane.showMessageDialog(LoginPage.this, "Welcome, " + username + "!");
                    new MainPage().setVisible(true);
                    dispose();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}
