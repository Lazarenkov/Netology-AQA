package test.validation;

import data.DataHelper;
import dto.Dto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;

public class CVCFieldValidationTest {

    @BeforeEach
    void startBrowser() {
        open("http://localhost:8080/");
    }

    @Test
    void shouldPrintSubWhenCVCFieldIsEmpty(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCVVCodeField("");
    }

    @Test
    void shouldPrintSubWhenCVCShorterThen3Digits(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCVVCodeField(DataHelper.getRandomCode(DataHelper.getRandomInt(1,1)));
    }

    @Test
    void shouldPrintSubWhenCVCIsChar(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCVVCodeField(DataHelper.getRandomChar());
    }

    @Test
    void shouldPrintSubWhenCVCIsEnglishLetter(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCVVCodeField(DataHelper.getRandomEnglishLetter());
    }

    @Test
    void shouldPrintSubWhenCVCIsRussianLetter(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCVVCodeField(DataHelper.getRandomRussianLetter());
    }


}
