package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private static SelenideElement heading = $("[data-test-id='dashboard']");
    private static ElementsCollection cards = $$(".list__item div");

    public DashboardPage verifyEntering() {
        heading.shouldBe(visible);
        heading.shouldHave(text("Личный кабинет"));
        return new DashboardPage();
    }

    public int getCardBalance(DataGenerator.CardInfo cardInfo) {
        SelenideElement item = cards.findBy(text(cardInfo.getCardNumber().substring(15, 19)));
        String value = item.getText();
        value = value.substring(value.indexOf(":") + 1, value.indexOf("р")).trim();
        return Integer.parseInt(value);
    }

    public TransferPage selectCardToTransfer(DataGenerator.CardInfo cardInfo) {
        cards.findBy(attribute("data-test-id", cardInfo.getTestID())).$("button").click();
        return new TransferPage();
    }

}
