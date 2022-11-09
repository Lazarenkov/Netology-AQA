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
    static void configureWebDriver() {
        WebDriverManager.chromedriver().setup(); //TODO - enable before committing
//        System.setProperty("webdriver.chrome.driver", "./WebDriver/chromedriver.exe"); //TODO - disable before committing
    }

    @BeforeEach
    void startBrowser() {
//        driver = new ChromeDriver(); //TODO - disable before committing

        ChromeOptions options = new ChromeOptions(); //TODO - enable before committing
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options); //TODO

        driver.get("http://localhost:7777");
    }

    @AfterEach
    void quitBrowser() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldCreateOrderWhenAllValidValues() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("������ ����������");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
        String expected = "���� ������ ������� ����������! ��� �������� �������� � ���� � ��������� �����.";
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenAllEmptyValues() {
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id='name']")).findElement(By.className("input__sub")).getText().trim();
        String expected = "���� ����������� ��� ����������";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCreateOrderWhenAllValidNeedsTrim() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys(" ������ ����������  ");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("  +79029873631 ");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
        String expected = "���� ������ ������� ����������! ��� �������� �������� � ���� � ��������� �����.";
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCreateOrderWhenAllValidDoubleName() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("��� ����� ����������");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
        String expected = "���� ������ ������� ����������! ��� �������� �������� � ���� � ��������� �����.";
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCreateOrderWhenAllValidDoubleSurname() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("������ ��������-������");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
        String expected = "���� ������ ������� ����������! ��� �������� �������� � ���� � ��������� �����.";
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCreateOrderWhenAllValidDoubleNameAndSurname() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("��� ����� ��������-������");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
        String expected = "���� ������ ������� ����������! ��� �������� �������� � ���� � ��������� �����.";
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

//    @Test
//    void shouldCreateOrderWhenValidNameWithYO() {//���� ������ �.�. ������ - ����� ����������� ��� � ������ � � ��������
//        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Ը��� �����");
//        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
//        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
//        driver.findElement(By.cssSelector("button")).click();
//        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
//        String expected = "���� ������ ������� ����������! ��� �������� �������� � ���� � ��������� �����.";
//        System.out.println(actual);
//        Assertions.assertEquals(expected, actual);
//    }

    @Test
    void shouldPrintPromptWhenInvalidEnglishName() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Andrew");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id='name']")).findElement(By.className("input__sub")).getText().trim();
        String expected = "��� � ������� �������� �������. ��������� ������ ������� �����, ������� � ������.";
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenInvalidNameWithChars() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("������!");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id='name']")).findElement(By.className("input__sub")).getText().trim();
        String expected = "��� � ������� �������� �������. ��������� ������ ������� �����, ������� � ������.";
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

//    @Test
//    void shouldPrintPromptWhenInvalidNameOfOneWord() { //���� ������ �.�. ������ - �� ����� ���� ��� �� ������ ����� � ��������
//        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("������");
//        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
//        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
//        driver.findElement(By.cssSelector("button")).click();
//        String actual = driver.findElement(By.cssSelector("[data-test-id='name']")).findElement(By.className("input__sub")).getText().trim();
//        String expected = "��� � ������� �������� �������. ��������� ������ ������� �����, ������� � ������.";
//        System.out.println(actual);
//        Assertions.assertEquals(expected, actual);
//    }

//    @Test
//    void shouldPrintPromptWhenInvalidNameOfOneLetter() { //���� ������ �.�. ������ - �� ����� ���� ��� �� ����� ����� � ��������
//        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("�");
//        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
//        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
//        driver.findElement(By.cssSelector("button")).click();
//        String actual = driver.findElement(By.cssSelector("[data-test-id='name']")).findElement(By.className("input__sub")).getText().trim();
//        String expected = "��� � ������� �������� �������. ��������� ������ ������� �����, ������� � ������.";
//        System.out.println(actual);
//        Assertions.assertEquals(expected, actual);
//    }

    @Test
    void shouldPrintPromptWhenInvalidNameOfOneChar() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("?");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id='name']")).findElement(By.className("input__sub")).getText().trim();
        String expected = "��� � ������� �������� �������. ��������� ������ ������� �����, ������� � ������.";
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenInvalidEmptyName() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id='name']")).findElement(By.className("input__sub")).getText().trim();
        String expected = "���� ����������� ��� ����������";
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenInvalidSpaceName() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys(" ");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79029873631");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id='name']")).findElement(By.className("input__sub")).getText().trim();
        String expected = "���� ����������� ��� ����������";
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }






    @Test
    void shouldPrintPromptWhenEmptyPhone() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("������ ����������");

        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone']")).findElement(By.className("input__sub")).getText().trim();
        String expected = "���� ����������� ��� ����������";
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenPhoneWithoutPlus() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("������ ����������");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("89109112222");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone']")).findElement(By.className("input__sub")).getText().trim();
        String expected = "������� ������ �������. ������ ���� 11 ����, ��������, +79012345678.";
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenPhoneWithOneDigitAndPlus() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("������ ����������");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+7");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone']")).findElement(By.className("input__sub")).getText().trim();
        String expected = "������� ������ �������. ������ ���� 11 ����, ��������, +79012345678.";
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenPhoneWithOneDigitAndNoPlus() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("������ ����������");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("7");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone']")).findElement(By.className("input__sub")).getText().trim();
        String expected = "������� ������ �������. ������ ���� 11 ����, ��������, +79012345678.";
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenPhoneWithTenDigitAndPlus() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("������ ����������");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+7910911121");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone']")).findElement(By.className("input__sub")).getText().trim();
        String expected = "������� ������ �������. ������ ���� 11 ����, ��������, +79012345678.";
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenPhoneWithTenDigitAndNoPlus() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("������ ����������");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("8910911121");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone']")).findElement(By.className("input__sub")).getText().trim();
        String expected = "������� ������ �������. ������ ���� 11 ����, ��������, +79012345678.";
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenPhoneWithTwelveDigitAndPlus() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("������ ����������");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+791091112123");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone']")).findElement(By.className("input__sub")).getText().trim();
        String expected = "������� ������ �������. ������ ���� 11 ����, ��������, +79012345678.";
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldPrintPromptWhenPhoneWithTwelveDigitAndNoPlus() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("������ ����������");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("891091112123");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone']")).findElement(By.className("input__sub")).getText().trim();
        String expected = "������� ������ �������. ������ ���� 11 ����, ��������, +79012345678.";
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }


}



