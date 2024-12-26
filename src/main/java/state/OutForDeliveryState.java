package state;

public class OutForDeliveryState implements OrderState {
    @Override
    public void next(OrderContext context) {
        context.setState(new DeliveredState());
    }

    @Override
    public void prev(OrderContext context) {
        context.setState(new InPreparationState());
    }

    @Override
    public String getStatus() {
        return "Out for Delivery";
    }
}
 