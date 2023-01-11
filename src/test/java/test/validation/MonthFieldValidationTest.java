package test.validation;

import data.DataHelper;
import dto.Dto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;

public class MonthFieldValidationTest {

    @BeforeEach
    void startBrowser() {
        open("http://localhost:8080/");
    }

    @Test
    void shouldPrintSubWhenMonthFieldIsEmpty(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardExpireMonthField("");
    }

    @Test
    void shouldPrintSubWhenMonthFieldWith1Digit(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardExpireMonthField(String.valueOf(DataHelper.getRandomInt(8,1)));
    }

    @Test
    void shouldPrintSubWhenMonthFieldWithChar(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardExpireMonthField(DataHelper.getRandomChar());
    }

    @Test
    void shouldPrintSubWhenMonthFieldWithEnglishLetter(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardExpireMonthField(DataHelper.getRandomEnglishLetter());
    }

    @Test
    void shouldPrintSubWhenMonthFieldWithRussianLetter(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardExpireMonthField(DataHelper.getRandomRussianLetter());
    }

    @Test
    void shouldPrintSubWhenMonthIs00(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardExpireMonthField("00");
    }

    @Test
    void shouldPrintSubWhenMonthIsAbove12(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardExpireMonthField(String.valueOf(DataHelper.getRandomInt(86,13)));
    }
}
