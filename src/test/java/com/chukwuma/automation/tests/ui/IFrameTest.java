package com.chukwuma.automation.tests.ui;

import org.testng.annotations.Test;

import com.chukwuma.automation.base.BaseUiTest;
import com.chukwuma.automation.config.ConfigReader;
import com.chukwuma.automation.page.IFramePage;

public class IFrameTest extends BaseUiTest {
    String UI_URL = ConfigReader.get("SELECTORS_HUB_BASE_URL") + "/iframe-scenario/";

    @Test(groups = { "ui", "regression" })
    public void testIFrameInteraction() {

        IFramePage iFramePage = new IFramePage();
        iFramePage.navigateToIFramePage(driver);

        // Switch to the iframe using its name or ID
        iFramePage.switchToIFrameById(driver, "pact1");

        iFramePage.enterInput(driver, "inp_val", "Selenium");

        // Switch to nested iframe
        iFramePage.switchToIFrameById(driver, "pact2");

        iFramePage.enterInput(driver, "jex", "Playwright");

        // Swich to an even deeper nested iframe;
        iFramePage.switchToIFrameById(driver, "pact3");

        iFramePage.enterInput(driver, "glaf", "SDET");

        // Switch back to the main content
        driver.switchTo().defaultContent();
    }

}
