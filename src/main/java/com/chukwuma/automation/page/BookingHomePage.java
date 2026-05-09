package com.chukwuma.automation.page;

import java.io.ObjectInputFilter.Config;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.chukwuma.automation.config.ConfigReader;

public class BookingHomePage {
    private WebDriverWait wait;
    private By adminLoginButton = By.cssSelector("a[href='/admin']");
    private String BASE_URL = ConfigReader.get("BASE_UI_URL");

    public void goToHomePage(ChromeDriver driver) {
        driver.get(BASE_URL);
    }

    public void clickAdminLink(ChromeDriver driver) {
        clickElement(driver, adminLoginButton);
    }

    private void clickElement(ChromeDriver driver, By locator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

}
