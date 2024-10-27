package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    private SelenideElement loginPage = $("[data-test-id='login'] input");
    private SelenideElement passwordPage = $("[data-test-id='password'] input");
    private SelenideElement actionButton = $("[data-test-id='action-login']");
    private SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public VerificationPage validLogin(DataGenerator.AuthInfo authInfo) {
        loginPage.setValue(authInfo.getLogin());
        passwordPage.setValue(authInfo.getPassword());
        actionButton.click();
        return page(VerificationPage.class);
    }
}
