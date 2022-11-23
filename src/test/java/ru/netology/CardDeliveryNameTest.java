package ru.netology;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;



public class CardDeliveryNameTest {


    private String setDateForTest(int todayPlusInt) {
        return LocalDate.now().plusDays(todayPlusInt).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    private static final String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;


    @BeforeEach
    void startBrowser() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldCreateOrderWhenAllValidDoubleName() {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Эрих Мария Лазаренков");
        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(11));
        $("[data-test-id=notification]").shouldHave(text("Успешно!"));
        $("[data-test-id=notification] [class='notification__content']")
                .shouldHave(text("Встреча успешно забронирована на " + setDateForTest(11)))
                .shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldCreateOrderWhenAllValidDoubleSurname() {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Андрей Мамин-Сибиряк");
        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(11));
        $("[data-test-id=notification]").shouldHave(text("Успешно!"));
        $("[data-test-id=notification] [class='notification__content']")
                .shouldHave(text("Встреча успешно забронирована на " + setDateForTest(11)))
                .shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldCreateOrderWhenAllValidDoubleNameAndSurname() {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Эрих Мария Мамин-Сибиряк");
        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(11));
        $("[data-test-id=notification]").shouldHave(text("Успешно!"));
        $("[data-test-id=notification] [class='notification__content']")
                .shouldHave(text("Встреча успешно забронирована на " + setDateForTest(11)))
                .shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldCreateOrderWhenValidNameWithYO() {
        $("[data-test-id='city'] [class='input__control']" ).setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Артём Артёмов");
        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text") ).click();

        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(11));
        $("[data-test-id=notification]").shouldHave(text("Успешно!"));
        $("[data-test-id=notification] [class='notification__content']")
                .shouldHave(text("Встреча успешно забронирована на " + setDateForTest(11)))
                .shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldPrintSubWhenInvalidEnglishName() {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Andrew Lazarenkov");
        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        $("[data-test-id='name'] [class='input__sub']").shouldHave(text("Имя и Фамилия указаные неверно"));

    }

    @Test
    void shouldPrintSubWhenInvalidNameWithChars() {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Андрей! Лазаренков");
        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        $("[data-test-id='name'] [class='input__sub']").shouldHave(text("Имя и Фамилия указаные неверно"));
    }

    @Test
    void shouldPrintSubWhenInvalidNameOfOneWord() {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Андрей");
        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        $("[data-test-id='name'] [class='input__sub']").shouldHave(text("Имя и Фамилия указаные неверно"));
    }

    @Test
    void shouldPrintSubWhenInvalidNameOfOneChar() {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("А");
        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        $("[data-test-id='name'] [class='input__sub']").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldPrintSubWhenInvalidEmptyName() {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));

        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        $("[data-test-id='name'] [class='input__sub']").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldPrintSubWhenInvalidSpaceName() {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue(" ");
        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        $("[data-test-id='name'] [class='input__sub']").shouldHave(text("Поле обязательно для заполнения"));
    }
}
