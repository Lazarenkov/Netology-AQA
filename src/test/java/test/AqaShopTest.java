package test;

import data.DataHelper;
import dto.Dto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;

public class AqaShopTest {

    @BeforeEach
    void startBrowser() {
        open("http://localhost:8080/");
    }

    @Test
    void debugging(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectLoanScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.clickContinue();
        dashboardPage.validateSuccessNotification();
    }




}
