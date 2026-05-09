package com.chukwuma.automation.tests.ui;

import org.testng.annotations.Test;

import com.chukwuma.automation.base.BaseUiTest;
import com.chukwuma.automation.page.AdminPage;
import com.chukwuma.automation.page.BookingHomePage;

public class LoginTest extends BaseUiTest {

    @Test
    public void login() {
        BookingHomePage homePage = new BookingHomePage();
        AdminPage adminPage = new AdminPage();

        homePage.goToHomePage(driver);
        homePage.clickAdminLink(driver);

        adminPage.login(driver, "admin", "password123");
    }
}