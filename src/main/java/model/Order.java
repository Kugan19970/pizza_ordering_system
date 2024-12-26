 
package model;

import java.io.Serializable;

public class Order implements Serializable {
    private String orderId;
    private String pizzaName;
    private String status;

    public Order(String orderId, String pizzaName) {
        this.orderId = orderId;
        this.pizzaName = pizzaName;
        this.status = "Order Received"; // Initial status
    }

    public String getOrderId() {
        return orderId;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void updateStatus() {
        switch (status) {
            case "Order Received":
                status = "Preparing";
                break;
            case "Preparing":
                status = "Baking";
                break;
            case "Baking":
                status = "Out for Delivery";
                break;
            case "Out for Delivery":
                status = "Delivered";
                break;
        }
    }
}
