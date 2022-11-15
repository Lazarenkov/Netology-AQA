package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;


public class CardDeliverySmokeTest {

    private String setDateForTest(int todayPlusInt) {
        return LocalDate.now().plusDays(todayPlusInt).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    private static final String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;


    @BeforeEach
    void startBrowser() {
        Configuration.headless = true;
        open("http://localhost:7777/");
    }

    @Test
    void shouldCreateOrderWhenAllValuesPositive() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("������");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(11));
        $("[data-test-id='name'] [name='name']").setValue("������ ����������");
        $("[data-test-id='phone'] [name='phone']").setValue("+79109101122");
        $("[class=checkbox__text]").click();
        $(By.className("button__text")).click();

        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(11));
        String msg = $("[data-test-id=notification]").getText();
        Assertions.assertTrue(msg.contains("�������!"));
    }

    @Test
    void shouldPrintSubWhenAllValuesEmpty() throws InterruptedException {
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $(By.className("button__text")).click();

        String msg = $("[data-test-id='city'] [class='input__sub']").getText().trim();
        Assertions.assertEquals("���� ����������� ��� ����������", msg);
    }

    @Test
    void shouldPrintSubWhenAllValuesNegative() throws InterruptedException {
        $("[data-test-id='city'] [class='input__control']").setValue("��������");
        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
        $("[data-test-id='date'] [class='input__control']").setValue(setDateForTest(-3));
        $("[data-test-id='name'] [name='name']").setValue("�");
        $("[data-test-id='phone'] [name='phone']").setValue("910910112");
        $(By.className("button__text")).click();

        String msg = $("[data-test-id='city'] [class='input__sub']").getText().trim();
        Assertions.assertEquals("�������� � ��������� ����� ����������", msg);
    }

//    @Test
//    void shouldCreateOrderWhenAllValidNeedsTrim(){
//        $("[data-test-id='city'] [class='input__control']" ).setValue("  ������ ");
//        $("[data-test-id='date'] [class='input__control']").setValue(deleteString);
//        $("[data-test-id='date'] [class='input__control']").setValue(" " + setDateForTest(11) + "  ");
//        $("[data-test-id='name'] [name='name']").setValue(" ������ ����������  ");
//        $("[data-test-id='phone'] [name='phone']").setValue("  +79109101122  ");
//        $("[class=checkbox__text]").click();
//        $(By.className("button__text") ).click();
//
//        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(11));
//        String msg = $("[data-test-id=notification]").getText();
//        Assertions.assertTrue(msg.contains("�������!"));
//    }

}
