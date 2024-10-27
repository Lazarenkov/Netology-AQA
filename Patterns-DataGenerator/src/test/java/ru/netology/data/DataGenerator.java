package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


public class DataGenerator {

    private DataGenerator(){}

    public static String generateDate(int shift){
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity(){
        String[] cities = {"Абакан", "Анадырь", "Архангельск","Астрахань","Барнаул","Белгород","Биробиджан","Благовещенск","Брянск",
                "Великий Новгород","Владивосток","Владикавказ","Владимир","Волгоград","Вологда","Воронеж","Горно-Алтайск",
                "Грозный","Екатеринбург","Иваново","Ижевск","Иркутск","Йошкар-Ола","Казань","Калининград","Калуга",
                "Кемерово","Киров","Кострома","Краснодар","Красноярск","Курган","Курск","Кызыл","Липецк","Магадан","Магас",
                "Майкоп","Махачкала","Москва","Мурманск","Нальчик","Нарьян-Мар","Нижний Новгород","Новосибирск","Омск","Орёл",
                "Оренбург","Пенза","Пермь","Петрозаводск","Петропавловск-Камчатский","Псков","Ростов-на-Дону","Рязань","Салехард",
                "Самара","Санкт-Петербург","Саранск","Саратов","Севастополь","Симферополь","Смоленск","Ставрополь","Сыктывкар",
                "Тамбов","Тверь","Томск","Тула","Тюмень","Улан-Удэ","Ульяновск","Уфа","Хабаровск","Ханты-Мансийск","Чебоксары",
                "Челябинск","Черкесск","Чита","Элиста","Южно-Сахалинск","Якутск","Ярославль"};
        return cities[new Random().nextInt(cities.length-1)];
    }

    public static int generateRandomDigit(){
        return new Random().nextInt(10);
    }

    public static String generateRandomLetter(){
        String[] letters = {"А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Ъ", "Ы", "Ь", "Э", "Ю", "Я"};
        return letters[new Random().nextInt(letters.length-1)];
    }

    public static String generateRandomChar(){
        String[] chars = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", ".", ",", "/", ";", ":", "<", ">", "+", "|", "=", "_"};
        return chars[new Random().nextInt(chars.length-1)];
    }

    public static String generateValidName(String locale){
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().lastName() + " " + faker.name().firstName();
        return name.replace("ё", "е");
    }

    public static String generateValidNameDoubleLastname(String locale){
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().lastName() + "-" + faker.name().lastName() + " " + faker.name().firstName();
        return name.replace("ё", "е");
    }

    public static String generateValidNameDoubleFirstname(String locale){
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().lastName() + " " + faker.name().firstName() + " " + faker.name().firstName();
        return name.replace("ё", "е");
    }

    public static String generateValidNameWithYO(String locale){
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().lastName() + " " + faker.name().firstName();
        return name.replace("е", "ё");
    }

    public static String generateFirstName(String locale){
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().firstName();
        return name.replace("ё", "е");
    }

    public static String generateValidPhone(String locale){
        Faker faker = new Faker(new Locale(locale));
       return "+79" + faker.phoneNumber().subscriberNumber(9);
    }

    public static String generateInvalidPhone(String locale){
        Faker faker = new Faker(new Locale(locale));
        return faker.phoneNumber().subscriberNumber(9);
    }

    public static class Registration{
        private Registration(){}

        public static UserInfo generateUser(String locale){
            return new UserInfo(generateCity(), generateValidName(locale), generateValidPhone(locale));

        }
    }



    @Value
    public static class UserInfo{
        String city;
        String name;
        String phone;
    }
}
