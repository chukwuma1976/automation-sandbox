package com.chukwuma.automation.tests.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.chukwuma.automation.base.BaseUiTest;
import com.chukwuma.automation.config.ConfigReader;

public class SliderTest extends BaseUiTest {
    String UI_URL = ConfigReader.get("PRACTICE_AUTOMATION_URL") + "/slider/";

    @Test(groups = { "ui", "regression" })
    public void testSliderInteraction() {
        driver.get(UI_URL);
        WebElement slider = driver.findElement(By.id("slideMe"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(slider, 100, 0).perform();
    }
}
