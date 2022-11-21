package ru.netology;

import lombok.Data;

@Data
public class RegistrationInfo {
    private final String randomDigit;
    private final String randomSymbol;
    private final String randomLetter;

    private final String city;

    private final String name;
    private final String firstName;
    private final String lastName;
    private final String firstNameDoubled;
    private final String lastNameDoubled;
    private final String englishName;
    private final String nameWithYO;

    private final String phone;
    private final String phoneNoPlus;
    private final String phoneNoPlus10;
    private final String phoneNoPlus12;
    private final String phoneWithPlus10;
    private final String phoneWithPlus12;
    private final String phoneWithPlus1;


    public RegistrationInfo(String randomDigit, String randomSymbol, String randomLetter, String city, String name, String firstName, String lastName, String firstNameDoubled, String lastNameDoubled, String englishName, String nameWithYO,  String phone, String phoneNoPlus, String phoneNoPlus10, String phoneNoPlus12, String phoneWithPlus10, String phoneWithPlus12, String phoneWithPlus1) {
        this.randomDigit = randomDigit;
        this.randomSymbol = randomSymbol;
        this.randomLetter = randomLetter;
        this.city = city;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.firstNameDoubled = firstNameDoubled;
        this.lastNameDoubled = lastNameDoubled;
        this.englishName = englishName;
        this.nameWithYO = nameWithYO;
        this.phone = phone;
        this.phoneNoPlus = phoneNoPlus;
        this.phoneNoPlus10 = phoneNoPlus10;
        this.phoneNoPlus12 = phoneNoPlus12;
        this.phoneWithPlus10 = phoneWithPlus10;
        this.phoneWithPlus12 = phoneWithPlus12;
        this.phoneWithPlus1 = phoneWithPlus1;
    }



    public String getRandomDigit() { return this.randomDigit; }

    public String getRandomSymbol() {
        return this.randomSymbol;
    }

    public String getRandomLetter() {
        return this.randomLetter;
    }

    public String getCity() {
        return this.city;
    }

    public String getName() {
        return this.name;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFirstNameDoubled() {
        return this.firstNameDoubled;
    }

    public String getLastNameDoubled() {
        return this.lastNameDoubled;
    }

    public String getEnglishName() {
        return this.englishName;
    }
    public String getNameWithYO() {
        return this.nameWithYO;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getPhoneNoPlus() {
        return this.phoneNoPlus;
    }

    public String getPhoneNoPlus10() {
        return this.phoneNoPlus10;
    }

    public String getPhoneNoPlus12() {
        return this.phoneNoPlus12;
    }

    public String getPhoneWithPlus10() {
        return this.phoneWithPlus10;
    }

    public String getPhoneWithPlus12() {
        return this.phoneWithPlus12;
    }

    public String getPhoneWithPlus1() {
        return this.phoneWithPlus1;
    }

}
