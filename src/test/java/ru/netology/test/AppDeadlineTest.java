package ru.netology.test;

import lombok.*;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;



public class AppDeadlineTest {

    @BeforeEach
    void startBrowser(){
        open ("http://localhost:9999/");
    }

    @AfterAll
    static void tearsDown(){
        SQLHelper.cleanDB();
    }

    @Test
    @SneakyThrows
    void shouldBeSuccessAuthorization() {
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = new VerificationPage();
        DashboardPage dashboardPage = new DashboardPage();

        loginPage.validLogin(authInfo);
        var code = DataHelper.getCode(authInfo.getLogin());
        verificationPage.validVerify(code);
        dashboardPage.verifyEntering();
    }

    @Test
    @SneakyThrows
    void shouldNotBeSuccessAuthorizationWhenInvalidLogin() {
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        DataHelper.AuthInfo invalidAuthInfo = DataHelper.getInvalidAuthInfo();
        LoginPage loginPage = new LoginPage();

        loginPage.invalidLogin(invalidAuthInfo.getLogin(), authInfo.getPassword());
        loginPage.verifyErrorNotification();
    }

    @Test
    @SneakyThrows
    void shouldNotBeSuccessAuthorizationWhenInvalidPassword() {
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        DataHelper.AuthInfo invalidAuthInfo = DataHelper.getInvalidAuthInfo();
        LoginPage loginPage = new LoginPage();

        loginPage.invalidLogin(authInfo.getLogin(), invalidAuthInfo.getPassword());
        loginPage.verifyErrorNotification();
    }

    @Test
    @SneakyThrows
    void shouldNotBeSuccessAuthorizationWhenInvalidVerificationCode() {
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = new VerificationPage();
        var code = DataHelper.getInvalidCode();

        loginPage.validLogin(authInfo);
        verificationPage.validVerify(code);
        verificationPage.verifyErrorNotification();
    }

    @Test
    @SneakyThrows
    void shouldBeBlockWhenInvalidPassword3Times() {
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        DataHelper.AuthInfo invalidAuthInfo = DataHelper.getInvalidAuthInfo();
        LoginPage loginPage = new LoginPage();

        for (int i=0; i<3; i++){
        loginPage.invalidLogin(authInfo.getLogin(), invalidAuthInfo.getPassword());
        loginPage.clearFields();
        }
        loginPage.validLogin(authInfo);
        loginPage.verifyErrorNotification();
    }

}
