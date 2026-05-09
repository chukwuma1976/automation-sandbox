package com.chukwuma.automation.tests.ui;

import org.testng.annotations.Test;

import com.chukwuma.automation.base.BaseUiTest;
import com.chukwuma.automation.page.AdminPage;
import com.chukwuma.automation.page.BookingHomePage;

public class LoginTest extends BaseUiTest {

    @Test(groups = { "ui", "smoke" })
    public void login() {
        BookingHomePage homePage = new BookingHomePage();
        AdminPage adminPage = new AdminPage();

        homePage.goToHomePage(driver);
        homePage.clickAdminLink(driver);

        adminPage.login(driver, "admin", "password123");
        // please note that this is not a real login test, as the application does not
        // have a real authentication mechanism. This is just a demonstration of how to
        // structure a UI test.
    }
}