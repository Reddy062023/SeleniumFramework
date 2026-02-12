package com.selenium.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    // Open SauceDemo login page
    public void openLoginPage(String url) {
        driver.get(url);
    }

    // Login method with explicit waits
    public void login(String username, String password) {
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));

        userField.clear();
        userField.sendKeys(username);

        passField.clear();
        passField.sendKeys(password);

        loginBtn.click();
    }
}
