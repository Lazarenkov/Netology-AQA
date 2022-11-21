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


public class CardDeliveryDateTest {

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
    void shouldPrintSubWhenDateNegative() {
        $("[data-test-id='city'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='city'] [class='input__control']").setValue(RegistrationInfo().getCity());
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(-1));
        $("[data-test-id='name'] [name='name']").setValue(RegistrationInfo().getName());
        $("[data-test-id='phone'] [name='phone']").setValue(RegistrationInfo().getPhone());
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        $("[data-test-id='date'] [class='input__sub']").shouldHave(text("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldCreateOrderWhenDateAuto() {
        $("[data-test-id='city'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='city'] [class='input__control']").setValue(RegistrationInfo().getCity());
        $("[data-test-id='name'] [name='name']").setValue(RegistrationInfo().getName());
        $("[data-test-id='phone'] [name='phone']").setValue(RegistrationInfo().getPhone());
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(12));
        $(".notification__title")
                .shouldHave(text("Успешно!"), Duration.ofSeconds(12))
                .shouldBe(visible);
        $(".notification__content")
                .shouldHave(text("Встреча успешно забронирована на " + setDateForTest(3)), Duration.ofSeconds(12))
                .shouldBe(visible);
    }

}
