package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    private SelenideElement loginField = $("[data-test-id='login'] input");
    private SelenideElement passwordField = $("[data-test-id='password'] input");
    private SelenideElement actionButton = $("[data-test-id='action-login'] .button__text");

    private SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__title");

    public LoginPage validLogin(DataHelper.AuthInfo authInfo){
        loginField.setValue(authInfo.getLogin());
        passwordField.setValue(authInfo.getPassword());
        actionButton.click();
        return new LoginPage();
    }

    public LoginPage invalidLogin(String login, String password){
        loginField.setValue(login);
        passwordField.setValue(password);
        actionButton.click();
        return new LoginPage();
    }

    public LoginPage clearFields(){
        loginField.setValue(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        passwordField.setValue(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        return new LoginPage();
    }

    public LoginPage verifyErrorNotification(){
        errorNotification.shouldBe(visible).shouldHave(text("Ошибка"));
        return new LoginPage();
    }

}
