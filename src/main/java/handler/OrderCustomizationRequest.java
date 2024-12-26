package handler;

import java.util.List;

public class OrderCustomizationRequest {
    private String crust;
    private String sauce;
    private List<String> toppings;

    public OrderCustomizationRequest(String crust, String sauce, List<String> toppings) {
        this.crust = crust;
        this.sauce = sauce;
        this.toppings = toppings;
    }

    public String getCrust() {
        return crust;
    }

    public String getSauce() {
        return sauce;
    }

    public List<String> getToppings() {
        return toppings;
    }
}
