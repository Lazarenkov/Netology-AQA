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

public class YearFieldValidationTest {

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
    void shouldPrintSubWhenPurchasingAndYearFieldIsEmpty() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireYearField");
        String value = "";
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndYearFieldWith1Digit() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireYearField");
        String value = String.valueOf(DataHelper.getRandomInt(8, 1));
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndYearFieldWithChar() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireYearField");
        String value = DataHelper.getRandomChar();
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndYearFieldWithEnglishLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireYearField");
        String value = DataHelper.getRandomEnglishLetter();
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndYearFieldWithRussianLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireYearField");
        String value = DataHelper.getRandomRussianLetter();
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndYearIsBelow23() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireYearField");
        String value = String.valueOf(DataHelper.getRandomInt(12, 10));
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldDisplayFirst2DigitsWhenPurchasingAndInputValueIsLonger() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireYearField");
        String value = DataHelper.getRandomCode(DataHelper.getRandomInt(83, 16));
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        String expected
                = value.substring(0,2);
        String actual = cardFormPage.getFieldValue(element);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldNotPrintAnotherSubWhenPurchasingAndInvalidYear(){
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireYearField");
        String value = "";
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        int actual = cardFormPage.getSubsNumber();
        Assertions.assertEquals(1, actual);
    }


    @Test
    void shouldPrintSubWhenLoanAndYearFieldIsEmpty() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireYearField");
        String value = "";
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndYearFieldWith1Digit() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireYearField");
        String value = String.valueOf(DataHelper.getRandomInt(8, 1));
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndYearFieldWithChar() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireYearField");
        String value = DataHelper.getRandomChar();
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndYearFieldWithEnglishLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireYearField");
        String value = DataHelper.getRandomEnglishLetter();
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndYearFieldWithRussianLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireYearField");
        String value = DataHelper.getRandomRussianLetter();
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }


    @Test
    void shouldPrintSubWhenLoanAndYearIsBelow23() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireYearField");
        String value = String.valueOf(DataHelper.getRandomInt(12, 10));
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldDisplayFirst2DigitsWhenLoanAndInputValueIsLonger() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireYearField");
        String value = DataHelper.getRandomCode(DataHelper.getRandomInt(83, 16));
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        String expected
                = value.substring(0,2);
        String actual = cardFormPage.getFieldValue(element);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldNotPrintAnotherSubWhenLoanAndInvalidYear(){
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireYearField");
        String value = "";
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        int actual = cardFormPage.getSubsNumber();
        Assertions.assertEquals(1, actual);
    }

}
