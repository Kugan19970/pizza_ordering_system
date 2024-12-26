package state;

public class InPreparationState implements OrderState {
    @Override
    public void next(OrderContext context) {
        context.setState(new OutForDeliveryState());
    }

    @Override
    public void prev(OrderContext context) {
        context.setState(new OrderPlacedState());
    }

    @Override
    public String getStatus() {
        return "In Preparation";
    }
}
