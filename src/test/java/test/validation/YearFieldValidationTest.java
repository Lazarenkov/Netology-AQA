package test.validation;

import data.DataHelper;
import dto.Dto;
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
    void shouldPrintSubWhenYearFieldIsEmpty(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardExpireYearField("");
    }

    @Test
    void shouldPrintSubWhenYearFieldWith1Digit(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardExpireYearField(String.valueOf(DataHelper.getRandomInt(8,1)));
    }

    @Test
    void shouldPrintSubWhenYearFieldWithChar(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardExpireYearField(DataHelper.getRandomChar());
    }

    @Test
    void shouldPrintSubWhenYearFieldWithEnglishLetter(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardExpireYearField(DataHelper.getRandomEnglishLetter());
    }

    @Test
    void shouldPrintSubWhenYearFieldWithRussianLetter(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardExpireYearField(DataHelper.getRandomRussianLetter());
    }


    @Test
    void shouldPrintSubWhenYearIsBelow23(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardExpireYearField(String.valueOf(DataHelper.getRandomInt(12,10)));
    }
}
