package com.chukwuma.automation.tests.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.chukwuma.automation.base.BaseUiTest;
import com.chukwuma.automation.config.ConfigReader;

public class ActionsTest extends BaseUiTest {

    String UI_URL = ConfigReader.get("PRACTICE_AUTOMATION_URL") + "/hover/";

    @Test(groups = { "ui", "regression" })
    public void testHoverAction() {
        driver.get(UI_URL);
        WebElement hoverable = driver.findElement(By.id("mouse_over"));
        Actions actions = new Actions(driver);
        String textBeforeHover = hoverable.getText(); // This is just to ensure the element is interactable before
                                                      // performing the hover
        assert textBeforeHover.equals("Mouse over me") : "Expected the text to be empty before hover, but it is not.";
        actions.moveToElement(hoverable).perform();
        String textAfterHover = hoverable.getText();
        assert textAfterHover.equals("You did it!") : "Expected the text to be 'Hovered!' after hover, but it is not.";
    }

}
