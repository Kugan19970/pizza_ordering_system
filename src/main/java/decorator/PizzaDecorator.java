package decorator;

import model.Pizza;

public abstract class PizzaDecorator extends Pizza {
    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        super(pizza.getName(), pizza.getType(), pizza.getPrice());
        this.pizza = pizza;
    }

    @Override
    public double getPrice() {
        return pizza.getPrice();
    }
}
