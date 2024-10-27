package test;

import data.DataHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.ApplicationPage;

import java.io.IOException;

public class CardOrderNameInputTest {

    private WebDriver driver;

    @BeforeAll
    static void configureWebDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void startBrowser() throws IOException {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearsDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldCreateOrderWhenAllValidValuesAndNameWithYO() {
        String nameSrc = DataHelper.getRussianFullName();
        String name = nameSrc.substring(0, 2) + "ё" + nameSrc.substring(3);
        String phone = DataHelper.getPhoneNumber("+7", 10);
        ApplicationPage page = new ApplicationPage(driver);
        page
                .fillNameField(name)
                .fillPhoneField(phone)
                .checkAgreement()
                .clickContinue();
        Assertions.assertEquals(DataHelper.getSuccessNotificationText(), page.getTextFromSuccessNotification());
    }

    @Test
    void shouldCreateOrderWhenAllValidValuesAndNameWithHyphen() {
        String name = DataHelper.getRussianFirstName()
                + " "
                + DataHelper.getRussianLastName()
                + "-"
                + DataHelper.getRussianLastName();
        String phone = DataHelper.getPhoneNumber("+7", 10);
        ApplicationPage page = new ApplicationPage(driver);
        page
                .fillNameField(name)
                .fillPhoneField(phone)
                .checkAgreement()
                .clickContinue();
        Assertions.assertEquals(DataHelper.getSuccessNotificationText(), page.getTextFromSuccessNotification());
    }

    @Test
    void shouldCreateOrderWhenAllValidValuesNeedsTrim() {
        String name = " " + DataHelper.getRussianFullName() + " ";
        String phone = " " + DataHelper.getPhoneNumber("+7", 10) + " ";
        ApplicationPage page = new ApplicationPage(driver);
        page
                .fillNameField(name)
                .fillPhoneField(phone)
                .checkAgreement()
                .clickContinue();
        Assertions.assertEquals(DataHelper.getSuccessNotificationText(), page.getTextFromSuccessNotification());
    }

    @Test
    void shouldPrintErrorSubWhenNameOfOneWord() {
        String name = DataHelper.getRussianFirstName();
        String phone = DataHelper.getPhoneNumber("+7", 10);
        ApplicationPage page = new ApplicationPage(driver);
        page
                .fillNameField(name)
                .fillPhoneField(phone)
                .checkAgreement()
                .clickContinue();
        Assertions.assertEquals(DataHelper.getErrorSubText(), page.getTextFromNameSub());
    }

    @Test
    void shouldPrintErrorSubWhenNameWithDigit() {
        String nameSrc = DataHelper.getRussianFullName();
        String name = nameSrc.substring(0, 2) + DataHelper.getPhoneNumber("", 1) + nameSrc.substring(3);
        String phone = DataHelper.getPhoneNumber("+7", 10);
        ApplicationPage page = new ApplicationPage(driver);
        page
                .fillNameField(name)
                .fillPhoneField(phone)
                .checkAgreement()
                .clickContinue();
        Assertions.assertEquals(DataHelper.getErrorSubText(), page.getTextFromNameSub());
    }

    @Test
    void shouldPrintErrorSubWhenNameWithChar() {
        String nameSrc = DataHelper.getRussianFullName();
        String name = nameSrc.substring(0, 2) + DataHelper.getRandomChar() + nameSrc.substring(3);
        String phone = DataHelper.getPhoneNumber("+7", 10);
        ApplicationPage page = new ApplicationPage(driver);
        page
                .fillNameField(name)
                .fillPhoneField(phone)
                .checkAgreement()
                .clickContinue();
        Assertions.assertEquals(DataHelper.getErrorSubText(), page.getTextFromNameSub());
    }

    @Test
    void shouldPrintErrorSubWhenEnglishName() {
        String name = DataHelper.getEnglishName();
        String phone = DataHelper.getPhoneNumber("+7", 10);
        ApplicationPage page = new ApplicationPage(driver);
        page
                .fillNameField(name)
                .fillPhoneField(phone)
                .checkAgreement()
                .clickContinue();
        Assertions.assertEquals(DataHelper.getErrorSubText(), page.getTextFromNameSub());
    }

    @Test
    void shouldPrintErrorSubWhenEmptyName() {
        String phone = DataHelper.getPhoneNumber("+7", 10);
        ApplicationPage page = new ApplicationPage(driver);
        page
                .fillPhoneField(phone)
                .checkAgreement()
                .clickContinue();
        Assertions.assertEquals(DataHelper.getEmptyInputSubText(), page.getTextFromNameSub());
    }

}
