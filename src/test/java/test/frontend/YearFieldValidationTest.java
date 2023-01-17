package test.frontend;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import dto.Dto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;

public class YearFieldValidationTest {

    @BeforeEach
    void startBrowser() {
        open("http://localhost:8080/");
    }

    @Test
    void shouldPrintSubWhenPurchasingAndYearFieldIsEmpty() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardExpireYearField");
        String value = "";

        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndYearFieldWith1Digit() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardExpireYearField");
        String value = String.valueOf(DataHelper.getRandomInt(8, 1));

        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndYearFieldWithChar() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardExpireYearField");
        String value = DataHelper.getRandomChar();

        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndYearFieldWithEnglishLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardExpireYearField");
        String value = DataHelper.getRandomEnglishLetter();

        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenPurchasingAndYearFieldWithRussianLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardExpireYearField");
        String value = DataHelper.getRandomRussianLetter();

        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }


    @Test
    void shouldPrintSubWhenPurchasingAndYearIsBelow23() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardExpireYearField");
        String value = String.valueOf(DataHelper.getRandomInt(12, 10));

        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldDisplayFirst2DigitsWhenPurchasingAndInputValueIsLonger() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardExpireYearField");
        String value = DataHelper.getRandomCode(DataHelper.getRandomInt(83, 16));

        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();

        String expected
                = value.substring(0,2);

        String actual = dashboardPage.getFieldValue(element);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintSubWhenLoanAndYearFieldIsEmpty() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardExpireYearField");
        String value = "";

        dashboardPage.selectLoanScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndYearFieldWith1Digit() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardExpireYearField");
        String value = String.valueOf(DataHelper.getRandomInt(8, 1));

        dashboardPage.selectLoanScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndYearFieldWithChar() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardExpireYearField");
        String value = DataHelper.getRandomChar();

        dashboardPage.selectLoanScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndYearFieldWithEnglishLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardExpireYearField");
        String value = DataHelper.getRandomEnglishLetter();

        dashboardPage.selectLoanScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenLoanAndYearFieldWithRussianLetter() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardExpireYearField");
        String value = DataHelper.getRandomRussianLetter();

        dashboardPage.selectLoanScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }


    @Test
    void shouldPrintSubWhenLoanAndYearIsBelow23() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardExpireYearField");
        String value = String.valueOf(DataHelper.getRandomInt(12, 10));

        dashboardPage.selectLoanScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldDisplayFirst2DigitsWhenLoanAndInputValueIsLonger() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardExpireYearField");
        String value = DataHelper.getRandomCode(DataHelper.getRandomInt(83, 16));

        dashboardPage.selectLoanScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();

        String expected
                = value.substring(0,2);

        String actual = dashboardPage.getFieldValue(element);
        Assertions.assertEquals(expected, actual);
    }

}
