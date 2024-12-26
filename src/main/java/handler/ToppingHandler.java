package handler;

import java.util.Arrays;
import java.util.List;

public class ToppingHandler extends OrderCustomizationHandler {
    private final List<String> validToppings = Arrays.asList("Pepperoni", "Mushrooms", "Onions", "Cheese", "Olives");

    @Override
    public boolean handleRequest(OrderCustomizationRequest request) {
        for (String topping : request.getToppings()) {
            if (!validToppings.contains(topping)) {
                System.out.println("Invalid topping: " + topping);
                return false;
            }
        }
        if (nextHandler != null) {
            return nextHandler.handleRequest(request);
        }
        return true;
    }
}
