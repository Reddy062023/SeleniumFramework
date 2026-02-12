package com.selenium.practice.tests;

import com.selenium.practice.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class WaitTest extends BaseTest {

    @Test
    public void waitUntilClickable() {

        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        By enableButtonLocator = By.xpath("//button[text()='Enable']");

        WebElement enableButton = wait.until(
                ExpectedConditions.elementToBeClickable(enableButtonLocator)
        );

        enableButton.click();
    }
}
