package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;
import ru.netology.data.DataGenerator;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataGenerator.*;


public class AppIbankTest {

    @BeforeEach
    void startBrowser() {
        open("http://localhost:9999/", LoginPage.class);
    }


    @Test
    void shouldBeSuccessTransferFromFirstToSecond() {
        var loginPage = new LoginPage();
        var authInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataGenerator.getVerificationCode();
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
        var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
        var amount = generateValidMoneyAmount(firstCardBalance);
        var expectedBalanceFirstCard = firstCardBalance - amount;
        var expectedBalanceSecondCard = secondCardBalance + amount;

        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), firstCardInfo);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);

        Assertions.assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        Assertions.assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    void shouldBeSuccessTransferFromSecondToFirst() {
        var loginPage = new LoginPage();
        var authInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataGenerator.getVerificationCode();
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
        var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
        var amount = generateValidMoneyAmount(secondCardBalance);
        var expectedBalanceFirstCard = firstCardBalance + amount;
        var expectedBalanceSecondCard = secondCardBalance - amount;

        var transferPage = dashboardPage.selectCardToTransfer(firstCardInfo);
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), secondCardInfo);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);

        Assertions.assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        Assertions.assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    void shouldNotBeTransferFromFirstToSecond() {
        var loginPage = new LoginPage();
        var authInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataGenerator.getVerificationCode();
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
        var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
        var amount = generateInvalidMoneyAmount(firstCardBalance);


        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), firstCardInfo);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);

        Assertions.assertEquals(firstCardBalance, actualBalanceFirstCard);
        Assertions.assertEquals(secondCardBalance, actualBalanceSecondCard);
    }

    @Test
    void shouldNotBeTransferFromSecondToFirst() {
        var loginPage = new LoginPage();
        var authInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataGenerator.getVerificationCode();
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
        var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
        var amount = generateInvalidMoneyAmount(secondCardBalance);

        var transferPage = dashboardPage.selectCardToTransfer(firstCardInfo);
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), secondCardInfo);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);

        Assertions.assertEquals(firstCardBalance, actualBalanceFirstCard);
        Assertions.assertEquals(secondCardBalance, actualBalanceSecondCard);
    }

    @Test
    void shouldNotBeTransferFromFirstToFirst() {
        var loginPage = new LoginPage();
        var authInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataGenerator.getVerificationCode();
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCardInfo();
        var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
        var amount = generateValidMoneyAmount(firstCardBalance);


        var transferPage = dashboardPage.selectCardToTransfer(firstCardInfo);
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), firstCardInfo);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);

        Assertions.assertEquals(firstCardBalance, actualBalanceFirstCard);
    }

    @Test
    void shouldNotBeTransferFromSecondToSecond() {
        var loginPage = new LoginPage();
        var authInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataGenerator.getVerificationCode();
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var secondCardInfo = getSecondCardInfo();
        var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
        var amount = generateValidMoneyAmount(secondCardBalance);

        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), secondCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);

        Assertions.assertEquals(secondCardBalance, actualBalanceSecondCard);
    }

    @Test
    void shouldNotBeTransferFromNothingToFirst() {
        var loginPage = new LoginPage();
        var authInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataGenerator.getVerificationCode();
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
        var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
        var amount = generateValidMoneyAmount(secondCardBalance);

        var transferPage = dashboardPage.selectCardToTransfer(firstCardInfo);
        dashboardPage = transferPage.makeInvalidTransfer(String.valueOf(amount), secondCardInfo);

        transferPage.findErrorMessage("Ошибка");
    }

    @Test
    void shouldNotBeTransferFromNothingToSecond() {
        var loginPage = new LoginPage();
        var authInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataGenerator.getVerificationCode();
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
        var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
        var amount = generateValidMoneyAmount(secondCardBalance);

        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transferPage.makeInvalidTransfer(String.valueOf(amount), secondCardInfo);

        transferPage.findErrorMessage("Ошибка");
    }

}