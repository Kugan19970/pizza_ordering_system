package handler;

import java.util.Arrays;
import java.util.List;

public class SauceHandler extends OrderCustomizationHandler {
    private final List<String> validSauces = Arrays.asList("Tomato", "Barbecue", "White Sauce");

    @Override
    public boolean handleRequest(OrderCustomizationRequest request) {
        if (!validSauces.contains(request.getSauce())) {
            System.out.println("Invalid sauce: " + request.getSauce());
            return false;
        }
        if (nextHandler != null) {
            return nextHandler.handleRequest(request);
        }
        return true;
    }
}
