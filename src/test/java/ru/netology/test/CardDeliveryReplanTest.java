package ru.netology.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class CardDeliveryReplanTest {

    @BeforeEach
    void startBrowser() {
        open("http://localhost:9999/");
    }
    
    @Test
    void shouldReplanWhenSameValuesYetAnotherDateNoPageRefresh(){
        DataGenerator.UserInfo validInfo = DataGenerator.Registration.generateUser("RU");
        String date = DataGenerator.generateDate(DataGenerator.generateRandomDigit() + 5);
        String anotherDate = DataGenerator.generateDate(DataGenerator.generateRandomDigit() + 5);
        $("[data-test-id='city'] [class='input__control']").setValue(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='city'] [class='input__control']").setValue(validInfo.getCity());
        $("[data-test-id='date'] [class='input__control']").setValue(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(date);
        $("[data-test-id='name'] [name='name']").setValue(validInfo.getName());
        $("[data-test-id='phone'] [name='phone']").setValue(validInfo.getPhone());
        $("[data-test-id='agreement'] [class='checkbox__box']").click();
        $$("[type='button']").findBy(text("Запланировать")).click();

        $("[data-test-id='success-notification'] [class='notification__title']")
                .shouldBe(visible)
                .shouldHave(text("Успешно!"));
        $("[data-test-id='success-notification'] [class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + date));

        $("[data-test-id='date'] [class='input__control']").setValue(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(anotherDate);
        $$("[type='button']").findBy(text("Запланировать")).click();

        $("[data-test-id='replan-notification'] [class='notification__title']")
                .shouldBe(visible)
                .shouldHave(text("Необходимо подтверждение"));
        $("[data-test-id='replan-notification'] [class='notification__content']")
                .shouldHave(text("У вас уже запланирована встреча на другую дату"));
        $("[data-test-id='replan-notification'] [class='button__text']")
                .shouldBe(visible)
                .shouldHave(text("Перепланировать"));

        $("[data-test-id='replan-notification'] [class='button__text']").click();
        $("[data-test-id=success-notification] [class='notification__title']")
               .shouldBe(visible)
               .shouldHave(text("Успешно!"));
        $("[data-test-id=success-notification] [class=notification__content]")
                .shouldBe(visible)
                .shouldHave(text("Встреча успешно запланирована на " + anotherDate));
    }

    @Test
    void shouldReplanWhenSameValuesYetAnotherDatePageRefresh(){
        DataGenerator.UserInfo validInfo = DataGenerator.Registration.generateUser("RU");
        String date = DataGenerator.generateDate(DataGenerator.generateRandomDigit() + 5);
        String anotherDate = DataGenerator.generateDate(DataGenerator.generateRandomDigit() + 5);
        $("[data-test-id='city'] [class='input__control']").setValue(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='city'] [class='input__control']").setValue(validInfo.getCity());
        $("[data-test-id='date'] [class='input__control']").setValue(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(date);
        $("[data-test-id='name'] [name='name']").setValue(validInfo.getName());
        $("[data-test-id='phone'] [name='phone']").setValue(validInfo.getPhone());
        $("[data-test-id='agreement'] [class='checkbox__box']").click();
        $$("[type='button']").findBy(text("Запланировать")).click();

        $("[data-test-id='success-notification'] [class='notification__title']")
                .shouldBe(visible)
                .shouldHave(text("Успешно!"));
        $("[data-test-id='success-notification'] [class='notification__content']")
                .shouldHave(text("Встреча успешно запланирована на " + date));

        refresh();

        $("[data-test-id='city'] [class='input__control']").setValue(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='city'] [class='input__control']").setValue(validInfo.getCity());
        $("[data-test-id='date'] [class='input__control']").setValue(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(anotherDate);
        $("[data-test-id='name'] [name='name']").setValue(validInfo.getName());
        $("[data-test-id='phone'] [name='phone']").setValue(validInfo.getPhone());
        $("[data-test-id='agreement'] [class='checkbox__box']").click();
        $$("[type='button']").findBy(text("Запланировать")).click();

        $("[data-test-id='replan-notification'] [class='notification__title']")
                .shouldBe(visible)
                .shouldHave(text("Необходимо подтверждение"));
        $("[data-test-id='replan-notification'] [class='notification__content']")
                .shouldHave(text("У вас уже запланирована встреча на другую дату"));
        $("[data-test-id='replan-notification'] [class='button__text']")
                .shouldBe(visible)
                .shouldHave(text("Перепланировать"));

        $("[data-test-id='replan-notification'] [class='button__text']").click();
        $("[data-test-id=success-notification] [class='notification__title']")
                .shouldBe(visible)
                .shouldHave(text("Успешно!"));
        $("[data-test-id=success-notification] [class=notification__content]")
                .shouldBe(visible)
                .shouldHave(text("Встреча успешно запланирована на " + anotherDate));
    }

}
