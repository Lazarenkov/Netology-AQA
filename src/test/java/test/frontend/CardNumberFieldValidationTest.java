package test.frontend;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import dto.Dto;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.CardFormPage;
import page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;

public class CardNumberFieldValidationTest {

    @BeforeAll
    static void setUpAll(){
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    @AfterAll
    static void tearsDownAll(){
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void startBrowser() {
        open("http://localhost:8080/");
    }

    @Test
    void shouldPrintSubWhenPurchasingAndCardNumberFieldIsEmpty() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardNumberField");
        String value = "";

        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndCardNumberFieldWithChar() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardNumberField");
        String value = DataHelper.getRandomChar();

        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndCardNumberFieldWithEnglishLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardNumberField");
        String value = DataHelper.getRandomEnglishLetter();

        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndCardNumberFieldWithRussianLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardNumberField");
        String value = DataHelper.getRandomRussianLetter();

        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndCardNumberShorter16digits() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardNumberField");
        String value = DataHelper.getRandomCode(DataHelper.getRandomInt(14, 1));

        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldDisplayFirst16DigitsWhenPurchasingAndCardNumberIsLonger() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardNumberField");
        String value = DataHelper.getRandomCode(DataHelper.getRandomInt(83, 16));

        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();

        String expected
                = value.substring(0,4)
                + " "
                + value.substring(4,8)
                + " "
                + value.substring(8,12)
                + " "
                + value.substring(12,16);

        String actual = cardFormPage.getFieldValue(element);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldNotPrintAnotherSubWhenPurchasingAndInvalidCardNumber(){
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardNumberField");
        String value = "";

        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        int actual = cardFormPage.getSubsNumber();
        Assertions.assertEquals(1, actual);
    }





    @Test
    void shouldPrintSubWhenLoanAndCardNumberFieldIsEmpty() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardNumberField");
        String value = "";

        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndCardNumberFieldWithChar() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardNumberField");
        String value = DataHelper.getRandomChar();

        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndCardNumberFieldWithEnglishLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardNumberField");
        String value = DataHelper.getRandomEnglishLetter();

        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndCardNumberFieldWithRussianLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardNumberField");
        String value = DataHelper.getRandomRussianLetter();

        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndCardNumberShorter16digits() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardNumberField");
        String value = DataHelper.getRandomCode(DataHelper.getRandomInt(14, 1));

        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldDisplayFirst16DigitsWhenLoanAndCardNumberIsLonger() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardNumberField");
        String value = DataHelper.getRandomCode(DataHelper.getRandomInt(83, 16));

        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();

        String expected
                = value.substring(0,4)
                + " "
                + value.substring(4,8)
                + " "
                + value.substring(8,12)
                + " "
                + value.substring(12,16);

        String actual = cardFormPage.getFieldValue(element);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldNotPrintAnotherSubWhenLoanAndInvalidCardNumber(){
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardNumberField");
        String value = "";

        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        int actual = cardFormPage.getSubsNumber();
        Assertions.assertEquals(1, actual);
    }


}
