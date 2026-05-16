package com.chukwuma.automation.tests.ui;

import org.testng.annotations.Test;

import com.chukwuma.automation.base.BaseUiTest;
import com.chukwuma.automation.config.ConfigReader;
import com.chukwuma.automation.page.PortfolioPage;

public class PortfolioTest extends BaseUiTest {
    String UI_URL = ConfigReader.get("PORTFOLIO_URL");

    @Test(groups = { "ui", "regression" })
    public void testNavigationBar() {
        new PortfolioPage().testAllNavLinks(driver);
    }

    @Test(groups = { "ui", "regression" })
    public void openSdetAutomationFramework() {
        new PortfolioPage().openSdetAutomationFramework(driver);
    }

    @Test(groups = { "ui", "regression" })
    public void openTestForgeAi() {
        new PortfolioPage().openTestForgeAi(driver);
    }

    @Test(groups = { "ui", "regression" })
    public void openSdetAutomationFrameworkDemo() {
        new PortfolioPage().openSdetAutomationFrameworkDemo(driver);
    }

    @Test(groups = { "ui", "regression" })
    public void openTestForgeAiDemo() {
        new PortfolioPage().openTestForgeAiDemo(driver);
    }

    @Test(groups = { "ui", "regression" })
    public void openBlogPost() {
        new PortfolioPage().openBlogPost(driver);
    }
}
