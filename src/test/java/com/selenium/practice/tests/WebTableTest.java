package com.selenium.practice.tests;

import com.selenium.practice.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class WebTableTest extends BaseTest {

    @Test
    public void verifyProductInTable() {

        driver.get("https://testautomationpractice.blogspot.com/");

        // Locate table rows (Product table)
        List<WebElement> rows = driver.findElements(
                By.xpath("//table[@name='BookTable']//tr")
        );

        boolean found = false;
        String expectedProduct = "Learn Selenium";   // Product inside table

        for (WebElement row : rows) {

            if (row.getText().contains(expectedProduct)) {
                found = true;
                break;
            }
        }

        Assert.assertTrue(found,
                "Product '" + expectedProduct + "' was not found in the table.");
    }
}
