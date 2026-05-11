package com.chukwuma.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.chukwuma.automation.config.ConfigReader;

public class IFramePage {

    public void navigateToIFramePage(WebDriver driver) {
        String UI_URL = ConfigReader.get("SELECTORS_HUB_BASE_URL") + "/iframe-scenario/";
        driver.get(UI_URL);
    }

    public void switchToIFrameById(WebDriver driver, String id) {
        driver.switchTo().frame(id);
    }

    public void enterInput(WebDriver driver, String id, String input) {
        By inputField = By.id(id);
        driver.findElement(inputField).sendKeys(input);
    }

}
