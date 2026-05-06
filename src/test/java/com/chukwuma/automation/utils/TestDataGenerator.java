package com.chukwuma.automation.utils;

import com.chukwuma.automation.models.Booking;
import com.chukwuma.automation.models.BookingDates;

public class TestDataGenerator {

    public static Booking createBooking() {
        Booking booking = new Booking();
        booking.setFirstname("James" + System.currentTimeMillis());
        booking.setLastname("Brown" + System.currentTimeMillis());
        booking.setTotalprice(150);
        booking.setDepositpaid(true);

        BookingDates dates = new BookingDates();
        dates.setCheckin("2024-01-01");
        dates.setCheckout("2024-01-10");
        booking.setBookingdates(dates);

        booking.setAdditionalneeds("Breakfast");

        return booking;
    }

}
