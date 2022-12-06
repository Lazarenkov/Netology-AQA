package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private SelenideElement amountInput = $("[data-test-id='amount'] input");
    private SelenideElement fromInput = $("[data-test-id='from'] input");
    private SelenideElement transferHead = $(byText("Пополнение карты"));
    private SelenideElement errorMessage = $("[data-test-id='error-notification']");

    public TransferPage verifyTransferPage() {
        transferHead.shouldBe(visible);
        return new TransferPage();
    }

    public DashboardPage makeValidTransfer(String amountToTransfer, DataGenerator.CardInfo cardInfo) {
        amountInput.setValue(amountToTransfer);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
        ;
        return new DashboardPage();

    }

    public DashboardPage makeInvalidTransfer(String amountToTransfer, DataGenerator.CardInfo cardInfo) {
        amountInput.setValue(amountToTransfer);
        transferButton.click();
        ;
        return new DashboardPage();
    }


    public void findErrorMessage(String expectedText) {
        errorMessage.shouldHave(text(expectedText));
    }
}
