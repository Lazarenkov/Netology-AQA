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


public class CardDeliveryCityTest {

    private String setDateForTest(int todayPlusInt) {
        return LocalDate.now().plusDays(todayPlusInt).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    private static final String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;


    @BeforeEach
    void startBrowser() {
        Configuration.headless = true;
        open("http://localhost:7777/");
    }

    @Test
    void shouldPrintSubWhenNameOfCityIsNotFromList() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("Урюпинск");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Андрей Лазаренков");
        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        String msg = $("[data-test-id='city'] [class='input__sub']").getText().trim();
        Assertions.assertEquals("Доставка в выбранный город недоступна", msg);
    }

    @Test
    void shouldPrintSubWhenNameOfCityIsOneLetter() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("У");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Андрей Лазаренков");
        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        String msg = $("[data-test-id='city'] [class='input__sub']").getText().trim();
        Assertions.assertEquals("Доставка в выбранный город недоступна", msg);
    }

    @Test
    void shouldPrintSubWhenNameOfCityIsDigit() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("1");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Андрей Лазаренков");
        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        String msg = $("[data-test-id='city'] [class='input__sub']").getText().trim();
        Assertions.assertEquals("Доставка в выбранный город недоступна", msg);
    }

    @Test
    void shouldPrintSubWhenNameOfCityIsChar() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("К");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Андрей Лазаренков");
        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        String msg = $("[data-test-id='city'] [class='input__sub']").getText().trim();
        Assertions.assertEquals("Доставка в выбранный город недоступна", msg);
    }
}
