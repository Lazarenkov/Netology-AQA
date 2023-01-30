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

public class CVCFieldValidationTest {

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
    void shouldPrintSubWhenPurchasingAndCVCFieldIsEmpty() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cvvCodeField");
        String value = "";
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndCVCShorterThen3Digits() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cvvCodeField");
        String value = DataHelper.getRandomCode(DataHelper.getRandomInt(1, 1));
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndCVCIsChar() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cvvCodeField");
        String value = DataHelper.getRandomChar();
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndCVCIsEnglishLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cvvCodeField");
        String value = DataHelper.getRandomEnglishLetter();
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndCVCIsRussianLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cvvCodeField");
        String value = DataHelper.getRandomRussianLetter();
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldDisplayFirst3DigitsWhenPurchasingAndCVCIsLonger() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cvvCodeField");
        String value = DataHelper.getRandomCode(DataHelper.getRandomInt(83, 16));
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        String expected
                = value.substring(0,3);
        String actual = cardFormPage.getFieldValue(element);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldNotPrintAnotherSubWhenPurchasingAndInvalidCVC(){
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cvvCodeField");
        String value = "";
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        int actual = cardFormPage.getSubsNumber();
        Assertions.assertEquals(1, actual);
    }


    @Test
    void shouldPrintSubWhenLoanAndCVCFieldIsEmpty() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cvvCodeField");
        String value = "";
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndCVCShorterThen3Digits() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cvvCodeField");
        String value = DataHelper.getRandomCode(DataHelper.getRandomInt(1, 1));
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndCVCIsChar() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cvvCodeField");
        String value = DataHelper.getRandomChar();
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndCVCIsEnglishLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cvvCodeField");
        String value = DataHelper.getRandomEnglishLetter();
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndCVCIsRussianLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cvvCodeField");
        String value = DataHelper.getRandomRussianLetter();
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldDisplayFirst3DigitsWhenLoanAndCVCIsLonger() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cvvCodeField");
        String value = DataHelper.getRandomCode(DataHelper.getRandomInt(83, 16));
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        String expected
                = value.substring(0,3);
        String actual = cardFormPage.getFieldValue(element);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldNotPrintAnotherSubWhenLoanAndInvalidCVC(){
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cvvCodeField");
        String value = "";
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        int actual = cardFormPage.getSubsNumber();
        Assertions.assertEquals(1, actual);
    }

}
