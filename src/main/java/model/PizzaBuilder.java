package model;
import java.util.List;
public class PizzaBuilder {
    private String name;
    private String crust;
    private String sauce;
    private List<String> toppings;
    private double totalCost;

    public PizzaBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PizzaBuilder setCrust(String crust) {
        this.crust = crust;
        return this;
    }

    public PizzaBuilder setSauce(String sauce) {
        this.sauce = sauce;
        return this;
    }

    public PizzaBuilder setToppings(List<String> toppings) {
        this.toppings = toppings;
        return this;
    }

    public PizzaBuilder setTotalCost(double totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    public Pizza build() {
        return new Pizza(name, crust + ", " + sauce + ", " + String.join(", ", toppings), totalCost);
    }
}
