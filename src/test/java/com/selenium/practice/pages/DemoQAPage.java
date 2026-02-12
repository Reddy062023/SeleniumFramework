package com.selenium.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DemoQAPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Logo locator — stable using alt attribute
    private By logo = By.xpath("//img[@alt='ToolsQA']");

    public DemoQAPage(WebDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    // Open DemoQA homepage
    public void openHomePage(String url) {
        driver.get(url);
    }

    // Check if logo is displayed
    public boolean isLogoDisplayed() {
        WebElement logoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(logo));
        return logoElement.isDisplayed();
    }
}
