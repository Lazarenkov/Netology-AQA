package ru.netology.test;

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
    void shouldBeSuccessAuthorization() {
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        LoginPage loginPage = new LoginPage();

        loginPage.login(authInfo.getLogin(), authInfo.getPassword());
        var code = DataHelper.getCode(authInfo.getLogin());

        VerificationPage verificationPage = new VerificationPage();
        verificationPage.validVerify(code);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.verifyEntering();
    }

    @Test
    void shouldNotBeSuccessAuthorizationWhenInvalidLogin() {
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        DataHelper.AuthInfo invalidAuthInfo = DataHelper.getInvalidAuthInfo();

        LoginPage loginPage = new LoginPage();
        loginPage.login(invalidAuthInfo.getLogin(), authInfo.getPassword());
        loginPage.verifyInvalidLoginErrorNotification();
    }

    @Test
    void shouldNotBeSuccessAuthorizationWhenInvalidPassword() {
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        DataHelper.AuthInfo invalidAuthInfo = DataHelper.getInvalidAuthInfo();

        LoginPage loginPage = new LoginPage();
        loginPage.login(authInfo.getLogin(), invalidAuthInfo.getPassword());
        loginPage.verifyInvalidLoginErrorNotification();
    }

    @Test
    void shouldNotBeSuccessAuthorizationWhenInvalidVerificationCode() {
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();

        LoginPage loginPage = new LoginPage();
        loginPage.login(authInfo.getLogin(), authInfo.getPassword());

        VerificationPage verificationPage = new VerificationPage();
        var invalidCode = DataHelper.getInvalidCode();
        verificationPage.validVerify(invalidCode);
        verificationPage.verifyErrorNotification();
    }

    @Test
    void shouldBeBlockWhenInvalidPassword3Times() {
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        DataHelper.AuthInfo invalidAuthInfo = DataHelper.getInvalidAuthInfo();
        LoginPage loginPage = new LoginPage();

        for (int i=0; i<3; i++){
        loginPage.login(authInfo.getLogin(), invalidAuthInfo.getPassword());
        loginPage.clearFields();
        }
        loginPage.login(authInfo.getLogin(), authInfo.getPassword());
        loginPage.verifyBlockingErrorNotification();
    }

}
