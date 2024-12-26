package handler;

public abstract class PaymentHandler {
    protected PaymentHandler nextHandler;

    public void setNextHandler(PaymentHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract boolean handle(String paymentData);
}
