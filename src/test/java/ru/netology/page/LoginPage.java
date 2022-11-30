package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement login = $("[data-test-id='login'] input");
    private SelenideElement password =$("[data-test-id='password'] input");
    private SelenideElement actionButton = $("[data-test-id='action-login']");
    private  SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public void validLogin(DataGenerator.AuthInfo info){
        login.setValue(info.getValidLogin());
        password.setValue(info.getValidPassword());
        actionButton.click();
    }

    public void invalidLogin(DataGenerator.AuthInfo info){
        login.setValue(info.getInvalidLogin());
        password.setValue(info.getInvalidPassword());
        actionButton.click();
        errorNotification.shouldBe(visible);
    }
}
