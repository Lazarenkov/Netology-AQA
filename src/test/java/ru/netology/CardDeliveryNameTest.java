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


public class CardDeliveryNameTest {


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
    void shouldCreateOrderWhenAllValidDoubleName() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Эрих Мария Лазаренков");
        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(11));
        String msg = $("[data-test-id=notification]").getText();
        Assertions.assertTrue(msg.contains("Успешно!"));
    }

    @Test
    void shouldCreateOrderWhenAllValidDoubleSurname() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Андрей Мамин-Сибиряк");
        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(11));
        String msg = $("[data-test-id=notification]").getText();
        Assertions.assertTrue(msg.contains("Успешно!"));
    }

    @Test
    void shouldCreateOrderWhenAllValidDoubleNameAndSurname() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Эрих Мария Мамин-Сибиряк");
        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(11));
        String msg = $("[data-test-id=notification]").getText();
        Assertions.assertTrue(msg.contains("Успешно!"));
    }

//    @Test
//    void shouldCreateOrderWhenValidNameWithYO() throws InterruptedException {
//        $("[data-test-id='city'] [class='input__control']" ).setValue("Калуга");
//        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
//        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
//        $("[data-test-id='name'] [name='name']").setValue("Артём Артёмов");
//        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
//        $("[class=checkbox__text]").click();
//        $(By.className("button__text") ).click();
//
//        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(11));
//        String msg = $("[data-test-id=notification]").getText();
//        Assertions.assertTrue(msg.contains("Успешно!"));
//    }

    @Test
    void shouldPrintSubWhenInvalidEnglishName() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Andrew Lazarenkov");
        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        String msg = $("[data-test-id='name'] [class='input__sub']").getText().trim();
        Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", msg);
    }

    @Test
    void shouldPrintSubWhenInvalidNameWithChars() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("Андрей! Лазаренков");
        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        String msg = $("[data-test-id='name'] [class='input__sub']").getText().trim();
        Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", msg);
    }

//    @Test
//    void shouldPrintSubWhenInvalidNameOfOneWord() throws InterruptedException {
//        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
//        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
//        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
//        $("[data-test-id='name'] [name='name']").setValue("Андрей");
//        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
//        $("[class=checkbox__text]").click();
//        $(By.className("button__text")).click();
//
//        String msg = $("[data-test-id='name'] [class='input__sub']").getText().trim();
//        Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", msg);
//    }
//
//    @Test
//    void shouldPrintSubWhenInvalidNameOfOneChar() throws InterruptedException {
//        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
//        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
//        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
//        $("[data-test-id='name'] [name='name']").setValue("А");
//        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
//        $("[class=checkbox__text]").click();
//        $(By.className("button__text")).click();
//
//        String msg = $("[data-test-id='name'] [class='input__sub']").getText().trim();
//        Assertions.assertEquals("Поле обязательно для заполнения", msg);
//    }

    @Test
    void shouldPrintSubWhenInvalidEmptyName() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));

        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        String msg = $("[data-test-id='name'] [class='input__sub']").getText().trim();
        Assertions.assertEquals("Поле обязательно для заполнения", msg);
    }

    @Test
    void shouldPrintSubWhenInvalidSpaceName() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("Калуга");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue(" ");
        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        String msg = $("[data-test-id='name'] [class='input__sub']").getText().trim();
        Assertions.assertEquals("Поле обязательно для заполнения", msg);
    }
}
