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

public class MonthFieldValidationTest {
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
    void shouldPrintSubWhenPurchasingAndMonthFieldIsEmpty() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireMonthField");
        String value = "";
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndMonthFieldWith1Digit() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireMonthField");
        String value = String.valueOf(DataHelper.getRandomInt(8, 1));
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndMonthFieldWithChar() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireMonthField");
        String value = DataHelper.getRandomChar();
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndMonthFieldWithEnglishLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireMonthField");
        String value = DataHelper.getRandomEnglishLetter();
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndMonthFieldWithRussianLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireMonthField");
        String value = DataHelper.getRandomRussianLetter();
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndMonthIs00() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireMonthField");
        String value = "00";
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndMonthIsAbove12() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireMonthField");
        String value = String.valueOf(DataHelper.getRandomInt(87, 13));
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
        SelenideElement element = cardFormPage.getPageElement("cardExpireMonthField");
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
    void shouldNotPrintAnotherSubWhenPurchasingAndInvalidMonth(){
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireMonthField");
        String value = "";
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        int actual = cardFormPage.getSubsNumber();
        Assertions.assertEquals(1, actual);
    }


    @Test
    void shouldPrintSubWhenLoanAndMonthFieldIsEmpty() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireMonthField");
        String value = "";
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndMonthFieldWith1Digit() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireMonthField");
        String value = String.valueOf(DataHelper.getRandomInt(8, 1));
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndMonthFieldWithChar() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireMonthField");
        String value = DataHelper.getRandomChar();
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndMonthFieldWithEnglishLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireMonthField");
        String value = DataHelper.getRandomEnglishLetter();
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndMonthFieldWithRussianLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireMonthField");
        String value = DataHelper.getRandomRussianLetter();
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndMonthIs00() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireMonthField");
        String value = "00";
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndMonthIsAbove12() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireMonthField");
        String value = String.valueOf(DataHelper.getRandomInt(87, 13));
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
        SelenideElement element = cardFormPage.getPageElement("cardExpireMonthField");
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
    void shouldNotPrintAnotherSubWhenLoanAndInvalidMonth(){
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardExpireMonthField");
        String value = "";
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        int actual = cardFormPage.getSubsNumber();
        Assertions.assertEquals(1, actual);
    }

}
