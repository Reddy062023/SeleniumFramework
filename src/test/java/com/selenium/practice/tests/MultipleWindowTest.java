package com.selenium.practice.tests;

import com.selenium.practice.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class MultipleWindowTest extends BaseTest {

    @Test
    public void testMultipleWindows() {

        driver.get("https://demoqa.com/browser-windows");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement newTabBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("tabButton"))
        );

        // Scroll + click safely
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", newTabBtn);
        wait.until(ExpectedConditions.elementToBeClickable(newTabBtn)).click();

        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        System.out.println("New tab title: " + driver.getTitle());

        driver.close();
        driver.switchTo().window(originalWindow);
    }
}
