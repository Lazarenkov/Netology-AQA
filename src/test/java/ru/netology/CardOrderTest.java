package ru.netology;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class CardOrderTest {

    private WebDriver driver;

    @BeforeAll
    static void configureWebDriver() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    void startBrowser() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("./build/reports/tests/test/"+screenshot.getName()));

        System.setProperty("webdriver.chrome.logfile", "./build/reports/tests/test/chromedriver.log");
        System.setProperty("webdriver.chrome.verboseLogging", "true");

    }

    @AfterEach
    void tearsDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldCreateOrderWhenAllValidValues() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Андрей Лазаренков");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenAllEmptyValues() {
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String actual = driver.findElement(By.cssSelector("[data-test-id='name']")).findElement(By.className("input__sub")).getText().trim();
        String expected = "Поле обязательно для заполнения";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCreateOrderWhenAllValidNeedsTrim() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys(" Андрей Лазаренков  ");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("  +79029873631 ");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCreateOrderWhenAllValidDoubleName() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Жан Клодт Лазаренков");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCreateOrderWhenAllValidDoubleSurname() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Андрей Салтыков-Щедрин");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCreateOrderWhenAllValidDoubleNameAndSurname() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Жан Клодт Салтыков-Щедрин");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCreateOrderWhenValidNameWithYO() {//тест падает т.к. дефект - могут встречаться ФИО с буквой ё в паспорте
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Фёдор Клёвый");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenInvalidEnglishName() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Andrew");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver
                .findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub"))
                .getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenInvalidNameWithChars() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Андрей!");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver
                .findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub"))
                .getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenInvalidNameOfOneWord() { //тест падает т.к. дефект - не может быть ФИО из одного слова в паспорте
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Андрей");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver
                .findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub"))
                .getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenInvalidNameOfOneLetter() { //тест падает т.к. дефект - не может быть ФИО из одной буквы в паспорте
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("А");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver
                .findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub"))
                .getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenInvalidNameOfOneChar() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("?");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver
                .findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub"))
                .getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenInvalidEmptyName() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "Поле обязательно для заполнения";
        String actual = driver
                .findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub"))
                .getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenInvalidSpaceName() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys(" ");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "Поле обязательно для заполнения";
        String actual = driver
                .findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub"))
                .getText().trim();

        Assertions.assertEquals(expected, actual);
    }






    @Test
    void shouldPrintPromptWhenEmptyPhone() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Андрей Лазаренков");

        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "Поле обязательно для заполнения";
        String actual = driver
                .findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"))
                .getText().trim();
        
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenPhoneWithoutPlus() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Андрей Лазаренков");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("89109112222");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver
                .findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"))
                .getText().trim();
        
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenPhoneWithOneDigitAndPlus() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Андрей Лазаренков");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+7");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver
                .findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"))
                .getText().trim();
        
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenPhoneWithOneDigitAndNoPlus() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Андрей Лазаренков");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("7");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver
                .findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"))
                .getText().trim();
        
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenPhoneWithTenDigitAndPlus() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Андрей Лазаренков");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+7910911121");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver
                .findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"))
                .getText().trim();
        
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenPhoneWithTenDigitAndNoPlus() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Андрей Лазаренков");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("8910911121");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver
                .findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"))
                .getText().trim();
        
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenPhoneWithTwelveDigitAndPlus() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Андрей Лазаренков");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+791091112123");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver
                .findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"))
                .getText().trim();
        
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenPhoneWithTwelveDigitAndNoPlus() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Андрей Лазаренков");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("891091112123");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual =  driver
                .findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"))
                .getText().trim();

        Assertions.assertEquals(expected, actual);
    }







    @Test
    void shouldBeInputInvalidWhenAgreementNotChecked(){
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Андрей Лазаренков");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79109111212");
        driver.findElement(By.cssSelector("button")).click();
        assertTrue(driver.findElement(By.cssSelector("[data-test-id='agreement'].input_invalid")).isDisplayed());
    }


}



