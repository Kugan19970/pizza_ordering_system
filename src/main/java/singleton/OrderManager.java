package singleton;

import java.util.ArrayList;
import java.util.List;
import model.Order;

public class OrderManager {
    private static OrderManager instance;
    private List<Order> orders;

    private OrderManager() {
        orders = new ArrayList<>();
    }

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Order getOrderById(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    public void updateOrderStatus(String orderId, String status) {
        Order order = getOrderById(orderId);
        if (order != null) {
            order.setStatus(status);
        }
    }
}
