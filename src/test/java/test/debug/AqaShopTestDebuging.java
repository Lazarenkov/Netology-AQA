package test.debug;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import data.SQLHelper;
import dto.Dto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;

public class AqaShopTestDebuging {

    @BeforeEach
    void startBrowser() {
        open("http://localhost:8080/");
    }

    @Test
    void debugging(){
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        dashboardPage.selectPurchasingScenario();
        SelenideElement element = dashboardPage.getPageElement("cardNumberField");
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element,"0");
        dashboardPage.clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void sqlDebugging(){

        System.out.println(SQLHelper.getStatusOfLastPayment());
    }

    @Test
    void helperDebugging(){
        System.out.println(DataHelper.getRandomCode(30));
    }

    @Test
    void fieldValueGetterDebug(){
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.selectPurchasingScenario();
        SelenideElement element = dashboardPage.getPageElement("cardNumberField");
        dashboardPage.fillField(element,"012345");
        System.out.println(dashboardPage.getFieldValue(element));

    }




}
