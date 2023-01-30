package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import dto.Dto;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class CardFormPage {

    private SelenideElement continueButton = $$(".button").findBy(text("Продолжить"));

    private ElementsCollection cardFormFields = $$(".input__inner");
    private SelenideElement cardNumberField = cardFormFields.findBy(text("Номер карты"));
    private SelenideElement cardExpireMonthField = cardFormFields.findBy(text("Месяц"));
    private SelenideElement cardExpireYearField = cardFormFields.findBy(text("Год"));
    private SelenideElement cardHolderNameField = cardFormFields.findBy(text("Владелец"));
    private SelenideElement cvvCodeField = cardFormFields.findBy(text("CVC/CVV"));

    public String clearField = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;

    public CardFormPage fillAllCardFields(Dto.User user) {
        cardNumberField.find(".input__control").setValue(user.getCardNumber());
        cardExpireMonthField.find(".input__control").setValue(String.valueOf(user.getCardExpireMonth()));
        cardExpireYearField.find(".input__control").setValue(String.valueOf(user.getCardExpireYear()));
        cvvCodeField.find(".input__control").setValue(String.valueOf(user.getCvvCode()));
        cardHolderNameField.find(".input__control").setValue(user.getCardHolderName());
        return this;
    }

    public CardFormPage fillField(SelenideElement element, String value) {
        element.find(".input__control").setValue(clearField).setValue(value);
        return this;
    }

    public SelenideElement getPageElement(String elementName) {
        switch (elementName) {
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

    public String getFieldValue(SelenideElement element) {
        return element.find(".input__control").getValue();
    }

    public CardFormPage clickContinue() {
        continueButton.click();
        return this;
    }

    public CardFormPage validateErrorSub(SelenideElement element) {
        element.find(".input__sub").shouldBe(visible);
        return this;
    }

    public CardFormPage validateErrorSubAbsence(SelenideElement element) {
        element.find(".input__sub").shouldNotBe(visible);
        return this;
    }

    public int getSubsNumber() {
        return $$(".input__sub").size();
    }

}
