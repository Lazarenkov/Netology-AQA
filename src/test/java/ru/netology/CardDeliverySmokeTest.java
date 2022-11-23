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


public class CardDeliverySmokeTest {

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
    void shouldCreateOrderWhenAllValuesPositive() {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Андрей Лазаренков");
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
    void shouldPrintSubWhenAllValuesEmpty() {
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $(By.className("button__text")).click();

        $("[data-test-id='city'] [class='input__sub']").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldPrintSubWhenAllValuesNegative() {
        $("[data-test-id='city'] [class='input__control']").setValue("Урюпинск");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(-3));
        $("[data-test-id='name'] [name='name']").setValue("А");
        $("[data-test-id='phone'] [name='phone']").setValue("910910112");
        $(By.className("button__text")).click();

        $("[data-test-id='city'] [class='input__sub']").shouldHave(text("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldCreateOrderWhenAllValidNeedsTrim(){
        $("[data-test-id='city'] [class='input__control']" ).setValue("  Калуга ");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(" " + setDateForTest(11) + "  ");
        $("[data-test-id='name'] [name='name']").setValue(" Андрей Лазаренков  ");
        $("[data-test-id='phone'] [name='phone']").setValue("  +79109101122  ");
        $("[class=checkbox__text]").click();
        $(By.className("button__text") ).click();

        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(11));
        $("[data-test-id=notification]").shouldHave(text("Успешно!"));
        $("[data-test-id=notification] [class='notification__content']")
                .shouldHave(text("Встреча успешно забронирована на " + setDateForTest(11)))
                .shouldBe(visible, Duration.ofSeconds(15));
    }

}
