package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import dto.Dto;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {


    private SelenideElement heading = $(".heading");

    private ElementsCollection dashboardButtons = $$(".button");
    private SelenideElement buyButton = dashboardButtons.findBy(text("Купить"));
    private SelenideElement creditButton = dashboardButtons.findBy(text("Купить в кредит"));

    private ElementsCollection notificationPopup = $$(".notification");


    public DashboardPage verifyEntering() {
        heading.shouldBe(visible);
        heading.shouldHave(text("Путешествие дня"));
        return this;
    }

    public DashboardPage selectPurchasingScenario() {
        buyButton.click();
        return this;
    }

    public DashboardPage selectLoanScenario() {
        creditButton.click();
        return this;
    }

    public DashboardPage validateErrorNotification() {
        notificationPopup.findBy(text("Ошибка")).shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }

    public DashboardPage validateSuccessNotification() {
        notificationPopup.findBy(text("Успешно")).shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }


}
