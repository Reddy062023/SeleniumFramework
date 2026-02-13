package com.selenium.practice.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set Chrome options for CI/CD
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");        // run in headless mode
        options.addArguments("--no-sandbox");          // required for Linux CI
        options.addArguments("--disable-dev-shm-usage");// overcome /dev/shm size limit
        options.addArguments("--disable-gpu");         // optional
        options.addArguments("--window-size=1920,1080"); // optional: ensures elements are visible

        // Initialize Chrome driver with options
        driver = new ChromeDriver(options);

        // Maximize browser window (optional)
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
