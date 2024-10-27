package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {


    private SelenideElement dashboard = $("[data-test-id='dashboard']");

    public DashboardPage verifyEntering(){
        dashboard.shouldHave(text("Личный кабинет"));
        return new DashboardPage();
    }

}
