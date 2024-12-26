package decorator;

import model.Pizza;

public class ExtraCheeseDecorator extends PizzaDecorator {

    public ExtraCheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 1.5; // Adding cost for extra cheese
    }
}
