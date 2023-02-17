package ru.netology.test;

import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.RequestHelper;
import ru.netology.data.SQLHelper;

import static ru.netology.data.RequestHelper.token;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransferTest {

    @Test
    @Order(1)
    void createLogin() {
        RequestHelper.sendAuthRequest();
        System.out.println(token);
    }


//    @Test
//    void cleanDB() {
//        SQLHelper.cleanDB();
//    }

    @Test
    @DisplayName("Happy path test: Transfer From one to another card valid amount")
    @Order(2)
    void shouldBeCorrectTransferWhenValidAmount() {
        String fromCard = DataHelper.getSecondCardNumber();
        String toCard = DataHelper.getFirstCardNumber();
        int value = DataHelper.getValidTransferAmount(fromCard);
        int fromCardBalanceProper = SQLHelper.getCardBalance(fromCard);
        int toCardBalanceProper = SQLHelper.getCardBalance(toCard);
        RequestHelper.sendAuthRequest();
        RequestHelper.sendTransferRequest(fromCard, toCard, value);
        int fromCardBalanceExpected = fromCardBalanceProper - (value*100);
        int fromCardBalanceActual = SQLHelper.getCardBalance(fromCard);
        int toCardBalanceExpected = toCardBalanceProper + (value*100);
        int toCardBalanceActual = SQLHelper.getCardBalance(toCard);
        System.out.println("сумма="+value+"изначально на карте назначения="+toCardBalanceProper);
        Assertions.assertEquals(fromCardBalanceExpected, fromCardBalanceActual);
        Assertions.assertEquals(toCardBalanceExpected, toCardBalanceActual);
    }

    @Test
    @DisplayName("Critical path test: Transfer From one to another card amount, which exceed balance")
    @Order(4)
    void shouldBeNoChangesIfValueExceedBalance(){
        String fromCard = DataHelper.getFirstCardNumber();
        String toCard = DataHelper.getSecondCardNumber();
        int value = DataHelper.getInvalidExceedingBalanceTransferAmount(fromCard);
        int fromCardBalanceProper = SQLHelper.getCardBalance(fromCard);
        int toCardBalanceProper = SQLHelper.getCardBalance(toCard);
        RequestHelper.sendAuthRequest();
        RequestHelper.sendTransferRequest(fromCard, toCard, value);
        int fromCardBalanceActual = SQLHelper.getCardBalance(fromCard);
        int toCardBalanceActual = SQLHelper.getCardBalance(toCard);
        System.out.println("сумма="+value+"изначально на карте назначения="+toCardBalanceProper);
        Assertions.assertEquals(fromCardBalanceProper, fromCardBalanceActual);
        Assertions.assertEquals(toCardBalanceProper, toCardBalanceActual);
    }

    @Test
    @DisplayName("Sad path test: Transfer From one to another card amount equals 0")
    @Order(5)
    void shouldBeNoChangesIfValueIs0(){
        String fromCard = DataHelper.getFirstCardNumber();
        String toCard = DataHelper.getSecondCardNumber();
        int value = 0;
        int fromCardBalanceProper = SQLHelper.getCardBalance(fromCard);
        int toCardBalanceProper = SQLHelper.getCardBalance(toCard);
        RequestHelper.sendAuthRequest();
        RequestHelper.sendTransferRequest(fromCard, toCard, value);
        int fromCardBalanceActual = SQLHelper.getCardBalance(fromCard);
        int toCardBalanceActual = SQLHelper.getCardBalance(toCard);
        System.out.println("сумма="+value+"изначально на карте назначения="+toCardBalanceProper);
        Assertions.assertEquals(fromCardBalanceProper, fromCardBalanceActual);
        Assertions.assertEquals(toCardBalanceProper, toCardBalanceActual);
    }

    @Test
    @DisplayName("Critical path test: Transfer From one to another card negative amount")
    @Order(3)
    void shouldBeNoChangesIfValueIsNegative(){
        String fromCard = DataHelper.getFirstCardNumber();
        String toCard = DataHelper.getSecondCardNumber();
        int value = DataHelper.getInvalidNegativeTransferAmount(fromCard);
        int fromCardBalanceProper = SQLHelper.getCardBalance(fromCard);
        int toCardBalanceProper = SQLHelper.getCardBalance(toCard);
        RequestHelper.sendAuthRequest();
        RequestHelper.sendTransferRequest(fromCard, toCard, value);
        int fromCardBalanceActual = SQLHelper.getCardBalance(fromCard);
        int toCardBalanceActual = SQLHelper.getCardBalance(toCard);
        System.out.println("сумма="+value+"изначально на карте списания="+fromCardBalanceProper);
        Assertions.assertEquals(fromCardBalanceProper, fromCardBalanceActual);
        Assertions.assertEquals(toCardBalanceProper, toCardBalanceActual);
    }

}

