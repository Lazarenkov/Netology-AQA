package test;

import data.DataHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.ApplicationPage;

import java.io.IOException;

public class CardOrderTest {

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
    void shouldCreateOrderWhenAllValidValues() {
        String name = DataHelper.getRussianFullName();
        String phone = DataHelper.getPhoneNumber("+7", 10);
        ApplicationPage page = new ApplicationPage(driver);
        page
                .fillNameField(name)
                .fillPhoneField(phone)
                .checkAgreement()
                .clickContinue();
        Assertions.assertEquals(DataHelper.getSuccessNotificationText(), page.getTextFromSuccessNotification());
    }
}
