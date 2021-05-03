package chapter7.autodebit;

public class AutoDebitReq {
    private String userId;
    private String cardNumber;

    public AutoDebitReq(String userId, String cardNumber) {
        this.userId = userId;
        this.cardNumber = cardNumber;
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public String getuserId() {
        return userId;
    }
}
