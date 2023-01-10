package data;

import com.github.javafaker.Faker;
import dto.Dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {

//    private DataHelper(){}

    private static Faker faker = new Faker(new Locale("EN"));

    public static Dto.User getValidApprovedUserData() {
        String cardNumber = "4444 4444 4444 4441";
        String cardExpireMonth = "05";
        String cardExpireYear = "25";
        String cardHolderName = "Ivan Ivanov";
        int cvvCode = 222;
        return new Dto.User(cardNumber, cardExpireMonth, cardExpireYear, cardHolderName, cvvCode);
    }

    public static Dto.User getValidDeclinedUserData() {
        String cardNumber = "4444 4444 4444 4442";
        String cardExpireMonth = "05";
        String cardExpireYear = "25";
        String cardHolderName = "Ivan Ivanov";
        int cvvCode = 222;
        return new Dto.User(cardNumber, cardExpireMonth, cardExpireYear, cardHolderName, cvvCode);
    }

    public static Dto.User getValidUnregisteredUserData() {
        String cardNumber = faker.finance().creditCard();
        String cardExpireMonth = LocalDate.now().plusDays(new Random().nextInt(365)).format(DateTimeFormatter.ofPattern("MM"));
        String cardExpireYear = String.valueOf(new Random().nextInt(10) + 23);
        String cardHolderName = faker.name().fullName();
        int cvvCode = new Random().nextInt(899) + 100;
        return new Dto.User(cardNumber, cardExpireMonth, cardExpireYear, cardHolderName, cvvCode);
    }

    public static String getRandomChar() {
        String[] chars = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", ".", ",", "/", ";", ":", "<", ">", "+", "|", "=", "_"};
        return chars[new Random().nextInt(chars.length - 1)];
    }

    public static int getRandomInt(int bound, int shift) {
        return new Random().nextInt(bound) + shift;
    }

    public static String getRandomName() {
        return faker.name().fullName();
    }


}
