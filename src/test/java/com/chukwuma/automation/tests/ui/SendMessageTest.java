package com.chukwuma.automation.tests.ui;

import java.util.Map;

import org.testng.annotations.Test;

import com.chukwuma.automation.base.BaseUiTest;
import com.chukwuma.automation.page.BookingHomePage;
import com.chukwuma.automation.utils.TestDataGenerator;

public class SendMessageTest extends BaseUiTest {
    @Test(groups = { "ui", "regression" })
    public void sendAMessage() {

        BookingHomePage homePage = new BookingHomePage();
        Map<String, String> messageData = TestDataGenerator.messagePayload();

        homePage.goToHomePage(driver);

        homePage.fillContactForm(driver, messageData);
        homePage.clickSendMessage(driver);

        homePage.confirmMessageSent(driver, messageData.get("name"));

    }
}
