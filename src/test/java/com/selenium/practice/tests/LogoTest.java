package com.selenium.practice.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.practice.base.BaseTest;
import com.selenium.practice.pages.DemoQAPage;
import com.selenium.practice.utils.ConfigReader;

public class LogoTest extends BaseTest {

    @Test(enabled = false)  // This will skip the test execution
    public void verifyLogoIsDisplayed() {

        // Pass the timeout from config
        DemoQAPage demoPage = new DemoQAPage(driver, Integer.parseInt(ConfigReader.getProperty("timeout")));

        // Pass the URL from config
        demoPage.openHomePage(ConfigReader.getProperty("demoqaUrl"));

        Assert.assertTrue(demoPage.isLogoDisplayed(), "DemoQA Logo is NOT displayed!");
    }
}
