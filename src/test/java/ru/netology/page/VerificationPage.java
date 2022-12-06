package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private SelenideElement code = $("[data-test-id='code'] input");
    private SelenideElement verificationButton = $("[data-test-id=action-verify]");

    public DashboardPage validVerify(DataGenerator.VerificationCode verificationCode) {
        code.setValue(verificationCode.getCode());
        verificationButton.click();
        return new DashboardPage();
    }

}
