package ru.netology.data;

import ru.netology.page.DashboardPage;

import java.util.Random;

public class DataGenerator {

    private DataGenerator() {
    }


    public static class AuthInfo {
        private String login;
        private String password;
        private String code;

        public String getValidLogin() {
            return "vasya";
        }

        public String getValidPassword() {
            return "qwerty123";
        }

        public String getValidCode() {
            return "12345";
        }

        public String getInvalidLogin() {
            return "petya";
        }

        public String getInvalidPassword() {
            return "321ytrewq";
        }

        public String getInvalidCode() {
            return "54321";
        }
    }

    public static class transferInfo {
        private String card1;
        private String card2;
        private int moneyAmount;

        public static String getCardNumber(int indexOfCard) {
            switch (indexOfCard) {
                case 0:
                    return "5559 0000 0000 0001";
                case 1:
                    return "5559 0000 0000 0002";
            }
            return "";
        }


        public int getMoneyAmount(int card, String condition) {
            switch (condition) {
                case "Valid":
                    return DashboardPage.Main.getBalances(card) - 1;

                case "EqualsBalance":
                    return DashboardPage.Main.getBalances(card);

                    case "AboveBalance":
                    return DashboardPage.Main.getBalances(card) + 1;

                case "BelowZero":
                    return -1;

                case "Zero":
                    return 0;

            }
            return 0;
        }
    }

        public static class additionalInfo{
            private int randomInt;
            public int getRandomInt(int bound){
                return new Random().nextInt(bound);
            }

            public int getAnotherRandomInt(int bound, int existingRandom){
                int value;
                do {
                    value = new Random().nextInt(bound);
                } while (value == existingRandom );
                return value;
            }
        }


}
