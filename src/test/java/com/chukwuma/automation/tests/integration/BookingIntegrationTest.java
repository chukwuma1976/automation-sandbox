package com.chukwuma.automation.tests.integration;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.chukwuma.automation.config.ConfigReader;
import com.chukwuma.automation.models.Booking;
import com.chukwuma.automation.utils.TestDataGenerator;

import io.restassured.response.Response;

public class BookingIntegrationTest {
    private String API_URL = ConfigReader.get("BASE_API_URL");
    private int createdBookingId;
    private String token;

    @BeforeMethod
    public void setup() {
        // Create a token
        token = extractToken();
    }

    @AfterMethod
    public void cleanup() {
        // Implement cleanup logic to delete any test data created during the tests
        deleteBooking(createdBookingId, token);
    }

    @Test
    public void testBookingCreationAndRetrieval() {
        // Create a booking using the API
        Booking bookingPayload = TestDataGenerator.createBooking();
        Response response = createBookingForTest(bookingPayload);

        int createdBookingId = response.jsonPath().getInt("bookingid"); // Extract the created booking ID
        // Retrieve the booking using the API
        given()
                .baseUri(API_URL)
                .pathParam("id", createdBookingId)
                .when()
                .get("/booking/{id}")
                .then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchemaInClasspath("reservation-schema.json"));
    }

    @Test
    public void testBookingUpdate() {
        // Create a booking
        Booking bookingPayload = TestDataGenerator.createBooking();
        Response response = createBookingForTest(bookingPayload);

        createdBookingId = response.jsonPath().getInt("bookingid"); // Extract the created booking ID
        bookingPayload.setFirstname("updatedFirstName");
        bookingPayload.setLastname("updatedLastName");
        token = extractToken();

        // Update the booking using the API
        given()
                .baseUri(API_URL)
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .body(bookingPayload)
                .when()
                .put("/booking/{id}", createdBookingId)
                .then()
                .statusCode(200)
                .body("firstname", equalTo("updatedFirstName"))
                .body("lastname", equalTo("updatedLastName"));

        // Retrieve the booking again to verify the update
        given()
                .baseUri(API_URL)
                .pathParam("id", createdBookingId)
                .when()
                .get("/booking/{id}")
                .then()
                .statusCode(200)
                .body("firstname", equalTo("updatedFirstName"))
                .body("lastname", equalTo("updatedLastName"))
                .assertThat().body(matchesJsonSchemaInClasspath("reservation-schema.json"));
    }

    private String extractToken() {
        Response token = given()
                .baseUri(API_URL)
                .headers("Content-Type", "application/json")
                .body("{ \"username\": \"admin\", \"password\": \"password123\" }")
                .post("/auth");

        return token.jsonPath().get("token");
    }

    private void deleteBooking(int bookingId, String token) {
        given()
                .baseUri(API_URL)
                .headers("Cookie", "token=" + token)
                .pathParam("id", bookingId)
                .when()
                .delete("/booking/{id}")
                .then()
                .statusCode(201);
    }

    private Response createBookingForTest(Booking bookingPayload) {
        return given()
                .baseUri(API_URL)
                .headers("Content-Type", "application/json")
                .body(bookingPayload)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .body("bookingid", notNullValue())
                .assertThat().body(matchesJsonSchemaInClasspath("created-booking-schema.json"))
                .extract().response();
    }

}
