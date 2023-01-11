package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import dto.Dto;
import org.openqa.selenium.Keys;

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


    public String clearField = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;

    public DashboardPage verifyEntering() {
        heading.shouldBe(visible);
        heading.shouldHave(text("Путешествие дня"));
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

    public DashboardPage fillAllCardFields(Dto.User user) {
        cardNumberField.find(".input__control").setValue(user.getCardNumber());
        cardExpireMonthField.find(".input__control").setValue(String.valueOf(user.getCardExpireMonth()));
        cardExpireYearField.find(".input__control").setValue(String.valueOf(user.getCardExpireYear()));
        cvvCodeField.find(".input__control").setValue(String.valueOf(user.getCvvCode()));
        cardHolderNameField.find(".input__control").setValue(user.getCardHolderName());
        return new DashboardPage();
    }

    public DashboardPage fillField(SelenideElement element, String value){
        element.find(".input__control").setValue(clearField).setValue(value);
        return new DashboardPage();
    }

    public SelenideElement getPageElement(String elementName){

        switch (elementName){
            case "cardNumberField":
                return cardNumberField;
            case "cardExpireMonthField":
                return cardExpireMonthField;
            case "cardExpireYearField":
                return cardExpireYearField;
            case "cardHolderNameField":
                return cardHolderNameField;
            case "cvvCodeField":
                return cvvCodeField;
        }
    return null;
    }

    public String getFieldValue(SelenideElement element){
        return element.find(".input__control").getValue();
    }

    public DashboardPage clickContinue() {
        continueButton.click();
        return new DashboardPage();
    }

    public DashboardPage validateErrorSub(SelenideElement element) {
        element.find(".input__sub").shouldBe(visible);
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
