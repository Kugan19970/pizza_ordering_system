package handler;

public class CVVHandler extends PaymentHandler {
    @Override
    public boolean handle(String cvv) {
        if (cvv == null || cvv.isEmpty() || cvv.length() != 3) {
            System.out.println("CVV is invalid.");
            return false;
        }
        if (nextHandler != null) {
            return nextHandler.handle(cvv);
        }
        return true;
    }
}
