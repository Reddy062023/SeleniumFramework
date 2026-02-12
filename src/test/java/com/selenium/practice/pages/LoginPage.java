package com.selenium.practice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    
    @FindBy(id = "username")
    WebElement username;
    
    @FindBy(id = "password")
    WebElement password;
    
    @FindBy(css = "button[type='submit']")
    WebElement loginBtn;
    
    @FindBy(id = "flash")
    WebElement flashMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String user, String pass) {
        username.sendKeys(user);
        password.sendKeys(pass);
        loginBtn.click();
    }

    public String getMessageText() {
        // wait up to 10 seconds for flash message to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(flashMessage));
        
        return flashMessage.getText().trim();
    }
}
