package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import dto.Dto;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {


    private SelenideElement heading = $(".heading");


    private ElementsCollection dashboardButtons = $$(".button");
    private SelenideElement buyButton = dashboardButtons.findBy(text("Купить"));
    private SelenideElement creditButton = dashboardButtons.findBy(text("Купить в кредит"));
    private SelenideElement continueButton = dashboardButtons.findBy(text("Продолжить"));


    private ElementsCollection dashboardFields = $$(".input__inner");
    private SelenideElement cardNumberField = dashboardFields.findBy(text("Номер карты"));
    private SelenideElement cardExpireMonthField = dashboardFields.findBy(text("Месяц"));
    private SelenideElement cardExpireYearField = dashboardFields.findBy(text("Год"));
    private SelenideElement cardHolderNameField = dashboardFields.findBy(text("Владелец"));
    private SelenideElement cvvCodeField = dashboardFields.findBy(text("CVC/CVV"));


    private ElementsCollection notificationPopup = $$(".notification");


    public DashboardPage verifyEntering() {
        heading.shouldBe(visible);
        heading.shouldHave(text("Путешествие дня"));
        return new DashboardPage();
    }

    public DashboardPage fillAllCardFields(Dto.User user) {
        cardNumberField.find(".input__control").setValue(user.getCardNumber());
        cardExpireMonthField.find(".input__control").setValue(String.valueOf(user.getCardExpireMonth()));
        cardExpireYearField.find(".input__control").setValue(String.valueOf(user.getCardExpireYear()));
        cvvCodeField.find(".input__control").setValue(String.valueOf(user.getCvvCode()));
        cardHolderNameField.find(".input__control").setValue(user.getCardHolderName());
        return new DashboardPage();
    }


    public DashboardPage selectPurchasingScenario() {
        buyButton.click();
        return new DashboardPage();
    }

    public DashboardPage selectLoanScenario() {
        creditButton.click();
        return new DashboardPage();
    }

    public DashboardPage clickContinue() {
        continueButton.click();
        return new DashboardPage();
    }


    public DashboardPage validateCardNumberField(String number) {
        cardNumberField.find(".input__control").setValue(number);
        clickContinue();
        cardNumberField.find(".input__sub").shouldBe(visible);
        return new DashboardPage();
    }

    public DashboardPage validateCardExpireMonthField(String month) {
        cardExpireMonthField.find(".input__control").setValue(month);
        clickContinue();
        cardExpireMonthField.find(".input__sub").shouldBe(visible);
        return new DashboardPage();
    }

    public DashboardPage validateCardExpireYearField(String year) {
        cardExpireYearField.find(".input__control").setValue(year);
        clickContinue();
        cardExpireYearField.find(".input__sub").shouldBe(visible);
        return new DashboardPage();
    }

    public DashboardPage validateCardHolderNameField(String cardHolderName) {
        cardHolderNameField.find(".input__control").setValue(cardHolderName);
        clickContinue();
        cardHolderNameField.find(".input__sub").shouldBe(visible);
        return new DashboardPage();
    }

    public DashboardPage validateCVVCodeField(int code) {
        cvvCodeField.find(".input__control").setValue(String.valueOf(code));
        clickContinue();
        cvvCodeField.find(".input__sub").shouldBe(visible);
        return new DashboardPage();
    }

    public DashboardPage validateErrorNotification() {
        notificationPopup.findBy(text("Ошибка")).shouldBe(visible, Duration.ofSeconds(10));
        return new DashboardPage();
    }

    public DashboardPage validateSuccessNotification() {
        notificationPopup.findBy(text("Успешно")).shouldBe(visible, Duration.ofSeconds(10));
        return new DashboardPage();
    }


}
