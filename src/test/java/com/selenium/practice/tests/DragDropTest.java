package com.selenium.practice.tests;

import com.selenium.practice.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragDropTest extends BaseTest {

    @Test
    public void dragAndDropElements() {

        driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        WebElement source = driver.findElement(By.id("column-a"));
        WebElement target = driver.findElement(By.id("column-b"));

        Actions actions = new Actions(driver);

        actions.clickAndHold(source)
                .moveToElement(target)
                .release()
                .build()
                .perform();

        // Verify swap happened
        String headerA = driver.findElement(By.cssSelector("#column-a header")).getText();

        Assert.assertTrue(headerA.equals("B"), "Element not dropped correctly");

        System.out.println("Drag and drop successful");
    }
}
