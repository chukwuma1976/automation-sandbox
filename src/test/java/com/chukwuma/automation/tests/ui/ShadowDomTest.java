package com.chukwuma.automation.tests.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.chukwuma.automation.base.BaseUiTest;
import com.chukwuma.automation.config.ConfigReader;

public class ShadowDomTest extends BaseUiTest {
    String UI_URL = ConfigReader.get("SELECTORS_HUB_BASE_URL") + "/iframe-in-shadow-dom/";

    @Test(groups = { "ui", "regression" })
    public void testShadowDomInteraction() {

        driver.get(UI_URL);

        // Locate the shadow host element and get its shadow root
        WebElement userNameShadowHost = driver.findElement(By.id("userName"));
        SearchContext userNameShadowRoot = userNameShadowHost.getShadowRoot();

        // Interact with elements inside the shadow DOM
        By userName = By.cssSelector("input#kils");
        userNameShadowRoot.findElement(userName).sendKeys("tester name");

        // Switch to the iframe inside the shadow DOM and interact with elements inside
        WebElement iFrame = userNameShadowRoot.findElement(By.cssSelector("#pact1"));
        driver.switchTo().frame(iFrame);
        By input = By.id("jex");
        driver.findElement(input).sendKeys("tester name in iframe");

        // Switch to nested iframe and interact with elements inside
        driver.switchTo().frame("pact3");
        By nestedInput = By.id("glaf");
        driver.findElement(nestedInput).sendKeys("tester name in nested iframe");

        driver.switchTo().defaultContent();

        // Find nested shadow host and interact with elements inside its shadow DOM
        WebElement nestedShadowHost = userNameShadowRoot.findElement(By.cssSelector("#app2"));
        SearchContext nestedShadowRoot = nestedShadowHost.getShadowRoot();
        By pizza = By.cssSelector("input#pizza");
        nestedShadowRoot.findElement(pizza).sendKeys("It's a pizza!");

        // Find another nestedshadow host with closed shadow root, so we cannot interact
        // with elements inside it but we can verify its presence
        WebElement nestedShadowHost2 = userNameShadowRoot.findElement(By.cssSelector("#concepts"));
        SearchContext nestedShadowRoot2 = nestedShadowHost2.getShadowRoot();
        By training = By.cssSelector("input#training");
        nestedShadowRoot2.findElement(training);

        // Another shadow host with shadow root which is closed, so we cannot interact
        // with elements inside it but we can verify its presence
        WebElement passwordShadowHost = driver.findElement(By.cssSelector("#userPass"));
        SearchContext passwordShadowRoot = passwordShadowHost.getShadowRoot();
        By passwordInput = By.cssSelector("input#pwd");
        passwordShadowRoot.findElement(passwordInput);

    }

}
