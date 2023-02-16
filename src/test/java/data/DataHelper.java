package data;

import com.github.javafaker.Faker;
import dto.Dto;

import java.util.Locale;
import java.util.Random;

public class DataHelper {

    private DataHelper() {
    }

    private static Faker fakerEN = new Faker(new Locale("EN"));
    private static Faker fakerRU = new Faker(new Locale("RU"));

    public static Dto.User getValidUserData() {
        String name = fakerRU.name().fullName();
        String phone = getPhoneNumber("+7", 10);
        return new Dto.User(name, phone);
    }

    public static String getPhoneNumber(String startSymbols, int length) {
        return startSymbols + fakerEN.number().digits(length);
    }

    public static String getRussianFirstName() {
        return fakerRU.name().firstName();
    }

    public static String getRussianLastName() {
        return fakerRU.name().lastName();
    }

    public static String getRussianFullName() {
        return fakerRU.name().fullName();
    }

    public static String getEnglishName() {
        return fakerEN.name().fullName();
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

    public static String getSuccessNotificationText() {
        return "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
    }

    public static String getErrorSubText() {
        return "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
    }

    public static String getEmptyInputSubText() {
        return "Поле обязательно для заполнения";
    }

    public static String getErrorPhoneSubText() {
        return "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
    }


}
