package ru.netology.dto;

import lombok.Value;

public class Dto {

    private Dto(){}


    @Value
    public static class Login {
        String login;
        String password;
    }

    @Value
    public static class Verification {
        String login;
        String code;
    }


    @Value
    public static class Transfer {
        String from;
        String to;
        String amount;
    }


}
