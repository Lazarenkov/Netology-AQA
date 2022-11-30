package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import ru.netology.data.DataGenerator;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private static SelenideElement dashboard = $("[data-test-id='dashboard']");
    private static List<SelenideElement> balances = $$("[class='list__item']");
    private static  SelenideElement balance0 = balances.get(0);
    private static SelenideElement balance1 = balances.get(1);
    private  List<SelenideElement> topUpButtons = $$("[data-test-id='action-deposit']");
//    private  SelenideElement topUpButton0 = topUpButtons.get(0);
//    private  SelenideElement topUpButton1 = topUpButtons.get(1);
    private  SelenideElement refreshButton = $("[data-test-id='action-reload']");
    private  SelenideElement amount =  $("[data-test-id='amount'] input");
    private  SelenideElement from = $("[data-test-id='from'] input");
    private  SelenideElement transferButton = $("[data-test-id='action-transfer']");


    public static class Main {

        public static DashboardPage verifyEntering() {
            dashboard.shouldBe(visible);
            dashboard.shouldHave(text("Личный кабинет"));
            return new DashboardPage();
        }

        public static int getBalances(int index){
            String  firstItem = balances.get(0).getText();
            String firstValue = firstItem.substring(firstItem.indexOf(":")+1, firstItem.indexOf("р")).trim();

            String  secondItem = balances.get(1).getText();
            String secondValue = secondItem.substring(secondItem.indexOf(":")+1, secondItem.indexOf("р")).trim();

            int[] values = {Integer.parseInt(firstValue), Integer.parseInt(secondValue)};
            return values[index];
        }

    }




    public  class Transfer {

       public  Transfer replenishCard(int indexOfCard) {
           topUpButtons.get(indexOfCard).click();
            return new Transfer();
        }

        public  Transfer fromCard(int indexOfCard) {
            from.setValue(DataGenerator.transferInfo.getCardNumber(indexOfCard));
            return new Transfer();
        }

        public  Transfer withMoneyAmount(int moneyAmount) {
            amount.setValue(String.valueOf(moneyAmount));
            transferButton.click();
            return new Transfer();
        }

    }

}
