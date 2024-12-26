package factory;

import model.Pizza;

public class PizzaFactory {
    public static Pizza createPizza(String type) {
        switch (type) {
            case "Margherita":
                return new Pizza("Margherita", "Tomato, Cheese", 5.99);
            case "Pepperoni":
                return new Pizza("Pepperoni", "Pepperoni, Cheese", 6.99);
            case "Veggie":
                return new Pizza("Veggie", "Tomato, Cheese, Vegetables", 6.49);
            default:
                return null;
        }
    }
}
