package test.frontend;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import dto.Dto;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;

public class AqaShopScenariosTest {

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
    void shouldBeSuccessNotificationWhenPurchasingWithRegisteredCard() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();

        dashboardPage.fillAllCardFields(user);
        dashboardPage.clickContinue();
        dashboardPage.validateSuccessNotification();
    }

    @Test
    void shouldBeSuccessNotificationWhenLoanWithRegisteredCard() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectLoanScenario();

        dashboardPage.fillAllCardFields(user);
        dashboardPage.clickContinue();
        dashboardPage.validateSuccessNotification();
    }

    @Test
    void shouldBeErrorNotificationWhenPurchasingWithDeclinedCard() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidDeclinedUserData();
        dashboardPage.selectPurchasingScenario();

        dashboardPage.fillAllCardFields(user);
        dashboardPage.clickContinue();
        dashboardPage.validateSuccessNotification();
    }

    @Test
    void shouldBeErrorNotificationWhenLoanWithDeclinedCard() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidDeclinedUserData();
        dashboardPage.selectLoanScenario();

        dashboardPage.fillAllCardFields(user);
        dashboardPage.clickContinue();
        dashboardPage.validateSuccessNotification();
    }

    @Test
    void shouldBeErrorNotificationWhenPurchasingWithUnregisteredCard() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidUnregisteredUserData();
        dashboardPage.selectPurchasingScenario();

        dashboardPage.fillAllCardFields(user);
        dashboardPage.clickContinue();
        dashboardPage.validateErrorNotification();
    }

    @Test
    void shouldBeErrorNotificationWhenLoanWithUnregisteredCard() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidUnregisteredUserData();
        dashboardPage.selectLoanScenario();

        dashboardPage.fillAllCardFields(user);
        dashboardPage.clickContinue();
        dashboardPage.validateErrorNotification();
    }
}
