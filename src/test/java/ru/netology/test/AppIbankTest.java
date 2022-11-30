package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;


public class AppIbankTest {

    @BeforeEach
    void startBrowser() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldBeSuccessLogin() {
        var loginPage = new LoginPage();
        var info = new DataGenerator.AuthInfo();
        var verificationPage = new VerificationPage();

        loginPage.validLogin(info);
        verificationPage.validVerify(info);
        DashboardPage.Main.verifyEntering();
    }

    @Test
    void shouldNotBeSuccessLogin() {
        var loginPage = new LoginPage();
        var info = new DataGenerator.AuthInfo();
        var verificationPage = new VerificationPage();

        loginPage.invalidLogin(info);
    }

    @Test
    void shouldBeSuccessTransferToAnotherCardWhenMoneyAmountValid() {
        var loginPage = new LoginPage();
        var info = new DataGenerator.AuthInfo();
        var transferInfo = new DataGenerator.transferInfo();
        var additionalInfo = new DataGenerator.additionalInfo();
        var verificationPage = new VerificationPage();
        var dashboardPage = new DashboardPage();
        var transfer = dashboardPage.new Transfer();

        loginPage.validLogin(info);
        verificationPage.validVerify(info);
        DashboardPage.Main.verifyEntering();


        int firstCard = additionalInfo.getRandomInt(2);
        int secondCard = additionalInfo.getAnotherRandomInt(2, firstCard);
        int moneyAmount = transferInfo.getMoneyAmount(secondCard, "Valid");

        int[] expected = {
                DashboardPage.Main.getBalances(firstCard) + moneyAmount,
                DashboardPage.Main.getBalances(secondCard) - moneyAmount};

        transfer.replenishCard(firstCard).fromCard(secondCard).withMoneyAmount(moneyAmount);
        DashboardPage.Main.verifyEntering();

        int[] actual = {DashboardPage.Main.getBalances(firstCard), DashboardPage.Main.getBalances(secondCard)};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldBeSuccessTransferToAnotherCardWhenMoneyAmountEqualsBalance() {
        var loginPage = new LoginPage();
        var info = new DataGenerator.AuthInfo();
        var transferInfo = new DataGenerator.transferInfo();
        var additionalInfo = new DataGenerator.additionalInfo();
        var verificationPage = new VerificationPage();
        var dashboardPage = new DashboardPage();
        var transfer = dashboardPage.new Transfer();

        loginPage.validLogin(info);
        verificationPage.validVerify(info);
        DashboardPage.Main.verifyEntering();


        int firstCard = additionalInfo.getRandomInt(2);
        int secondCard = additionalInfo.getAnotherRandomInt(2, firstCard);
        int moneyAmount = transferInfo.getMoneyAmount(secondCard, "EqualsBalance");

        int[] expected = {
                DashboardPage.Main.getBalances(firstCard) + moneyAmount,
                DashboardPage.Main.getBalances(secondCard) - moneyAmount};

        transfer.replenishCard(firstCard).fromCard(secondCard).withMoneyAmount(moneyAmount);
        DashboardPage.Main.verifyEntering();

        int[] actual = {DashboardPage.Main.getBalances(firstCard), DashboardPage.Main.getBalances(secondCard)};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotBeSuccessTransferToAnotherCardWhenMoneyAmountAboveBalance() {
        var loginPage = new LoginPage();
        var info = new DataGenerator.AuthInfo();
        var transferInfo = new DataGenerator.transferInfo();
        var additionalInfo = new DataGenerator.additionalInfo();
        var verificationPage = new VerificationPage();
        var dashboardPage = new DashboardPage();
        var transfer = dashboardPage.new Transfer();

        loginPage.validLogin(info);
        verificationPage.validVerify(info);
        DashboardPage.Main.verifyEntering();


        int firstCard = additionalInfo.getRandomInt(2);
        int secondCard = additionalInfo.getAnotherRandomInt(2, firstCard);
        int moneyAmount = transferInfo.getMoneyAmount(secondCard, "AboveBalance");

        int[] expected = {
                DashboardPage.Main.getBalances(firstCard),
                DashboardPage.Main.getBalances(secondCard)};

        transfer.replenishCard(firstCard).fromCard(secondCard).withMoneyAmount(moneyAmount);
        DashboardPage.Main.verifyEntering();

        int[] actual = {DashboardPage.Main.getBalances(firstCard), DashboardPage.Main.getBalances(secondCard)};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldBeSuccessTransferToAnotherCardWhenMoneyAmountZero() {
        var loginPage = new LoginPage();
        var info = new DataGenerator.AuthInfo();
        var transferInfo = new DataGenerator.transferInfo();
        var additionalInfo = new DataGenerator.additionalInfo();
        var verificationPage = new VerificationPage();
        var dashboardPage = new DashboardPage();
        var transfer = dashboardPage.new Transfer();

        loginPage.validLogin(info);
        verificationPage.validVerify(info);
        DashboardPage.Main.verifyEntering();

        int firstCard = additionalInfo.getRandomInt(2);
        int secondCard = additionalInfo.getAnotherRandomInt(2, firstCard);
        int moneyAmount = transferInfo.getMoneyAmount(secondCard, "Zero");

        int[] expected = {
                DashboardPage.Main.getBalances(firstCard),
                DashboardPage.Main.getBalances(secondCard)};

        transfer.replenishCard(firstCard).fromCard(secondCard).withMoneyAmount(moneyAmount);
        DashboardPage.Main.verifyEntering();

        int[] actual = {DashboardPage.Main.getBalances(firstCard), DashboardPage.Main.getBalances(secondCard)};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldBeCorrectedTransferToAnotherCardWhenMoneyAmountBelowZero() {
        var loginPage = new LoginPage();
        var info = new DataGenerator.AuthInfo();
        var transferInfo = new DataGenerator.transferInfo();
        var additionalInfo = new DataGenerator.additionalInfo();
        var verificationPage = new VerificationPage();
        var dashboardPage = new DashboardPage();
        var transfer = dashboardPage.new Transfer();

        loginPage.validLogin(info);
        verificationPage.validVerify(info);
        DashboardPage.Main.verifyEntering();

        int firstCard = additionalInfo.getRandomInt(2);
        int secondCard = additionalInfo.getAnotherRandomInt(2, firstCard);
        int moneyAmount = transferInfo.getMoneyAmount(secondCard, "BelowZero");

        int[] expected = {
                DashboardPage.Main.getBalances(firstCard) - moneyAmount,
                DashboardPage.Main.getBalances(secondCard) + moneyAmount};

        transfer.replenishCard(firstCard).fromCard(secondCard).withMoneyAmount(moneyAmount);
        DashboardPage.Main.verifyEntering();

        int[] actual = {DashboardPage.Main.getBalances(firstCard), DashboardPage.Main.getBalances(secondCard)};

        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    void shouldNotBeSuccessTransferToSameCardWhenMoneyAmountValid() {
        var loginPage = new LoginPage();
        var info = new DataGenerator.AuthInfo();
        var transferInfo = new DataGenerator.transferInfo();
        var additionalInfo = new DataGenerator.additionalInfo();
        var verificationPage = new VerificationPage();
        var dashboardPage = new DashboardPage();
        var transfer = dashboardPage.new Transfer();

        loginPage.validLogin(info);
        verificationPage.validVerify(info);
        DashboardPage.Main.verifyEntering();


        int firstCard = additionalInfo.getRandomInt(2);
        int secondCard = additionalInfo.getAnotherRandomInt(2, firstCard);
        int moneyAmount = transferInfo.getMoneyAmount(firstCard, "valid");

        int[] expected = {
                DashboardPage.Main.getBalances(firstCard),
                DashboardPage.Main.getBalances(secondCard)};

        transfer.replenishCard(firstCard).fromCard(firstCard).withMoneyAmount(moneyAmount);
        DashboardPage.Main.verifyEntering();

        int[] actual = {DashboardPage.Main.getBalances(firstCard), DashboardPage.Main.getBalances(secondCard)};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotBeSuccessTransferToSameCardWhenMoneyEqualsBalance() {
        var loginPage = new LoginPage();
        var info = new DataGenerator.AuthInfo();
        var transferInfo = new DataGenerator.transferInfo();
        var additionalInfo = new DataGenerator.additionalInfo();
        var verificationPage = new VerificationPage();
        var dashboardPage = new DashboardPage();
        var transfer = dashboardPage.new Transfer();

        loginPage.validLogin(info);
        verificationPage.validVerify(info);
        DashboardPage.Main.verifyEntering();


        int firstCard = additionalInfo.getRandomInt(2);
        int secondCard = additionalInfo.getAnotherRandomInt(2, firstCard);
        int moneyAmount = transferInfo.getMoneyAmount(firstCard, "EqualsBalance");

        int[] expected = {
                DashboardPage.Main.getBalances(firstCard),
                DashboardPage.Main.getBalances(secondCard)};

        transfer.replenishCard(firstCard).fromCard(firstCard).withMoneyAmount(moneyAmount);
        DashboardPage.Main.verifyEntering();

        int[] actual = {DashboardPage.Main.getBalances(firstCard), DashboardPage.Main.getBalances(secondCard)};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotBeSuccessTransferToSameCardWhenMoneyAmountAboveBalance() {
        var loginPage = new LoginPage();
        var info = new DataGenerator.AuthInfo();
        var transferInfo = new DataGenerator.transferInfo();
        var additionalInfo = new DataGenerator.additionalInfo();
        var verificationPage = new VerificationPage();
        var dashboardPage = new DashboardPage();
        var transfer = dashboardPage.new Transfer();

        loginPage.validLogin(info);
        verificationPage.validVerify(info);
        DashboardPage.Main.verifyEntering();


        int firstCard = additionalInfo.getRandomInt(2);
        int secondCard = additionalInfo.getAnotherRandomInt(2, firstCard);
        int moneyAmount = transferInfo.getMoneyAmount(firstCard, "AboveBalance");

        int[] expected = {
                DashboardPage.Main.getBalances(firstCard),
                DashboardPage.Main.getBalances(secondCard)};

        transfer.replenishCard(firstCard).fromCard(firstCard).withMoneyAmount(moneyAmount);
        DashboardPage.Main.verifyEntering();

        int[] actual = {DashboardPage.Main.getBalances(firstCard), DashboardPage.Main.getBalances(secondCard)};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotBeSuccessTransferToSameCardWhenMoneyAmountZero() {
        var loginPage = new LoginPage();
        var info = new DataGenerator.AuthInfo();
        var transferInfo = new DataGenerator.transferInfo();
        var additionalInfo = new DataGenerator.additionalInfo();
        var verificationPage = new VerificationPage();
        var dashboardPage = new DashboardPage();
        var transfer = dashboardPage.new Transfer();

        loginPage.validLogin(info);
        verificationPage.validVerify(info);
        DashboardPage.Main.verifyEntering();

        int firstCard = additionalInfo.getRandomInt(2);
        int secondCard = additionalInfo.getAnotherRandomInt(2, firstCard);
        int moneyAmount = transferInfo.getMoneyAmount(firstCard, "Zero");

        int[] expected = {
                DashboardPage.Main.getBalances(firstCard),
                DashboardPage.Main.getBalances(secondCard)};

        transfer.replenishCard(firstCard).fromCard(firstCard).withMoneyAmount(moneyAmount);
        DashboardPage.Main.verifyEntering();

        int[] actual = {DashboardPage.Main.getBalances(firstCard), DashboardPage.Main.getBalances(secondCard)};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotBeSuccessTransferToSameCardWhenMoneyAmountBelowZero() {
        var loginPage = new LoginPage();
        var info = new DataGenerator.AuthInfo();
        var transferInfo = new DataGenerator.transferInfo();
        var additionalInfo = new DataGenerator.additionalInfo();
        var verificationPage = new VerificationPage();
        var dashboardPage = new DashboardPage();
        var transfer = dashboardPage.new Transfer();

        loginPage.validLogin(info);
        verificationPage.validVerify(info);
        DashboardPage.Main.verifyEntering();

        int firstCard = additionalInfo.getRandomInt(2);
        int secondCard = additionalInfo.getAnotherRandomInt(2, firstCard);
        int moneyAmount = transferInfo.getMoneyAmount(firstCard, "BelowZero");

        int[] expected = {
                DashboardPage.Main.getBalances(firstCard),
                DashboardPage.Main.getBalances(secondCard)};

        transfer.replenishCard(firstCard).fromCard(firstCard).withMoneyAmount(moneyAmount);
        DashboardPage.Main.verifyEntering();

        int[] actual = {DashboardPage.Main.getBalances(firstCard), DashboardPage.Main.getBalances(secondCard)};

        Assertions.assertArrayEquals(expected, actual);
    }

}