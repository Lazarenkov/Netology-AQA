package test.validation;

import data.DataHelper;
import dto.Dto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;

public class CardHolderFieldValidationTest {

    @BeforeEach
    void startBrowser() {
        open("http://localhost:8080/");
    }

    @Test
    void shouldPrintSubWhenCardHolderFieldIsEmpty() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardHolderNameField("");
    }

    @Test
    void shouldPrintSubWhenCardHolderFiledWithRussianLetters() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardHolderNameField(DataHelper.getRandomRussianName());
    }

    @Test
    void shouldPrintSubWhenCardHolderNameOfOneWord() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardHolderNameField(DataHelper.getRandomEnglishFirstName());
    }

    @Test
    void shouldBeSuccessTransactionWhenCardHolderNameWithHyphens() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.validateCardHolderNameField(DataHelper
                .getRandomEnglishFirstName()
                + " "
                + DataHelper.getRandomEnglishFirstName()
                + "-"
                + DataHelper.getRandomEnglishFirstName());

    }
}
