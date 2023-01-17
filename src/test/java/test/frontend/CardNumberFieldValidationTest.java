package test.frontend;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import dto.Dto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;

public class CardNumberFieldValidationTest {

    @BeforeEach
    void startBrowser() {
        open("http://localhost:8080/");
    }

    @Test
    void shouldPrintSubWhenPurchasingAndCardNumberFieldIsEmpty() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardNumberField");
        String value = "";

        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndCardNumberFieldWithChar() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardNumberField");
        String value = DataHelper.getRandomChar();

        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndCardNumberFieldWithEnglishLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardNumberField");
        String value = DataHelper.getRandomEnglishLetter();

        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndCardNumberFieldWithRussianLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardNumberField");
        String value = DataHelper.getRandomRussianLetter();

        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndCardNumberShorter16digits() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardNumberField");
        String value = DataHelper.getRandomCode(DataHelper.getRandomInt(14, 1));

        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldDisplayFirst16DigitsWhenPurchasingAndCardNumberIsLonger() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardNumberField");
        String value = DataHelper.getRandomCode(DataHelper.getRandomInt(83, 16));

        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();

        String expected
                = value.substring(0,4)
                + " "
                + value.substring(4,8)
                + " "
                + value.substring(8,12)
                + " "
                + value.substring(12,16);

        String actual = dashboardPage.getFieldValue(element);
        Assertions.assertEquals(expected, actual);
    }


    
    
    
    @Test
    void shouldPrintSubWhenLoanAndCardNumberFieldIsEmpty() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardNumberField");
        String value = "";

        dashboardPage.selectLoanScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndCardNumberFieldWithChar() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardNumberField");
        String value = DataHelper.getRandomChar();

        dashboardPage.selectLoanScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndCardNumberFieldWithEnglishLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardNumberField");
        String value = DataHelper.getRandomEnglishLetter();

        dashboardPage.selectLoanScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndCardNumberFieldWithRussianLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardNumberField");
        String value = DataHelper.getRandomRussianLetter();

        dashboardPage.selectLoanScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndCardNumberShorter16digits() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardNumberField");
        String value = DataHelper.getRandomCode(DataHelper.getRandomInt(14, 1));

        dashboardPage.selectLoanScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldDisplayFirst16DigitsWhenLoanAndCardNumberIsLonger() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardNumberField");
        String value = DataHelper.getRandomCode(DataHelper.getRandomInt(83, 16));

        dashboardPage.selectLoanScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();

        String expected
                = value.substring(0,4)
                + " "
                + value.substring(4,8)
                + " "
                + value.substring(8,12)
                + " "
                + value.substring(12,16);

        String actual = dashboardPage.getFieldValue(element);
        Assertions.assertEquals(expected, actual);
    }


}
