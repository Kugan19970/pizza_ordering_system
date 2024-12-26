package decorator;

import model.Pizza;

public class MushroomsDecorator extends PizzaDecorator {

    public MushroomsDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 1.25; // Adding cost for mushrooms
    }
}
