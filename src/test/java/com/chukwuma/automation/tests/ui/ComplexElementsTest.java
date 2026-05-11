package com.chukwuma.automation.tests.ui;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.chukwuma.automation.base.BaseUiTest;
import com.chukwuma.automation.config.ConfigReader;
import com.chukwuma.automation.utils.TestDataGenerator;

public class ComplexElementsTest extends BaseUiTest {
    String UI_URL = ConfigReader.get("SELECTORS_HUB_BASE_URL") + "/xpath-practice-page";

    @Test(groups = { "ui", "regression" })
    public void testEnabledAndDisabledFormFields() {
        driver.get(UI_URL);

        By enabledInputLocator = By.xpath("//input[@placeholder='First Enter name']");
        WebElement firstNameInput = driver.findElement(enabledInputLocator);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", firstNameInput);
        jsExecutor.executeScript("arguments[0].value = 'Chukwuma';", firstNameInput);

        By disabledInputLocator = By.xpath("//input[@placeholder='Enter Last name']");
        WebElement lastNameInput = driver.findElement(disabledInputLocator);
        assert !lastNameInput.isEnabled() : "Expected the last name input to be disabled, but it is enabled.";
    }

    @Test(groups = { "ui", "regression" })
    public void testHoverableButtonWithDropdown() {
        driver.get(UI_URL);

        By checkOutButtonLocator = By.xpath("//button[text()='Checkout here']");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(checkOutButtonLocator)).perform();

        By testCaseSelection = By.xpath("//a[text()='Try TestCase Studio']");
        WebElement option3 = driver.findElement(testCaseSelection);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", option3);
    }

    @Test(groups = { "ui", "regression" })
    public void testCarsDropdownMenu() {
        driver.get(UI_URL);
        WebElement carsDropDown = driver.findElement(By.id("cars"));
        Select carsSelect = new Select(carsDropDown);

        carsSelect.selectByVisibleText("Saab");
        carsSelect.selectByValue("volvo");
        carsSelect.selectByIndex(2);

    }

    @Test(groups = { "ui", "regression" })
    public void testDatePickerInput() {

        driver.get(UI_URL);

        WebElement datePicker = driver.findElement(By.xpath("//input[@type='date']"));
        datePicker.clear();
        datePicker.sendKeys("08/14/2026");

    }

    @Test(groups = { "ui", "regression" })
    public void testFileDownload() {

        driver.get(UI_URL);

        WebElement downloadBtn = driver.findElement(By.xpath("//a[contains(text(), 'Click to Download')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", downloadBtn);

    }

    @Test(groups = { "ui", "regression" })
    public void testFileUpload() {

        driver.get(UI_URL);

        String fileToSend = TestDataGenerator.getTestFilePath("fileForUpload.txt");

        WebElement fileUpload = driver.findElement(By.id("myFile"));
        fileUpload.sendKeys(fileToSend);
    }

    @Test(groups = { "ui", "regression" })
    public void testAlert() {

        driver.get(UI_URL);

        By buttonLocator = By.xpath("//button[contains(text(), 'Window Alert')]");
        WebElement windowAlertButton = driver.findElement(buttonLocator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", windowAlertButton);

        Alert alert = driver.switchTo().alert();
        alert.accept();

        js.executeScript("arguments[0].click()", windowAlertButton);
        Alert alert2 = driver.switchTo().alert();
        alert2.dismiss();
    }

    @Test(groups = { "ui", "regression" })
    public void testAlertWithTextEntry() {

        driver.get(UI_URL);

        By buttonLocator = By.xpath("//button[contains(text(), 'Window Prompt Alert')]");
        WebElement windowAlertButton = driver.findElement(buttonLocator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", windowAlertButton);

        Alert alert = driver.switchTo().alert();
        alert.sendKeys("No, I am using Selenium to test alert");
        alert.accept();
    }

    @Test(groups = { "ui", "regression" })
    public void testHtmlModal() {

        driver.get(UI_URL);

        WebElement openModalButton = driver.findElement(By.id("myBtn"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", openModalButton);

        WebElement closeButton = driver.findElement(By.cssSelector("#myModal span"));
        js.executeScript("arguments[0].click()", closeButton);

    }

}
