package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class VerificationPage {

    private SelenideElement codeField = $("[data-test-id='code'] input");
    private SelenideElement actionButton = $("[data-test-id='action-verify'] .button__text");

    private SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__title");


    public VerificationPage validVerify(DataHelper.VerificationCode verificationCode){
        codeField.setValue(verificationCode.getCode());
        actionButton.click();
        return new VerificationPage();
    }

    public VerificationPage verifyErrorNotification(){
          errorNotification.shouldBe(visible).shouldHave(text("Ошибка"));
          return new VerificationPage();
    }

}
