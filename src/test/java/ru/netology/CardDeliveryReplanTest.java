package ru.netology;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class CardDeliveryReplanTest {

    private String setDateForTest(int todayPlusInt) {
        return LocalDate.now().plusDays(todayPlusInt).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    private static final String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;

     private RegistrationInfo RegistrationInfo() {
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
    void shouldSuggestReplanWhenSameValuesYetAnotherDatePageRefresh(){
        String city = RegistrationInfo().getCity();
        String date = setDateForTest(new Random().nextInt(30)+3);
        String name = RegistrationInfo().getName();
        String phone = RegistrationInfo().getPhone();

        $("[data-test-id='city'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='city'] [class='input__control']").setValue(city);
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(date);
        $("[data-test-id='name'] [name='name']").setValue(name);
        $("[data-test-id='phone'] [name='phone']").setValue(phone);
        $("[data-test-id='agreement'] [class='checkbox__box']").click();
        $$("[type='button']").findBy(text("Запланировать")).click();

        refresh();


        $("[data-test-id='city'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='city'] [class='input__control']").setValue(city);
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(date);
        $("[data-test-id='name'] [name='name']").setValue(name);
        $("[data-test-id='phone'] [name='phone']").setValue(phone);
        $("[data-test-id='agreement'] [class='checkbox__box']").click();
        $$("[type='button']").findBy(text("Запланировать")).click();



        $("[data-test-id='replan-notification']").shouldBe(visible, Duration.ofSeconds(1));
        $("[data-test-id='replan-notification'] [class='notification__title']")
                .shouldHave(text("Необходимо подтверждение"));
        $("[data-test-id='replan-notification'] [class='notification__content']")
                .shouldHave(text("У вас уже запланирована встреча на другую дату"));
        $("[data-test-id='replan-notification'] [type='button']").shouldBe(visible);
        $("[data-test-id='replan-notification'] [class='button__text']").shouldHave(text("Перепланировать"));
    }

    @Test
    void shouldSuggestReplanWhenSameValuesYetAnotherDateNoPageRefresh(){
        String city = RegistrationInfo().getCity();
        String date = setDateForTest(new Random().nextInt(30)+3);
        String name = RegistrationInfo().getName();
        String phone = RegistrationInfo().getPhone();

        $("[data-test-id='city'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='city'] [class='input__control']").setValue(city);
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(date);
        $("[data-test-id='name'] [name='name']").setValue(name);
        $("[data-test-id='phone'] [name='phone']").setValue(phone);
        $("[data-test-id='agreement'] [class='checkbox__box']").click();
        $$("[type='button']").findBy(text("Запланировать")).click();


        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(date);
        $$("[type='button']").findBy(text("Запланировать")).click();



        $("[data-test-id='replan-notification']").shouldBe(visible, Duration.ofSeconds(1));
        $("[data-test-id='replan-notification'] [class='notification__title']")
                .shouldHave(text("Необходимо подтверждение"));
        $("[data-test-id='replan-notification'] [class='notification__content']")
                .shouldHave(text("У вас уже запланирована встреча на другую дату"));
        $("[data-test-id='replan-notification'] [type='button']").shouldBe(visible);
        $("[data-test-id='replan-notification'] [class='button__text']").shouldHave(text("Перепланировать"));
    }

    @Test
    void shouldReplanWhenSameValuesYetAnotherDatePageRefresh(){
        String city = RegistrationInfo().getCity();
        String date = setDateForTest(new Random().nextInt(30)+3);
        String name = RegistrationInfo().getName();
        String phone = RegistrationInfo().getPhone();

        $("[data-test-id='city'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='city'] [class='input__control']").setValue(city);
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(date);
        $("[data-test-id='name'] [name='name']").setValue(name);
        $("[data-test-id='phone'] [name='phone']").setValue(phone);
        $("[data-test-id='agreement'] [class='checkbox__box']").click();
        $$("[type='button']").findBy(text("Запланировать")).click();

        refresh();

        $("[data-test-id='city'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='city'] [class='input__control']").setValue(city);
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(date);
        $("[data-test-id='name'] [name='name']").setValue(name);
        $("[data-test-id='phone'] [name='phone']").setValue(phone);
        $("[data-test-id='agreement'] [class='checkbox__box']").click();
        $$("[type='button']").findBy(text("Запланировать")).click();
        $$("[data-test-id='replan-notification'] [type='button']").findBy(text("Перепланировать")).click();

        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(1));
        $("[data-test-id='success-notification'] [class='notification__title']")
                .shouldHave(text("Успешно!"));
        $("[data-test-id='success-notification'] [class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + date));
    }

    @Test
    void shouldReplanWhenSameValuesYetAnotherDateNoPageRefresh(){
        String city = RegistrationInfo().getCity();
        String date = setDateForTest(new Random().nextInt(30)+3);
        String name = RegistrationInfo().getName();
        String phone = RegistrationInfo().getPhone();

        $("[data-test-id='city'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='city'] [class='input__control']").setValue(city);
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(date);
        $("[data-test-id='name'] [name='name']").setValue(name);
        $("[data-test-id='phone'] [name='phone']").setValue(phone);
        $("[data-test-id='agreement'] [class='checkbox__box']").click();
        $$("[type='button']").findBy(text("Запланировать")).click();

        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(date);
        $$("[type='button']").findBy(text("Запланировать")).click();
        $$("[data-test-id='replan-notification'] [type='button']").findBy(text("Перепланировать")).click();

        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(1));
        $("[data-test-id='success-notification'] [class='notification__title']")
                .shouldHave(text("Успешно!"));
        $("[data-test-id='success-notification'] [class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + date));
    }
}
