package handler;

import java.util.Arrays;
import java.util.List;

public class CrustHandler extends OrderCustomizationHandler {
    private final List<String> validCrusts = Arrays.asList("Thin Crust", "Thick Crust", "Cheese Stuffed Crust");

    @Override
    public boolean handleRequest(OrderCustomizationRequest request) {
        if (!validCrusts.contains(request.getCrust())) {
            System.out.println("Invalid crust: " + request.getCrust());
            return false;
        }
        if (nextHandler != null) {
            return nextHandler.handleRequest(request);
        }
        return true;
    }
}
