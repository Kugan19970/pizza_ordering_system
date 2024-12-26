package handler;

public class CardNumberHandler extends PaymentHandler {
    @Override
    public boolean handle(String cardNumber) {
        if (cardNumber == null || cardNumber.isEmpty()) {
            System.out.println("Card Number is invalid.");
            return false;
        }
        if (nextHandler != null) {
            return nextHandler.handle(cardNumber);
        }
        return true;
    }
}
