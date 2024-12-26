package state;

public class OrderContext {
    private OrderState state;

    public OrderContext() {
        state = new OrderPlacedState(); // Initial state
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void nextState() {
        state.next(this);
    }

    public void prevState() {
        state.prev(this);
    }

    public String getStatus() {
        return state.getStatus();
    }
}
