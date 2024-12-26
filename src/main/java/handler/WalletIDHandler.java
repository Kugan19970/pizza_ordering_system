package handler;

public class WalletIDHandler extends PaymentHandler {
    @Override
    public boolean handle(String walletID) {
        if (walletID == null || walletID.isEmpty()) {
            System.out.println("Wallet ID is invalid.");
            return false;
        }
        if (nextHandler != null) {
            return nextHandler.handle(walletID);
        }
        return true;
    }
}
