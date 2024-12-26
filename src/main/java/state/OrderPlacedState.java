package state;

public class OrderPlacedState implements OrderState {
    @Override
    public void next(OrderContext context) {
        context.setState(new InPreparationState());
    }

    @Override
    public void prev(OrderContext context) {
        System.out.println("Order is already in the initial state.");
    }

    @Override
    public String getStatus() {
        return "Order Placed";
    }
}
