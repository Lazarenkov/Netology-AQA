package ru.netology.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import java.time.Duration;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class CardDeliveryDateTest {

    @BeforeEach
    void startBrowser() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldPrintSubWhenDateNegative() {
        DataGenerator.UserInfo validInfo = DataGenerator.Registration.generateUser("RU");
        String dateForTest = DataGenerator.generateDate(DataGenerator.generateRandomDigit() * -1);
        $("[data-test-id='city'] [class='input__control']").setValue(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='city'] [class='input__control']").setValue(validInfo.getCity());
        $("[data-test-id='date'] [class='input__control']").setValue(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(dateForTest);
        $("[data-test-id='name'] [name='name']").setValue(validInfo.getName());
        $("[data-test-id='phone'] [name='phone']").setValue(validInfo.getPhone());
        $("[data-test-id='agreement'] [class='checkbox__box']").click();
        $$("[type='button']").findBy(text("Запланировать")).click();

        $("[data-test-id='date'] [class='input__sub']").shouldHave(text("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldCreateOrderWhenDateAuto() {
        DataGenerator.UserInfo validInfo = DataGenerator.Registration.generateUser("RU");
        String dateForTest = DataGenerator.generateDate(3);
        $("[data-test-id='city'] [class='input__control']").setValue(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='city'] [class='input__control']").setValue(validInfo.getCity());
        $("[data-test-id='name'] [name='name']").setValue(validInfo.getName());
        $("[data-test-id='phone'] [name='phone']").setValue(validInfo.getPhone());
        $("[data-test-id='agreement'] [class='checkbox__box']").click();
        $$("[type='button']").findBy(text("Запланировать")).click();

        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(1));
        $("[data-test-id='success-notification'] [class='notification__title']")
                .shouldHave(text("Успешно!"));
        $("[data-test-id='success-notification'] [class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + dateForTest));
    }

}
