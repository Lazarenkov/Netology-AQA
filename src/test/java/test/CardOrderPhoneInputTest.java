package test;

import data.DataHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.ApplicationPage;

import java.io.IOException;

public class CardOrderPhoneInputTest {

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
    void shouldPrintErrorSubWhenPhoneWithoutPlus() {
        String name = DataHelper.getRussianFullName();
        String phone = DataHelper.getPhoneNumber("8", 10);
        ApplicationPage page = new ApplicationPage(driver);
        page    .fillNameField(name)
                .fillPhoneField(phone)
                .checkAgreement()
                .clickContinue();
        Assertions.assertEquals(DataHelper.getErrorPhoneSubText(), page.getTextFromPhoneSub());
    }

    @Test
    void shouldPrintErrorSubWhenPhoneOf12Digits() {
        String name = DataHelper.getRussianFullName();
        String phone = DataHelper.getPhoneNumber("+7", 11);
        ApplicationPage page = new ApplicationPage(driver);
        page    .fillNameField(name)
                .fillPhoneField(phone)
                .checkAgreement()
                .clickContinue();
        Assertions.assertEquals(DataHelper.getErrorPhoneSubText(), page.getTextFromPhoneSub());
    }

    @Test
    void shouldPrintErrorSubWhenPhoneOf10Digits() {
        String name = DataHelper.getRussianFullName();
        String phone = DataHelper.getPhoneNumber("+7", 9);
        ApplicationPage page = new ApplicationPage(driver);
        page    .fillNameField(name)
                .fillPhoneField(phone)
                .checkAgreement()
                .clickContinue();
        Assertions.assertEquals(DataHelper.getErrorPhoneSubText(), page.getTextFromPhoneSub());
    }

    @Test
    void shouldPrintErrorSubWhenPhoneWithLetters() {
        String name = DataHelper.getRussianFullName();
        String phoneSrc = DataHelper.getPhoneNumber("+7", 10);
        String phone = phoneSrc.substring(0, 2) + DataHelper.getRandomRussianLetter() + phoneSrc.substring(3);
        ApplicationPage page = new ApplicationPage(driver);
        page    .fillNameField(name)
                .fillPhoneField(phone)
                .checkAgreement()
                .clickContinue();
        Assertions.assertEquals(DataHelper.getErrorPhoneSubText(), page.getTextFromPhoneSub());
    }

    @Test
    void shouldPrintErrorSubWhenPhoneWithChar() {
        String name = DataHelper.getRussianFullName();
        String phoneSrc = DataHelper.getPhoneNumber("+7", 10);
        String phone = phoneSrc.substring(0, 2) + DataHelper.getRandomChar() + phoneSrc.substring(3);
        ApplicationPage page = new ApplicationPage(driver);
        page    .fillNameField(name)
                .fillPhoneField(phone)
                .checkAgreement()
                .clickContinue();
        Assertions.assertEquals(DataHelper.getErrorPhoneSubText(), page.getTextFromPhoneSub());
    }

    @Test
    void shouldPrintErrorSubWhenEmptyPhone() {
        String name = DataHelper.getRussianFullName();
        ApplicationPage page = new ApplicationPage(driver);
        page    .fillNameField(name)
                .checkAgreement()
                .clickContinue();
        Assertions.assertEquals(DataHelper.getEmptyInputSubText(), page.getTextFromPhoneSub());
    }


}
