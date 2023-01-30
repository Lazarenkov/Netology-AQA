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

public class CardHolderFieldValidationTest {

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
    void shouldPrintSubWhenPurchasingAndCardHolderFieldIsEmpty() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardHolderNameField");
        String value = "";
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndCardHolderNameIsRussian() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardHolderNameField");
        String value = DataHelper.getRandomRussianName();
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndCardHolderNameIs1Word() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardHolderNameField");
        String value = DataHelper.getRandomEnglishName();
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        dashboardPage.validateSuccessNotification();
    }

    @Test
    void shouldPrintSubWhenPurchasingAndCardHolderNameWithHyphens() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardHolderNameField");
        String value = DataHelper.getRandomEnglishFirstName()
                + " "
                + DataHelper.getRandomEnglishFirstName()
                + "-"
                + DataHelper.getRandomEnglishFirstName();
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        dashboardPage.validateSuccessNotification();
    }

    @Test
    void shouldPrintSubWhenPurchasingAndCardHolderNameIsChar() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardHolderNameField");
        String value = DataHelper.getRandomChar();
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndCardHolderNameIs3Words() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardHolderNameField");
        String value = DataHelper.getRandomEnglishName()
                + " "
                + DataHelper.getRandomEnglishFirstName();
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        dashboardPage.validateSuccessNotification();
    }

    @Test
    void shouldPrintSubWhenPurchasingAndCardHolderNameIsDigit() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardHolderNameField");
        String value = DataHelper.getRandomCode(1);
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldNotPrintAnotherSubWhenPurchasingAndInvalidCardHolder(){
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardHolderNameField");
        String value = "";
        dashboardPage.selectPurchasingScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        int actual = cardFormPage.getSubsNumber();
        Assertions.assertEquals(1, actual);
    }


    @Test
    void shouldPrintSubWhenLoanAndCardHolderFieldIsEmpty() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardHolderNameField");
        String value = "";
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndCardHolderNameIsRussian() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardHolderNameField");
        String value = DataHelper.getRandomRussianName();
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndCardHolderNameIs1Word() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardHolderNameField");
        String value = DataHelper.getRandomEnglishName();
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        dashboardPage.validateSuccessNotification();
    }

    @Test
    void shouldPrintSubWhenLoanAndCardHolderNameWithHyphens() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardHolderNameField");
        String value = DataHelper.getRandomEnglishFirstName()
                + " "
                + DataHelper.getRandomEnglishFirstName()
                + "-"
                + DataHelper.getRandomEnglishFirstName();
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        dashboardPage.validateSuccessNotification();
    }

    @Test
    void shouldPrintSubWhenLoanAndCardHolderNameIsChar() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardHolderNameField");
        String value = DataHelper.getRandomChar();
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndCardHolderNameIs3Words() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardHolderNameField");
        String value = DataHelper.getRandomEnglishName()
                + " "
                + DataHelper.getRandomEnglishFirstName();
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        dashboardPage.validateSuccessNotification();
    }

    @Test
    void shouldPrintSubWhenLoanAndCardHolderNameIsDigit() {
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardHolderNameField");
        String value = DataHelper.getRandomCode(1);
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        cardFormPage.validateErrorSub(element);
    }

    @Test
    void shouldNotPrintAnotherSubWhenLoanAndInvalidCardHolder(){
        DashboardPage dashboardPage = new DashboardPage();
        CardFormPage cardFormPage = new CardFormPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = cardFormPage.getPageElement("cardHolderNameField");
        String value = "";
        dashboardPage.selectLoanScenario();
        cardFormPage.fillAllCardFields(user);
        cardFormPage.fillField(element, value).clickContinue();
        int actual = cardFormPage.getSubsNumber();
        Assertions.assertEquals(1, actual);
    }

}
