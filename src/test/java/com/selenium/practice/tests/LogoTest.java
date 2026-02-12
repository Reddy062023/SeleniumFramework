package com.selenium.practice.tests;

import com.selenium.practice.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LogoTest extends BaseTest {

    @Test
    public void verifyLogoDisplayedAndCorrect() {

        driver.get("https://www.selenium.dev/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        By logoLocator = By.cssSelector("a.navbar-brand");

        WebElement logo = wait.until(
                ExpectedConditions.visibilityOfElementLocated(logoLocator)
        );

        Assert.assertTrue(logo.isDisplayed(), "Logo is NOT displayed");

        System.out.println("Logo found successfully");
    }
}
