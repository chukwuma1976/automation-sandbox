package com.chukwuma.automation.tests.ui;

import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.chukwuma.automation.base.BaseUiTest;
import com.chukwuma.automation.page.BookingHomePage;
import com.chukwuma.automation.page.ReservationPage;
import com.chukwuma.automation.utils.TestDataGenerator;

public class BookingTest extends BaseUiTest {
    Map<String, String> bookingData;

    @BeforeTest(alwaysRun = true)
    public void setup() {
        bookingData = TestDataGenerator.createUIBookingData();
    }

    @Test(groups = { "ui", "smoke" })
    public void testBookingWithConfirmation() {
        BookingHomePage homePage = new BookingHomePage();
        ReservationPage reservationPage = new ReservationPage();

        homePage.goToHomePage(driver);

        homePage.clickMainBookingButton(driver);
        homePage.clickCheckAvailability(driver);
        homePage.clickFirstRoomBookNow(driver);

        reservationPage.confirmOnReservationPage(driver);
        reservationPage.clickReserveNow(driver);

        reservationPage.fillReservationForm(driver, bookingData.get("firstname"), bookingData.get("lastname"),
                bookingData.get("email"), bookingData.get("phone"));
        reservationPage.clickConfirmBooking(driver);

        // reservationPage.checkBookingConfirmation(driver);

    }

    @Test(groups = { "ui", "regression" })
    public void testBookingWithCancellation() {
        BookingHomePage homePage = new BookingHomePage();
        ReservationPage reservationPage = new ReservationPage();

        homePage.goToHomePage(driver);

        homePage.clickMainBookingButton(driver);
        homePage.clickCheckAvailability(driver);
        homePage.clickFirstRoomBookNow(driver);

        reservationPage.confirmOnReservationPage(driver);
        reservationPage.clickReserveNow(driver);

        reservationPage.fillReservationForm(driver, bookingData.get("firstname"), bookingData.get("lastname"),
                bookingData.get("email"), bookingData.get("phone"));
        reservationPage.clickCancelBooking(driver);
        reservationPage.confirmOnReservationPage(driver);

    }

}
