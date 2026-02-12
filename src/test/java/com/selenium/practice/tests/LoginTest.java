package com.selenium.practice.tests;

import java.util.List;

import org.testng.annotations.Test;

import com.selenium.practice.base.BaseTest;
import com.selenium.practice.pages.LoginPage;
import com.selenium.practice.utils.CSVReader;
import com.selenium.practice.utils.ConfigReader;

public class LoginTest extends BaseTest {

    @Test
    public void loginWithMultipleUsers() {

        // Pass the timeout from config
        LoginPage loginPage = new LoginPage(driver, Integer.parseInt(ConfigReader.getProperty("timeout")));

        // Get the correct URL from config
        String url = ConfigReader.getProperty("saucedemoUrl");

        // Read users from CSV
        List<String[]> users = CSVReader.readCSV("users.csv");

        for (String[] user : users) {
            String username = user[0];
            String password = user[1];

            // Open the login page with the correct URL
            loginPage.openLoginPage(url);
            loginPage.login(username, password);

            System.out.println("Attempted login with: " + username);
        }
    }
}
