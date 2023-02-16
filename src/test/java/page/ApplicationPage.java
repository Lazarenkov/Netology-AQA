package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class ApplicationPage {

    By nameField = By.cssSelector("[data-test-id='name']");
    By nameFieldInput = By.cssSelector("[data-test-id='name'] input");
    By phoneField = By.cssSelector("[data-test-id='phone']");
    By phoneFieldInput = By.cssSelector("[data-test-id='phone'] input");
    By agreementCheckbox = By.cssSelector("[data-test-id='agreement']");
    By agreementInputInvalid = By.cssSelector("[data-test-id='agreement'].input_invalid");
    By continueButton = By.cssSelector("button");
    By successNotification = By.cssSelector("[data-test-id='order-success']");
    By inputSub = By.className("input__sub");


    private final WebDriver driver;

    public ApplicationPage(WebDriver driver) {
        this.driver = driver;
    }

    public ApplicationPage fillNameField(String name) {
        driver.findElement(nameFieldInput).sendKeys(name);
        return this;
    }

    public ApplicationPage fillPhoneField(String phone) {
        driver.findElement(phoneFieldInput).sendKeys(phone);
        return this;
    }

    public ApplicationPage checkAgreement() {
        driver.findElement(agreementCheckbox).click();
        return this;
    }

    public ApplicationPage clickContinue() {
        driver.findElement(continueButton).click();
        return this;
    }

    public String getTextFromSuccessNotification() {
        return driver.findElement(successNotification).getText().trim();
    }

    public String getTextFromNameSub() {
        return driver.findElement(nameField).findElement(inputSub).getText().trim();
    }

    public String getTextFromPhoneSub() {
        return driver.findElement(phoneField).findElement(inputSub).getText().trim();
    }

    public boolean getInputInvalidStatus() {
        return driver.findElement(agreementInputInvalid).isDisplayed();
    }

    public ApplicationPage clearNameField() {
        driver.findElement(nameFieldInput).sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        return this;
    }

    public ApplicationPage clearPhoneField() {
        driver.findElement(phoneFieldInput).sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        return this;
    }


}
