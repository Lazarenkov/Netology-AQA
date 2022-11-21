package ru.netology;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.LogEventListener;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;


public class CardDeliverySmokeTest {

    private String setDateForTest(int todayPlusInt) {
        return LocalDate.now().plusDays(todayPlusInt).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    private static final String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;

    RegistrationInfo RegistrationInfo() {
        return DataGenerator.generate("ru");
    }

    @BeforeAll
    static void setUpAll(){
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    @BeforeEach
    void startBrowser() {
        Configuration.headless = true;
        open("http://localhost:9999/");
    }

    @AfterAll
    static void tearsDownAll(){
        SelenideLogger.removeListener("allure");
    }


    @Test
    void sout(){

       System.out.println(RegistrationInfo().getName());
        System.out.println(RegistrationInfo().getName());
        System.out.println(RegistrationInfo().getName());

    }
    @Test
    void shouldCreateOrderWhenAllValuesPositive(){
        $("[data-test-id='city'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='city'] [class='input__control']").setValue(RegistrationInfo().getCity());
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue(RegistrationInfo().getName());
        $("[data-test-id='phone'] [name='phone']").setValue(RegistrationInfo().getPhone());
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(12));
        $(".notification__title")
                .shouldHave(text("Успешно!"), Duration.ofSeconds(12))
                .shouldBe(visible);
        $(".notification__content")
                .shouldHave(text("Встреча успешно забронирована на " + setDateForTest(11)), Duration.ofSeconds(12))
                .shouldBe(visible);
    }

    @Test
    void shouldPrintSubWhenAllValuesEmpty(){
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $(By.className("button__text")).click();

        $("[data-test-id='city'] [class='input__sub']").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldPrintSubWhenAllValuesNegative(){
        $("[data-test-id='city'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='city'] [class='input__control']").setValue(RegistrationInfo().getName());
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(-3));
        $("[data-test-id='name'] [name='name']").setValue(RegistrationInfo().getRandomSymbol());
        $("[data-test-id='phone'] [name='phone']").setValue(RegistrationInfo().getPhoneNoPlus12());
        $(By.className("button__text")).click();

        $("[data-test-id='city'] [class='input__sub']").shouldHave(text("Доставка в выбранный город недоступна"));

    }

    @Test
    void shouldCreateOrderWhenAllValidNeedsTrim() {
        $("[data-test-id='city'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='city'] [class='input__control']").setValue(" " + RegistrationInfo().getCity() + "  ");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(" " + setDateForTest(11) + "  ");
        $("[data-test-id='name'] [name='name']").setValue(" " + RegistrationInfo().getName());
        $("[data-test-id='phone'] [name='phone']").setValue(" " + RegistrationInfo().getPhone() + " ");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(12));
        $(".notification__title")
                .shouldHave(text("Успешно!"), Duration.ofSeconds(12))
                .shouldBe(visible);
        $(".notification__content")
                .shouldHave(text("Встреча успешно забронирована на " + setDateForTest(11)), Duration.ofSeconds(12))
                .shouldBe(visible);
    }

}
