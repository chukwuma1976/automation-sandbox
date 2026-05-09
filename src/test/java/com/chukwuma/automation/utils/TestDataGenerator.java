package com.chukwuma.automation.utils;

import java.util.HashMap;
import java.util.Map;

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

    public static Map<String, String> messagePayload() {
        Map<String, String> messagePayload = new HashMap<>();

        messagePayload.put("name", "Paul Uzoma");
        messagePayload.put("email", "paul.uzoma@gmail.com");
        messagePayload.put("phone", "(800) 123-4567");
        messagePayload.put("subject", "Thank you");
        messagePayload.put("description", "Thank you for allowing me to do API testing using RestAssured");

        return messagePayload;
    }

}
