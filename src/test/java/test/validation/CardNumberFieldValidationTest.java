package test.validation;

import data.DataHelper;
import dto.Dto;
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
    void shouldPrintSubWhenCardNumberFieldIsEmpty(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardNumberField("");
    }

    @Test
    void shouldPrintSubWhenCardNumberFieldWithChar(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardNumberField(DataHelper.getRandomChar());
    }

    @Test
    void shouldPrintSubWhenCardNumberFieldWithEnglishLetter(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardNumberField(DataHelper.getRandomEnglishLetter());
    }

    @Test
    void shouldPrintSubWhenCardNumberFieldWithRussianLetter(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardNumberField(DataHelper.getRandomRussianLetter());
    }

    @Test
    void shouldPrintSubWhenCardNumberShorter16digits(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardNumberField(DataHelper.getRandomCode(DataHelper.getRandomInt(14, 1)));
    }


}
