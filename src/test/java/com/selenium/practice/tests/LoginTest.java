package com.selenium.practice.tests;

import com.selenium.practice.base.BaseTest;
import com.selenium.practice.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {
        driver.get("https://the-internet.herokuapp.com/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("tomsmith", "SuperSecretPassword!");

        String actualMessage = loginPage.getMessageText();
        System.out.println("Actual success message: >>> " + actualMessage + " <<<");

        String expectedMessage = "You logged into a secure area!";
        Assert.assertTrue(actualMessage.toLowerCase()
                .contains(expectedMessage.toLowerCase()),
                "Success message mismatch.\nActual: " + actualMessage +
                "\nExpected to contain: " + expectedMessage);
    }

    @Test
    public void invalidUsernameTest() {
        driver.get("https://the-internet.herokuapp.com/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid", "invalidPass");

        String actualMessage = loginPage.getMessageText();
        System.out.println("Actual invalid username message: >>> " + actualMessage + " <<<");

        String expectedMessage = "Your username is invalid!";
        Assert.assertTrue(actualMessage.toLowerCase()
                .contains(expectedMessage.toLowerCase()),
                "Invalid username message mismatch.\nActual: " + actualMessage +
                "\nExpected to contain: " + expectedMessage);
    }

    @Test
    public void wrongPasswordTest() {
        driver.get("https://the-internet.herokuapp.com/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("tomsmith", "wrongPass");

        String actualMessage = loginPage.getMessageText();
        System.out.println("Actual wrong password message: >>> " + actualMessage + " <<<");

        String expectedMessage = "Your password is invalid!";
        Assert.assertTrue(actualMessage.toLowerCase()
                .contains(expectedMessage.toLowerCase()),
                "Wrong password message mismatch.\nActual: " + actualMessage +
                "\nExpected to contain: " + expectedMessage);
    }
}
