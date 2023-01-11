package dto;

import lombok.Value;

public class Dto {

    private Dto() {
    }


    @Value
    public static class User {
        String cardNumber;
        String cardExpireMonth;
        String cardExpireYear;
        String cardHolderName;
        String cvvCode;
    }
}
