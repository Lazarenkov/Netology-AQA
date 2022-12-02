package ru.netology.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AuthTest {

    @BeforeEach
    void startBrowser(){
        open("http://localhost:9999/");
    }

    @Test
    @DisplayName("Positive test: Registered user - Valid Login, Valid Password")
    void shouldAuthorizeWhenAllValid() {
        DataGenerator.RegistrationDTO user = DataGenerator.Registration.getRegisteredUser("active");

        $("[data-test-id='login'] input").setValue(user.getLogin());
        $("[data-test-id='password'] input").setValue(user.getPassword());
        $("[data-test-id='action-login'] .button__text").click();

        $(By.className("App_appContainer__3jRx1")).shouldBe(visible).shouldHave(text("Личный кабинет"));
    }

    @Test
    @DisplayName("Negative test: Unregistered user - Valid Login, Valid Password")
    void shouldNotAuthorizeWhenUnregisteredUser() {
        DataGenerator.RegistrationDTO user = DataGenerator.Registration.getRegisteredUser("blocked");

        $("[data-test-id='login'] input").setValue(user.getLogin());
        $("[data-test-id='password'] input").setValue(user.getPassword());
        $("[data-test-id='action-login'] .button__text").click();

        $("[data-test-id='error-notification'] .notification__title")
                .shouldBe(visible)
                .shouldHave(text("Ошибка"));
        $("[data-test-id='error-notification'] .notification__content")
                .shouldBe(visible)
                .shouldHave(text("Пользователь заблокирован"));
    }


    @Test
    @DisplayName("Negative test: Registered user - Invalid Login, Valid Password")
    void shouldNotAuthorizeWhenRegisteredUserInvalidLogin() {
        DataGenerator.RegistrationDTO user = DataGenerator.Registration.getRegisteredUser("active");

        $("[data-test-id='login'] input").setValue(DataGenerator.getRandomLogin());
        $("[data-test-id='password'] input").setValue(user.getPassword());
        $("[data-test-id='action-login'] .button__text").click();

        $("[data-test-id='error-notification'] .notification__title")
                .shouldBe(visible)
                .shouldHave(text("Ошибка"));
        $("[data-test-id='error-notification'] .notification__content")
                .shouldBe(visible)
                .shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    @DisplayName("Negative test: Registered user - Valid Login, Invalid Password")
    void shouldNotAuthorizeWhenRegisteredUserInvalidPassword() {
        DataGenerator.RegistrationDTO user = DataGenerator.Registration.getRegisteredUser("active");

        $("[data-test-id='login'] input").setValue(user.getLogin());
        $("[data-test-id='password'] input").setValue(DataGenerator.getRandomPassword());
        $("[data-test-id='action-login'] .button__text").click();

        $("[data-test-id='error-notification'] .notification__title")
                .shouldBe(visible)
                .shouldHave(text("Ошибка"));
        $("[data-test-id='error-notification'] .notification__content")
                .shouldBe(visible)
                .shouldHave(text("Неверно указан логин или пароль"));
    }

}
