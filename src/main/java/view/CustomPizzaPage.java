package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import model.Pizza;
import model.PizzaBuilder;
import user.User;
import user.UserManager;

public class CustomPizzaPage extends JFrame {

    private JTextField txtPizzaName;
    private JComboBox<String> cmbCrust;
    private JComboBox<String> cmbSauce;
    private JList<String> lstToppings;
    private JRadioButton rbtnDelivery;
    private JRadioButton rbtnPickup;
    private JButton btnSubmit, btnBack;
    private JLabel lblTotal;

    private final HashMap<String, Double> crustPrices;
    private final HashMap<String, Double> saucePrices;
    private final HashMap<String, Double> toppingPrices;

    public CustomPizzaPage() {
        // Initialize price maps
        crustPrices = new HashMap<>();
        crustPrices.put("Thin Crust", 2.0);
        crustPrices.put("Thick Crust", 2.5);
        crustPrices.put("Cheese Stuffed Crust", 3.0);

        saucePrices = new HashMap<>();
        saucePrices.put("Tomato", 1.0);
        saucePrices.put("Barbecue", 1.5);
        saucePrices.put("White Sauce", 1.5);

        toppingPrices = new HashMap<>();
        toppingPrices.put("Pepperoni", 1.5);
        toppingPrices.put("Mushrooms", 1.0);
        toppingPrices.put("Onions", 0.75);
        toppingPrices.put("Cheese", 1.25);
        toppingPrices.put("Olives", 1.0);

        // Set up frame
        setTitle("Customize Your Pizza");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background

        // Header label
        JLabel lblHeader = new JLabel("Customize Your Pizza");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 22));
        lblHeader.setForeground(new Color(0, 102, 204)); // Blue color
        lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeader.setBounds(50, 20, 500, 40);
        add(lblHeader);

        // Pizza name
        JLabel lblPizzaName = new JLabel("Pizza Name:");
        lblPizzaName.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPizzaName.setBounds(50, 80, 100, 30);
        add(lblPizzaName);

        txtPizzaName = new JTextField();
        txtPizzaName.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPizzaName.setBounds(150, 80, 200, 30);
        txtPizzaName.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(txtPizzaName);

        // Crust selection
        JLabel lblCrust = new JLabel("Select Crust:");
        lblCrust.setFont(new Font("Arial", Font.PLAIN, 14));
        lblCrust.setBounds(50, 130, 100, 30);
        add(lblCrust);

        cmbCrust = new JComboBox<>(crustPrices.keySet().toArray(new String[0]));
        cmbCrust.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbCrust.setBounds(150, 130, 200, 30);
        add(cmbCrust);

        // Sauce selection
        JLabel lblSauce = new JLabel("Select Sauce:");
        lblSauce.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSauce.setBounds(50, 180, 100, 30);
        add(lblSauce);

        cmbSauce = new JComboBox<>(saucePrices.keySet().toArray(new String[0]));
        cmbSauce.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbSauce.setBounds(150, 180, 200, 30);
        add(cmbSauce);

        // Toppings selection
        JLabel lblToppings = new JLabel("Select Toppings:");
        lblToppings.setFont(new Font("Arial", Font.PLAIN, 14));
        lblToppings.setBounds(50, 230, 150, 30);
        add(lblToppings);

        lstToppings = new JList<>(toppingPrices.keySet().toArray(new String[0]));
        lstToppings.setFont(new Font("Arial", Font.PLAIN, 14));
        lstToppings.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane toppingsScrollPane = new JScrollPane(lstToppings);
        toppingsScrollPane.setBounds(150, 230, 200, 100);
        add(toppingsScrollPane);

        // Delivery method
        JLabel lblDelivery = new JLabel("Delivery Method:");
        lblDelivery.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDelivery.setBounds(50, 350, 150, 30);
        add(lblDelivery);

        rbtnDelivery = new JRadioButton("Delivery");
        rbtnDelivery.setFont(new Font("Arial", Font.PLAIN, 14));
        rbtnDelivery.setBounds(150, 350, 100, 30);
        rbtnPickup = new JRadioButton("Pickup");
        rbtnPickup.setFont(new Font("Arial", Font.PLAIN, 14));
        rbtnPickup.setBounds(250, 350, 100, 30);

        ButtonGroup deliveryGroup = new ButtonGroup();
        deliveryGroup.add(rbtnDelivery);
        deliveryGroup.add(rbtnPickup);

        add(rbtnDelivery);
        add(rbtnPickup);

        // Total cost display
        lblTotal = new JLabel("Total: $0.00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotal.setBounds(50, 400, 200, 30);
        add(lblTotal);

        // Submit button
        btnSubmit = new JButton("Submit");
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 14));
        btnSubmit.setBackground(new Color(0, 153, 76)); // Green
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBounds(100, 450, 120, 40);
        add(btnSubmit);

        // Back button
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(new Color(204, 0, 0)); // Red
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(250, 450, 120, 40);
        add(btnBack);

        // Save as Favorite button
        JButton btnSaveFavorite = new JButton("Save as Favorite");
        btnSaveFavorite.setFont(new Font("Arial", Font.BOLD, 14));
        btnSaveFavorite.setBackground(new Color(0, 102, 204)); // Blue
        btnSaveFavorite.setForeground(Color.WHITE);
        btnSaveFavorite.setBounds(100, 500, 270, 40);
        add(btnSaveFavorite);

        // Add listeners for dynamic updates
        cmbCrust.addActionListener(e -> updateTotal());
        cmbSauce.addActionListener(e -> updateTotal());
        lstToppings.addListSelectionListener(e -> updateTotal());
        rbtnDelivery.addActionListener(e -> updateTotal());
        rbtnPickup.addActionListener(e -> updateTotal());

        btnSubmit.addActionListener(e -> showOrderSummary());

        btnBack.addActionListener(e -> {
            new MainPage().setVisible(true);
            dispose();
        });

        btnSaveFavorite.addActionListener(e -> saveFavorite());
    }

    private void updateTotal() {
        String selectedCrust = (String) cmbCrust.getSelectedItem();
        String selectedSauce = (String) cmbSauce.getSelectedItem();
        List<String> selectedToppings = lstToppings.getSelectedValuesList();
        double total = calculateTotal(selectedCrust, selectedSauce, selectedToppings);

        lblTotal.setText(String.format("Total: $%.2f", total));
    }

    private void saveFavorite() {
    UserManager userManager = UserManager.getInstance();
    User loggedInUser = userManager.getLoggedInUser();

    if (loggedInUser == null) {
        JOptionPane.showMessageDialog(this, "You must log in to save favorites!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String pizzaName = txtPizzaName.getText().isEmpty() ? "Custom Pizza" : txtPizzaName.getText();
    String crust = (String) cmbCrust.getSelectedItem();
    String sauce = (String) cmbSauce.getSelectedItem();
    List<String> toppings = lstToppings.getSelectedValuesList();
    double totalCost = calculateTotal(crust, sauce, toppings);

    Pizza pizza = new PizzaBuilder()
        .setName(pizzaName)
        .setCrust(crust)
        .setSauce(sauce)
        .setToppings(toppings)
        .setTotalCost(totalCost)
        .build();

    loggedInUser.addFavoritePizza(pizza);
    JOptionPane.showMessageDialog(this, "Pizza saved as favorite for user: " + loggedInUser.getUsername());
}


    private double calculateTotal(String crust, String sauce, List<String> toppings) {
        double total = crustPrices.getOrDefault(crust, 0.0);
        total += saucePrices.getOrDefault(sauce, 0.0);
        for (String topping : toppings) {
            total += toppingPrices.getOrDefault(topping, 0.0);
        }
        if (rbtnDelivery.isSelected()) {
            total += 3.0;
        }
        return total;
    }

    private void showOrderSummary() {
        UserManager userManager = UserManager.getInstance();
        User loggedInUser = userManager.getLoggedInUser();

        if (loggedInUser == null) {
            JOptionPane.showMessageDialog(this, "No user is logged in!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String pizzaName = txtPizzaName.getText().isEmpty() ? "Custom Pizza" : txtPizzaName.getText();
        String crust = (String) cmbCrust.getSelectedItem();
        String sauce = (String) cmbSauce.getSelectedItem();
        List<String> toppings = lstToppings.getSelectedValuesList();
        String deliveryMethod = rbtnDelivery.isSelected() ? "Delivery" : "Pickup";
        double total = calculateTotal(crust, sauce, toppings);

        String summary = String.format(
                "Order Summary:\n"
                        + "User: %s\n"
                        + "Pizza Name: %s\n"
                        + "Crust: %s\n"
                        + "Sauce: %s\n"
                        + "Toppings: %s\n"
                        + "Delivery Method: %s\n"
                        + "Total Cost: $%.2f",
                loggedInUser.getUsername(), pizzaName, crust, sauce,
                String.join(", ", toppings), deliveryMethod, total);

        int result = JOptionPane.showConfirmDialog(this, summary, "Order Summary", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            new PaymentPage().setVisible(true);
            dispose();
        }
    }
}
