package test.validation;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
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
        SelenideElement element = dashboardPage.getPageElement("cardHolderNameField");
        String value = "";

        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenCardHolderNameIsRussian() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardHolderNameField");
        String value = DataHelper.getRandomRussianName();

        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenCardHolderNameIs1Word() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardHolderNameField");
        String value = DataHelper.getRandomEnglishName();

        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateSuccessNotification();
    }

    @Test
    void shouldPrintSubWhenCardHolderNameWithHyphens() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardHolderNameField");
        String value = DataHelper.getRandomEnglishFirstName()
                + " "
                + DataHelper.getRandomEnglishFirstName()
                + "-"
                + DataHelper.getRandomEnglishFirstName();

        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateSuccessNotification();
    }

    @Test
    void shouldPrintSubWhenCardHolderNameIsChar() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardHolderNameField");
        String value = DataHelper.getRandomChar();

        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

    @Test
    void shouldPrintSubWhenCardHolderNameIs3Words() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardHolderNameField");
        String value = DataHelper.getRandomEnglishName()
                + " "
                + DataHelper.getRandomEnglishFirstName();

        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateSuccessNotification();
    }

    @Test
    void shouldPrintSubWhenCardHolderNameIsDigit() {
        DashboardPage dashboardPage = new DashboardPage();
        Dto.User user = DataHelper.getValidApprovedUserData();
        SelenideElement element = dashboardPage.getPageElement("cardHolderNameField");
        String value = DataHelper.getRandomCode(1);

        dashboardPage.selectPurchasingScenario();
        dashboardPage.fillAllCardFields(user);
        dashboardPage.fillField(element, value).clickContinue();
        dashboardPage.validateErrorSub(element);
    }

}
