package ru.netology;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;
import java.util.Set;

public class CardOrderTest {

    private WebDriver driver;

    @BeforeAll
    static void configureWebDriver(){
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "./WebDriver/chromedriver.exe");
    }

    @BeforeEach
    void startBrowser() {
        //driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void quitBrowser(){
        driver.quit();
        driver = null;
    }

    @Test
    void shouldCreateOrder(){
        driver.get("http://localhost:9999");
    }
}
