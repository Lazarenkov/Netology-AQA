package ru.netology;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.util.Locale;
import java.util.Random;

@UtilityClass
public class RequestGenerator {



    public static RegistrationData generateAuthData(String loginShould, String passwordShould, String statusShould) {
        Faker faker = new Faker(new Locale("EN"));

        String login = null;
        if (loginShould.equals("valid")) {
            login = faker.internet().emailAddress();
        } else if (loginShould.equals("invalid")) {
            login = "";
        }

        String password = null;
        if (passwordShould.equals("valid")) {
            password = faker.internet().password();
        }else if (passwordShould.equals("invalid")) {
            password = "";
        }

        String status = null;
        if (statusShould.equals("valid")) {
            String[] statuses = {"active", "blocked"};
            status = statuses[new Random().nextInt(statuses.length - 1)];
        }else if (statusShould.equals("invalid")){
            status = faker.lorem().word();
        }

        return new RegistrationData(
                login,
                password,
                status);

    }

}
