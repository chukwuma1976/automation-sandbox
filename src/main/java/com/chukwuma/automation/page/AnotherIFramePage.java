package com.chukwuma.automation.page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.chukwuma.automation.config.ConfigReader;

public class AnotherIFramePage {
    String UI_URL = ConfigReader.get("PRACTICE_AUTOMATION_URL") + "/iframes/";

    public void navigateToIFramePage(WebDriver driver) {
        driver.get(UI_URL);
    }

    public void interactWithIFrame1(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.switchTo().frame("iframe-1");
        driver.findElement(By.xpath("//button[contains(@aria-label, 'Search')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#docsearch-input")));
        driver.findElement(By.cssSelector("input#docsearch-input")).sendKeys("frame");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("docsearch-hits0-item-0")));
        driver.findElement(By.id("docsearch-hits0-item-0")).click();
        driver.switchTo().defaultContent();
    }

    public void interactWithIFrame2(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.switchTo().frame("iframe-2");
        WebElement searchButton = driver.findElement(By.xpath("//button[contains(@aria-label, 'Search')]"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", searchButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#docsearch-input")));
        driver.findElement(By.cssSelector("input#docsearch-input")).sendKeys("frame");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("docsearch-item-0")));
        WebElement firstSearchResult = driver.findElement(By.id("docsearch-item-0"));
        jsExecutor.executeScript("arguments[0].click();", firstSearchResult);
        driver.switchTo().defaultContent();
    }

}
