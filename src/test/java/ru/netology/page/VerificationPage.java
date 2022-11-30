package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private SelenideElement code = $("[data-test-id='code'] input");
    private SelenideElement verificationButton = $("[data-test-id=action-verify]");

    public void validVerify(DataGenerator.AuthInfo info){
        code.setValue(info.getValidCode());
        verificationButton.click();
    }

}
