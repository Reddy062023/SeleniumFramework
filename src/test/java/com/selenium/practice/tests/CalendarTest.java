package com.selenium.practice.tests;

import com.selenium.practice.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class CalendarTest extends BaseTest {

    @Test
    public void selectDateFromCalendar() {

        driver.get("https://demoqa.com/date-picker");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By dateInputLocator = By.id("datePickerMonthYearInput");

        WebElement dateInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(dateInputLocator)
        );

        // Scroll to element (very important for DemoQA)
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", dateInput);

        wait.until(ExpectedConditions.elementToBeClickable(dateInput)).click();

        // Clear existing date
        dateInput.clear();

        // Enter new date directly (simpler than calendar UI handling)
        dateInput.sendKeys("02/20/2026");
    }
}
