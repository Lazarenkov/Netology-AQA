package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.*;

import java.util.Locale;

public class DataHelper {

    private DataHelper(){}

    private static Faker faker = new Faker(new Locale("EN"));

    public static AuthInfo getAuthInfo(){
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getInvalidAuthInfo(){
        var login = faker.name().username();
        var password = faker.internet().password();
        return new AuthInfo(login, password);
    }

    public static VerificationCode getCode(String login){
        String code = new SQLHelper().getCodeByLogin(login);
        return new VerificationCode(code);
    }

    public static VerificationCode getInvalidCode(){
        String code = String.valueOf(faker.number().randomNumber(6, true));
        return new VerificationCode(code);
    }


    @Value
    public static class User {
        String id;
        String login;
        String password;
        String status;
    }

    @Value
    public static class AuthInfo{
        String login;
        String password;
    }

    @Value
    public static class VerificationCode{
        String code;
    }
}
