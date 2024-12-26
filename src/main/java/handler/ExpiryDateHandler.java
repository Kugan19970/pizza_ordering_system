package handler;

public class ExpiryDateHandler extends PaymentHandler {
    @Override
    public boolean handle(String expiryDate) {
        if (expiryDate == null || expiryDate.isEmpty() ) {
            System.out.println("Expiry Date is invalid.");
            return false;
        }
        if (nextHandler != null) {
            return nextHandler.handle(expiryDate);
        }
        return true;
    }
} 



 