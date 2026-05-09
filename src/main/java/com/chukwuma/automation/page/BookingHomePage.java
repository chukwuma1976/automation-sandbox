package com.chukwuma.automation.page;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.chukwuma.automation.config.ConfigReader;
import com.chukwuma.automation.utils.PageActions;

public class BookingHomePage {
    private String BASE_URL = ConfigReader.get("BASE_UI_URL");
    private By adminLoginButton = By.cssSelector("a[href='/admin']");

    private By mainBookNowButton = By.cssSelector("a[href='#booking']");
    private By checkAvailability = By.xpath("//button[contains(text(), 'Check Availability')]");
    private By firstRoomBookNow = By.xpath("//a[contains(@href, 'reservation/1')]");

    private By nameField = By.id("name");
    private By emailField = By.id("email");
    private By phoneField = By.id("phone");
    private By subjectField = By.id("subject");
    private By messageText = By.id("description");
    private By sendMessageButton = By.xpath("//button[contains(text(), 'Submit')]");

    private By messageSentConfirmation = By.xpath("//h3[contains(text(), 'Thanks for getting in touch')]");

    public void goToHomePage(WebDriver driver) {
        driver.get(BASE_URL);
    }

    public void clickAdminLink(WebDriver driver) {
        PageActions.clickElement(driver, adminLoginButton);
    }

    public void clickMainBookingButton(WebDriver driver) {
        PageActions.clickElement(driver, mainBookNowButton);
    }

    public void clickCheckAvailability(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(checkAvailability));
    }

    public void clickFirstRoomBookNow(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(firstRoomBookNow));
    }

    public void fillContactForm(WebDriver driver, Map<String, String> messageData) {
        PageActions.waitForElement(driver, nameField, 10);
        driver.findElement(nameField).sendKeys(messageData.get("name"));
        driver.findElement(emailField).sendKeys(messageData.get("email"));
        driver.findElement(phoneField).sendKeys(messageData.get("phone"));
        driver.findElement(subjectField).sendKeys(messageData.get("subject"));
        driver.findElement(messageText).sendKeys(messageData.get("description"));
    }

    public void clickSendMessage(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(sendMessageButton));
    }

    public void confirmMessageSent(WebDriver driver, String name) {
        PageActions.waitForElement(driver, messageSentConfirmation, 10);
        assert (driver.findElement(messageSentConfirmation).isDisplayed());
        assert (driver.findElement(messageSentConfirmation).getText().contains(name));
    }

}
