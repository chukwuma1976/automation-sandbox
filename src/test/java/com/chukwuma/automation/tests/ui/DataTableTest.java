package com.chukwuma.automation.tests.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.chukwuma.automation.base.BaseUiTest;
import com.chukwuma.automation.config.ConfigReader;

public class DataTableTest extends BaseUiTest {
    String UI_URL = ConfigReader.get("SELECTORS_HUB_BASE_URL") + "/xpath-practice-page";

    @Test(groups = { "ui", "regression" })
    public void clickHeaderRowCheckbox() {

        driver.get(UI_URL);
        WebElement headerCheckbox = driver.findElement(By.id("ohrmList_chkSelectAll"));

        if (!headerCheckbox.isSelected()) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].click()", headerCheckbox);
        }

    }

    @Test(groups = { "ui", "regression" })
    public void inspectTableHeader() {
        driver.get(UI_URL);
        WebElement tableHeader = driver.findElement(By.cssSelector("table#resultTable thead tr"));
        String headerText = tableHeader.getText();

        String[] headers = { "Username", "User Role", "Employee Name", "Status" };
        for (String header : headers) {
            assert headerText.contains(header) : "Header '" + header + "' is not present in the table header.";
        }
    }
}
