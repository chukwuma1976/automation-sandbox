package com.chukwuma.automation.page;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.chukwuma.automation.config.ConfigReader;

public class PortfolioPage {
    String UI_URL = ConfigReader.get("PORTFOLIO_URL");

    By sdetProjectLink = By.xpath("//a[contains(@href, 'sdet-enterprise-automation-framework')]");
    By aiProjectLink = By.xpath("//a[contains(@href, 'TestForge-AI')]");
    By sdetProjectDemoLink = By.xpath("//a[contains(@alt, 'automation framework')]");
    By aiProjectDemoLink = By.xpath("//a[contains(@alt, 'Test Forge AI')]");
    By blogLink = By.cssSelector("#blog a");

    public void testAllNavLinks(WebDriver driver) {
        driver.get(UI_URL);

        List<String> links = List.of("chukwumaanyadike-link", "projects-link", "skills-link", "blog-link",
                "contact-link");

        links.forEach(link -> {
            WebElement navigationLink = driver.findElement(By.id(link));
            assert navigationLink.isDisplayed();
            navigationLink.click();
        });

    }

    public void openSdetAutomationFramework(WebDriver driver) {
        openNewWindow(driver, sdetProjectLink);
    }

    public void openTestForgeAi(WebDriver driver) {
        openNewWindow(driver, aiProjectLink);
    }

    public void openSdetAutomationFrameworkDemo(WebDriver driver) {
        openNewWindow(driver, sdetProjectDemoLink);
    }

    public void openTestForgeAiDemo(WebDriver driver) {
        openNewWindow(driver, aiProjectDemoLink);
    }

    public void openBlogPost(WebDriver driver) {
        openNewWindow(driver, blogLink);
    }

    private void openNewWindow(WebDriver driver, By linkLocator) {
        driver.get(UI_URL);

        String firstWindow = driver.getWindowHandle();
        By sdetProjectLink = By.xpath("//a[contains(@href, 'sdet-enterprise-automation-framework')]");
        driver.findElement(sdetProjectLink).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            System.out.println("window: " + window);
            if (!window.equals(firstWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        System.out.println(driver.getTitle());
        driver.close();
        driver.switchTo().window(firstWindow);
    }

}
