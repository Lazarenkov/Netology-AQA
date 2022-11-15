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


public class CardDeliveryPhoneTest {


    private String setDateForTest(int todayPlusInt) {
        return LocalDate.now().plusDays(todayPlusInt).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    private static final String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;


    @BeforeEach
    void startBrowser() {
        Configuration.headless = true;
        open("http://localhost:9999/");
    }

    @Test
    void shouldPrintSubWhenEmptyPhone() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Андрей Лазаренков");

        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        String msg = $("[data-test-id='phone'] [class='input__sub']").getText().trim();
        Assertions.assertEquals("Поле обязательно для заполнения", msg);
    }

    @Test
    void shouldPrintSubWhenPhoneWithoutPlus() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Андрей Лазаренков");
        $("[data-test-id='phone'] [name='phone']").setValue("79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        String msg = $("[data-test-id='phone'] [class='input__sub']").getText().trim();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", msg);
    }

    @Test
    void shouldPrintSubWhenPhoneWithOneDigitAndPlus() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Андрей Лазаренков");
        $("[data-test-id='phone'] [name='phone']").setValue("+7");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        String msg = $("[data-test-id='phone'] [class='input__sub']").getText().trim();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", msg);
    }

    @Test
    void shouldPrintSubWhenPhoneWithOneDigitAndNoPlus() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Андрей Лазаренков");
        $("[data-test-id='phone'] [name='phone']").setValue("7");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        String msg = $("[data-test-id='phone'] [class='input__sub']").getText().trim();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", msg);
    }

    @Test
    void shouldPrintSubWhenPhoneWithTenDigitsAndPlus() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Андрей Лазаренков");
        $("[data-test-id='phone'] [name='phone']").setValue("+7910910112");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        String msg = $("[data-test-id='phone'] [class='input__sub']").getText().trim();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", msg);
    }

    @Test
    void shouldPrintSubWhenPhoneWithTenDigitsAndNoPlus() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Андрей Лазаренков");
        $("[data-test-id='phone'] [name='phone']").setValue("791091011");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        String msg = $("[data-test-id='phone'] [class='input__sub']").getText().trim();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", msg);
    }

    @Test
    void shouldPrintSubWhenPhoneWithTwelveDigitsAndPlus() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Андрей Лазаренков");
        $("[data-test-id='phone'] [name='phone']").setValue("+791091011223");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        String msg = $("[data-test-id='phone'] [class='input__sub']").getText().trim();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", msg);
    }

    @Test
    void shouldPrintSubWhenPhoneWithTwelveDigitsAndNoPlus() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Андрей Лазаренков");
        $("[data-test-id='phone'] [name='phone']").setValue("791091011223");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        String msg = $("[data-test-id='phone'] [class='input__sub']").getText().trim();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", msg);
    }
}
