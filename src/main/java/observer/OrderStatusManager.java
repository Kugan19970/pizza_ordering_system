package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class OrderStatusManager {
    private static OrderStatusManager instance;
    private final List<OrderStatusObserver> observers = new ArrayList<>();
    private final String[] statuses = {
        "Order Received",
        "Preparing",
        "Baking",
        "Packing",
        "Ready for Delivery",
        "Delivered"
    };
    private int statusIndex = 0; // Track the current status
    private Timer timer;

    private OrderStatusManager() {}

    public static synchronized OrderStatusManager getInstance() {
        if (instance == null) {
            instance = new OrderStatusManager();
        }
        return instance;
    }

    public void addObserver(OrderStatusObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OrderStatusObserver observer) {
        observers.remove(observer);
    }

    public void startOrderStatusUpdates() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        statusIndex = 0; // Reset status index

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (statusIndex < statuses.length) {
                    notifyObservers(statuses[statusIndex]);
                    statusIndex++;
                } else {
                    timer.cancel();
                }
            }
        }, 0, 5000); // Update every 5 seconds
    }

    private void notifyObservers(String status) {
        for (OrderStatusObserver observer : observers) {
            observer.onStatusChanged(status);
        }
    }
}
