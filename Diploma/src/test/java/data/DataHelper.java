package data;

import com.github.javafaker.Faker;
import dto.Dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {

    private DataHelper() {
    }

    private static Faker fakerEN = new Faker(new Locale("EN"));
    private static Faker fakerRU = new Faker(new Locale("RU"));

    public static Dto.User getValidApprovedUserData() {
        String cardNumber = "4444 4444 4444 4441";
        String cardExpireMonth = LocalDate.now().plusDays(new Random().nextInt(365)).format(DateTimeFormatter.ofPattern("MM"));
        String cardExpireYear = String.valueOf(getRandomInt(5, 23));
        String cardHolderName = getRandomEnglishName();
        String cvvCode = getRandomCode(3);
        return new Dto.User(cardNumber, cardExpireMonth, cardExpireYear, cardHolderName, cvvCode);
    }

    public static Dto.User getValidDeclinedUserData() {
        String cardNumber = "4444 4444 4444 4442";
        String cardExpireMonth = LocalDate.now().plusDays(new Random().nextInt(365)).format(DateTimeFormatter.ofPattern("MM"));
        String cardExpireYear = String.valueOf(getRandomInt(5, 23));
        String cardHolderName = getRandomEnglishName();
        String cvvCode = getRandomCode(3);
        return new Dto.User(cardNumber, cardExpireMonth, cardExpireYear, cardHolderName, cvvCode);
    }

    public static Dto.User getValidUnregisteredUserData() {
        String cardNumber = fakerEN.finance().creditCard()+getRandomCode(2);
        String cardExpireMonth = LocalDate.now().plusDays(new Random().nextInt(365)).format(DateTimeFormatter.ofPattern("MM"));
        String cardExpireYear = String.valueOf(new Random().nextInt(5) + 23);
        String cardHolderName = fakerEN.name().fullName();
        String cvvCode = getRandomCode(3);
        return new Dto.User(cardNumber, cardExpireMonth, cardExpireYear, cardHolderName, cvvCode);
    }

    public static Dto.Request getValidApprovedUserDataRequest() {
        Dto.User user = getValidApprovedUserData();
        String number = user.getCardNumber();
        String year = user.getCardExpireYear();
        String month = user.getCardExpireMonth();
        String cvc = user.getCvvCode();
        String holder = user.getCardHolderName();
        return new Dto.Request(number, year, month, cvc, holder);
    }

    public static Dto.Request getValidDeclinedUserDataRequest() {
        Dto.User user = getValidDeclinedUserData();
        String number = user.getCardNumber();
        String year = user.getCardExpireYear();
        String month = user.getCardExpireMonth();
        String cvc = user.getCvvCode();
        String holder = user.getCardHolderName();
        return new Dto.Request(number, year, month, cvc, holder);
    }

    public static Dto.Request getValidUnregisteredUserDataRequest() {
        Dto.User user = getValidUnregisteredUserData();
        String number = user.getCardNumber();
        String year = user.getCardExpireYear();
        String month = user.getCardExpireMonth();
        String cvc = user.getCvvCode();
        String holder = user.getCardHolderName();
        return new Dto.Request(number, year, month, cvc, holder);
    }

    public static String getRandomChar() {
        String[] chars = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", ".", ",", "/", ";", ":", "<", ">", "+", "|", "=", "_"};
        return chars[new Random().nextInt(chars.length - 1)];
    }

    public static String getRandomEnglishLetter() {
        return fakerEN.letterify("?");
    }

    public static String getRandomRussianLetter() {
        String[] letters = {"А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Ъ", "Ы", "Ь", "Э", "Ю", "Я"};
        return letters[new Random().nextInt(letters.length - 1)];
    }

    public static int getRandomInt(int bound, int shift) {
        return new Random().nextInt(bound) + shift;
    }

    public static String getRandomCode(int length) {
        return fakerEN.number().digits(length);
    }

    public static String getRandomEnglishName() {
        return fakerEN.name().fullName();
    }

    public static String getRandomEnglishFirstName() {
        return fakerEN.name().firstName();
    }

    public static String getRandomRussianName() {
        return fakerRU.name().fullName();
    }

    public static String getRandomRussianFirstName() {
        return fakerRU.name().firstName();
    }

}
