package view;

import decorator.*;
import model.Pizza;
import user.User;
import user.UserManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PreMadePizzaPage extends JFrame {

    private JComboBox<String> cmbPizzaType, cmbExtraTopping;
    private JSpinner spnQuantity;
    private JLabel lblPrice, lblTotal;
    private JButton btnSubmit, btnBack, btnSaveFavorite;

    private final HashMap<String, Double> pizzaPrices;

    public PreMadePizzaPage() {
        // Initialize pizza prices
        pizzaPrices = new HashMap<>();
        pizzaPrices.put("Margherita", 5.99);
        pizzaPrices.put("Pepperoni", 6.99);
        pizzaPrices.put("Veggie", 6.49);
        pizzaPrices.put("BBQ Chicken", 7.99);
        pizzaPrices.put("Hawaiian", 7.49);
        pizzaPrices.put("Theriyaki Chiken", 19.49);

        // Set up frame
        setTitle("Pre-Made Pizza");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background

        // Header label
        JLabel lblHeader = new JLabel("Select Your Pre-Made Pizza");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        lblHeader.setForeground(new Color(0, 102, 204)); // Blue color
        lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeader.setBounds(50, 20, 500, 40);
        add(lblHeader);

        // Pizza selection
        JLabel lblPizzaType = new JLabel("Select Pizza:");
        lblPizzaType.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPizzaType.setBounds(50, 80, 120, 30);
        add(lblPizzaType);

        cmbPizzaType = new JComboBox<>(pizzaPrices.keySet().toArray(new String[0]));
        cmbPizzaType.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbPizzaType.setBounds(200, 80, 300, 30);
        add(cmbPizzaType);

        // Quantity spinner
        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setFont(new Font("Arial", Font.PLAIN, 14));
        lblQuantity.setBounds(50, 130, 120, 30);
        add(lblQuantity);

        spnQuantity = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1)); // Min 1, Max 20, Step 1
        spnQuantity.setFont(new Font("Arial", Font.PLAIN, 14));
        spnQuantity.setBounds(200, 130, 100, 30);
        add(spnQuantity);

        // Extra Topping selection
        JLabel lblExtraTopping = new JLabel("Add Extra Topping:");
        lblExtraTopping.setFont(new Font("Arial", Font.PLAIN, 14));
        lblExtraTopping.setBounds(50, 180, 150, 30);
        add(lblExtraTopping);

        cmbExtraTopping = new JComboBox<>(new String[]{"None", "Extra Cheese", "Olives", "Mushrooms"});
        cmbExtraTopping.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbExtraTopping.setBounds(200, 180, 300, 30);
        add(cmbExtraTopping);

        // Price label
        lblPrice = new JLabel("Price: $0.00");
        lblPrice.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPrice.setBounds(50, 230, 200, 30);
        add(lblPrice);

        // Total cost label
        lblTotal = new JLabel("Total: $0.00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotal.setForeground(new Color(0, 153, 76)); // Green
        lblTotal.setBounds(50, 280, 200, 30);
        add(lblTotal);

        // Submit button
        btnSubmit = new JButton("Submit");
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 14));
        btnSubmit.setBackground(new Color(0, 153, 76)); // Green
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBounds(100, 350, 120, 40);
        add(btnSubmit);

        // Back button
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(new Color(204, 0, 0)); // Red
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(250, 350, 120, 40);
        add(btnBack);

        // Save favorite button
        btnSaveFavorite = new JButton("Save as Favorite");
        btnSaveFavorite.setFont(new Font("Arial", Font.BOLD, 14));
        btnSaveFavorite.setBackground(new Color(0, 102, 204)); // Blue
        btnSaveFavorite.setForeground(Color.WHITE);
        btnSaveFavorite.setBounds(400, 350, 150, 40);
        add(btnSaveFavorite);

        // Event listeners
        cmbPizzaType.addActionListener(e -> updatePrice());
        spnQuantity.addChangeListener(e -> updateTotal());
        cmbExtraTopping.addActionListener(e -> updateTotal());

        btnSubmit.addActionListener(e -> showOrderSummary());
        btnBack.addActionListener(e -> {
            new MainPage().setVisible(true);
            dispose();
        });
        btnSaveFavorite.addActionListener(e -> saveFavorite());

        // Initialize display
        updatePrice();
        updateTotal();
    }

    private void updatePrice() {
        String selectedPizza = (String) cmbPizzaType.getSelectedItem();
        double price = pizzaPrices.getOrDefault(selectedPizza, 0.0);
        lblPrice.setText(String.format("Price: $%.2f", price));
    }

    private void updateTotal() {
        String selectedPizza = (String) cmbPizzaType.getSelectedItem();
        String extraTopping = (String) cmbExtraTopping.getSelectedItem();
        double price = pizzaPrices.getOrDefault(selectedPizza, 0.0);

        Pizza pizza = new Pizza(selectedPizza, "Pre-Made Pizza", price);
        pizza = decoratePizza(pizza, extraTopping);

        int quantity = (int) spnQuantity.getValue();
        double total = pizza.getPrice() * quantity;

        lblTotal.setText(String.format("Total: $%.2f", total));
    }

    private Pizza decoratePizza(Pizza pizza, String extraTopping) {
        switch (extraTopping) {
            case "Extra Cheese":
                return new ExtraCheeseDecorator(pizza);
            case "Olives":
                return new OlivesDecorator(pizza);
            case "Mushrooms":
                return new MushroomsDecorator(pizza);
            default:
                return pizza;
        }
    }

    private void saveFavorite() {
        UserManager userManager = UserManager.getInstance();
        User loggedInUser = userManager.getLoggedInUser();

        if (loggedInUser == null) {
            JOptionPane.showMessageDialog(this, "You must log in to save favorites!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String pizzaName = (String) cmbPizzaType.getSelectedItem();
        String extraTopping = (String) cmbExtraTopping.getSelectedItem();
        int quantity = (int) spnQuantity.getValue();
        double price = pizzaPrices.getOrDefault(pizzaName, 0.0);

        Pizza pizza = new Pizza(pizzaName, "Pre-Made Pizza", price);
        pizza = decoratePizza(pizza, extraTopping);

        double total = pizza.getPrice() * quantity;
        loggedInUser.addFavoritePizza(new Pizza(pizza.getName(), pizza.getType(), total));

        JOptionPane.showMessageDialog(this, "Pizza saved as favorite for user: " + loggedInUser.getUsername());
    }

    private void showOrderSummary() {
        String selectedPizza = (String) cmbPizzaType.getSelectedItem();
        String extraTopping = (String) cmbExtraTopping.getSelectedItem();
        int quantity = (int) spnQuantity.getValue();
        double price = pizzaPrices.getOrDefault(selectedPizza, 0.0);

        Pizza pizza = new Pizza(selectedPizza, "Pre-Made Pizza", price);
        pizza = decoratePizza(pizza, extraTopping);

        double total = pizza.getPrice() * quantity;

        String summary = String.format("Order Summary:\nPizza: %s\nExtras: %s\nQuantity: %d\nTotal: $%.2f",
                pizza.getName(), extraTopping, quantity, total);

        int result = JOptionPane.showConfirmDialog(this, summary, "Order Summary", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            new PaymentPage().setVisible(true);
            dispose();
        }
    }
}
