package ru.netology.dto;

import lombok.Value;

public class DTO {

    @Value
    public static class LoginDTO{
        String login;
        String password;
    }

    @Value
    public static class VerificationDTO{
        String login;
        String code;
    }

    @Value
    public static class TransferDTO{
        String from;
        String to;
        String amount;
    }
}
