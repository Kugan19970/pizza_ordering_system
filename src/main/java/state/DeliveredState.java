package state;

public class DeliveredState implements OrderState {
    @Override
    public void next(OrderContext context) {
        System.out.println("Order is already delivered.");
    }

    @Override
    public void prev(OrderContext context) {
        context.setState(new OutForDeliveryState());
    }

    @Override
    public String getStatus() {
        return "Delivered";
    }
}
