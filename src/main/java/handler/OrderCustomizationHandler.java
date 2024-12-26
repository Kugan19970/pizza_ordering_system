package handler;

public abstract class OrderCustomizationHandler {
    protected OrderCustomizationHandler nextHandler;

    public void setNextHandler(OrderCustomizationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract boolean handleRequest(OrderCustomizationRequest request);
}