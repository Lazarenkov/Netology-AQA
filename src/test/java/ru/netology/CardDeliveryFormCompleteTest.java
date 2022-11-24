package ru.netology;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.selector.ByText;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class CardDeliveryFormCompleteTest {

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
        open("http://localhost:9999/");
    }

    @AfterAll
    static void tearsDownAll(){
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldCreateOrderWhenAllValuesPositive(){
        $("[data-test-id='city'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='city'] [class='input__control']").setValue(RegistrationInfo().getCity());
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue(RegistrationInfo().getName());
        $("[data-test-id='phone'] [name='phone']").setValue(RegistrationInfo().getPhone());
        $("[data-test-id='agreement'] [class='checkbox__box']").click();
        $$("[type='button']").findBy(text("Запланировать")).click();

        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(1));
        $("[data-test-id='success-notification'] [class='notification__title']")
                .shouldHave(text("Успешно!"));
        $("[data-test-id='success-notification'] [class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + setDateForTest(11)));
    }

    @Test
    void shouldPrintSubWhenAllValuesEmpty(){
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $$("[type='button']").findBy(text("Запланировать")).click();

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
        $$("[type='button']").findBy(text("Запланировать")).click();

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
        $("[data-test-id='agreement'] [class='checkbox__box']").click();
        $$("[type='button']").findBy(text("Запланировать")).click();

        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(1));
        $("[data-test-id='success-notification'] [class='notification__title']")
                .shouldHave(text("Успешно!"));
        $("[data-test-id='success-notification'] [class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + setDateForTest(11)));
    }

}
