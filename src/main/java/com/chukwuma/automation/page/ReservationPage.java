package com.chukwuma.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.chukwuma.automation.utils.PageActions;

public class ReservationPage {

    private By reserveNowButton = By.id("doReservation");
    private By firstNameField = By.cssSelector("input[aria-label='Firstname']");
    private By lastNameField = By.cssSelector("input[aria-label='Lastname']");
    private By emailField = By.cssSelector("input[aria-label='Email']");
    private By phoneNumberField = By.cssSelector("input[aria-label='Phone']");
    private By confirmBookingButton = By.xpath("//button[contains(text(), 'Reserve Now')]");
    private By cancelBookingButton = By.xpath("//button[contains(text(), 'Cancel')]");
    private By confirmationMessage = By.xpath("//h2[contains(text(), 'Booking Confirmed')]");

    public void clickReserveNow(WebDriver driver) {
        PageActions.waitForElement(driver, reserveNowButton, 10);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(reserveNowButton));
    }

    public void confirmOnReservationPage(WebDriver driver) {
        PageActions.waitForElement(driver, reserveNowButton, 10);
        assert (driver.getCurrentUrl().contains("/reservation"));
    }

    public void fillReservationForm(WebDriver driver, String firstName, String lastName, String email, String phone) {
        PageActions.waitForElement(driver, firstNameField, 10);
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(phoneNumberField).sendKeys(phone);
    }

    public void clickConfirmBooking(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(confirmBookingButton));
    }

    public void clickCancelBooking(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(cancelBookingButton));
    }

    public void checkBookingConfirmation(WebDriver driver) {
        PageActions.waitForElement(driver, confirmationMessage, 10);
        assert (driver.findElement(confirmationMessage).isDisplayed());
    }

}
