package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;


public class CardDeliveryAgreementTest {

    private String setDateForTest(int todayPlusInt) {
        return LocalDate.now().plusDays(todayPlusInt).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    private static final String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;

    RegistrationInfo RegistrationInfo() {
        return DataGenerator.generate("ru");
    }


    @BeforeEach
    void startBrowser() {
        Configuration.headless = true;
        open("http://localhost:9999/");
    }

    @Test
    void shouldBeRedTextWhenAgreementNotChecked() {
        $("[data-test-id='city'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='city'] [class='input__control']").setValue(RegistrationInfo().getCity());
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue(RegistrationInfo().getName());
        $("[data-test-id='phone'] [name='phone']").setValue(RegistrationInfo().getPhone());

        $(By.className("button__text")).click();

        $("[data-test-id='agreement'] [class=checkbox__text]")
                .shouldNotHave(cssValue("color", "rgba(11,31,53,.95)"));
    }
}

