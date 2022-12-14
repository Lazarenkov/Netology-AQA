package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;



public class CardDeliveryAgreementTest {

    @BeforeAll
    static void setUpAll(){
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    @AfterAll
    static void tearsDownAll(){
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void startBrowser() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldBeRedTextWhenAgreementNotChecked() {
        DataGenerator.UserInfo validInfo = DataGenerator.Registration.generateUser("RU");
        String dateForTest = DataGenerator.generateDate(DataGenerator.generateRandomDigit() + 5);
        $("[data-test-id='city'] [class='input__control']").setValue(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='city'] [class='input__control']").setValue(validInfo.getCity());
        $("[data-test-id='date'] [class='input__control']").setValue(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(dateForTest);
        $("[data-test-id='name'] [name='name']").setValue(validInfo.getName());
        $("[data-test-id='phone'] [name='phone']").setValue(validInfo.getPhone());

        $$("[type='button']").findBy(text("Запланировать")).click();

        $("[data-test-id='agreement'].input_invalid").should(exist);
    }
}

