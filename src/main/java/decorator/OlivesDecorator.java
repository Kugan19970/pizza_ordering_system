package decorator;

import model.Pizza;

public class OlivesDecorator extends PizzaDecorator {

    public OlivesDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 1.0; // Adding cost for olives
    }
}
