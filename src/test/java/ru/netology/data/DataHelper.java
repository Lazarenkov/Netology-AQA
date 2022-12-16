package ru.netology.data;

import com.github.javafaker.Faker;
import ru.netology.dto.Dto;

import java.util.Locale;
import java.util.Random;

public class DataHelper {

    private DataHelper(){}

    private static final Faker faker = new Faker(new Locale("EN"));


    public static String getLogin() {
        return "vasya";
    }

    public static String getPassword() {
        return "qwerty123";
    }

    public static String getCode() {
        SQLHelper query = new SQLHelper();
        return query.getCodeByLogin(getLogin());
    }

    public static String getFirstCardNumber() {
        return "5559 0000 0000 0001";
    }

    public static String getSecondCardNumber() {
        return "5559 0000 0000 0002";
    }

    public static int getValidTransferAmount(String fromCard) {
        int bound = SQLHelper.getCardBalance(fromCard);
        int amount = new Random().nextInt(bound - 1) + 1;
        return amount;
    }

    public static Dto.Login getLoginDTO() {
        return new Dto.Login(getLogin(), getPassword());
    }

    public static Dto.Verification getVerificationDTO() {
        return new Dto.Verification(getLogin(), getCode());
    }

    public static Dto.Transfer getTransferDTO(String cardFrom, String cardTo, int value) {
        return new Dto.Transfer(cardFrom, cardTo, String.valueOf(value));
    }


}
