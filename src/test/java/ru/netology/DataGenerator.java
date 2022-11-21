package ru.netology;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.util.Locale;

@UtilityClass
public class DataGenerator {

    public static RegistrationInfo generate(String locale){
        Faker faker = new Faker(new Locale(locale));
        Faker faker1 = new Faker(new Locale("EN"));

        String randomDigit = String.valueOf((int) (Math.random()*10));

        String[] letters = {"А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Ъ", "Ы", "Ь", "Э", "Ю", "Я"};
        String randomLetter = letters[(int) (Math.random()* letters.length-1)];

        String[] chars = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", ".", ",", "/", ";", ":", "<", ">", "+", "|", "=", "_"};
        String randomSymbol = chars[(int) (Math.random()* chars.length-1)];

        String[] cities = {"Абакан", "Анадырь", "Архангельск","Астрахань","Барнаул","Белгород","Биробиджан","Благовещенск","Брянск",
        "Великий Новгород","Владивосток","Владикавказ","Владимир","Волгоград","Вологда","Воронеж","Горно-Алтайск",
        "Грозный","Екатеринбург","Иваново","Ижевск","Иркутск","Йошкар-Ола","Казань","Калининград","Калуга",
        "Кемерово","Киров","Кострома","Краснодар","Красноярск","Курган","Курск","Кызыл","Липецк","Магадан","Магас",
        "Майкоп","Махачкала","Москва","Мурманск","Нальчик","Нарьян-Мар","Нижний Новгород","Новосибирск","Омск","Орёл",
        "Оренбург","Пенза","Пермь","Петрозаводск","Петропавловск-Камчатский","Псков","Ростов-на-Дону","Рязань","Салехард",
        "Самара","Санкт-Петербург","Саранск","Саратов","Севастополь","Симферополь","Смоленск","Ставрополь","Сыктывкар",
        "Тамбов","Тверь","Томск","Тула","Тюмень","Улан-Удэ","Ульяновск","Уфа","Хабаровск","Ханты-Мансийск","Чебоксары",
        "Челябинск","Черкесск","Чита","Элиста","Южно-Сахалинск","Якутск","Ярославль"};
        String city = cities[(int) (Math.random()*cities.length-1)];


        String name = createNameWithoutYO();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String firstNameDoubled = faker.name().firstName() + " " + faker.name().firstName();
        String lastNameDoubled = faker.name().lastName() + "-" + faker.name().lastName();
        String englishName = faker1.name().fullName();
        String nameWithYO = createNameWithYO();

        String phone = "+79" + faker.phoneNumber().subscriberNumber(9);
        String phoneNoPlus = "79" + faker.phoneNumber().subscriberNumber(9);
        String phoneNoPlus10 = "79" + faker.phoneNumber().subscriberNumber(8);
        String phoneNoPlus12 = "79" + faker.phoneNumber().subscriberNumber(10);
        String phoneWithPlus10 = "+79" + faker.phoneNumber().subscriberNumber(8);
        String phoneWithPlus12 = "+79" + faker.phoneNumber().subscriberNumber(10);
        String phoneWithPlus1 = "+" + faker.phoneNumber().subscriberNumber(1);

        return new RegistrationInfo(randomDigit,
                randomSymbol,
                randomLetter,
                city,
                name,
                firstName,
                lastName,
                firstNameDoubled,
                lastNameDoubled,
                englishName,
                nameWithYO,
                phone,
                phoneNoPlus,
                phoneNoPlus10,
                phoneNoPlus12,
                phoneWithPlus10,
                phoneWithPlus12,
                phoneWithPlus1);
    }

    static String createNameWithYO(){
        Faker faker = new Faker(new Locale("RU"));
        String name = faker.name().fullName();
        for (int i=1; i<100; i++){
            if (!name.contains("ё")){
                faker = new Faker(new Locale("RU"));
                name = faker.name().fullName();
            }
        }
        return name;
    }

    static String createNameWithoutYO(){
        Faker faker = new Faker(new Locale("RU"));
        String name = faker.name().fullName();
        for (int i=1; i<100; i++){
            if (name.contains("ё")){
                faker = new Faker(new Locale("RU"));
                name = faker.name().fullName();
            }
        }
        return name;
    }
}
