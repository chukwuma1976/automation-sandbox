package com.chukwuma.automation.tests.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test;

import com.chukwuma.automation.base.BaseApiTest;
import com.chukwuma.automation.config.ConfigReader;
import com.chukwuma.automation.models.Booking;
import com.chukwuma.automation.utils.TestDataGenerator;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class BookingApiTest extends BaseApiTest {
        private String API_URL = ConfigReader.get("BASE_API_URL");

        @Test(groups = { "api", "smoke" })
        void getBookingIds() {
                given()
                                .baseUri(API_URL)
                                .when()
                                .get("/booking")
                                .then()
                                .statusCode(200)
                                .extract().response();
        }

        @Test(groups = { "api", "regression" })
        public void getBookingById() {
                int bookingId = extractBookingId(); // Get an existing booking ID for testing
                given()
                                .baseUri(API_URL)
                                .pathParam("id", bookingId)
                                .when()
                                .get("/booking/{id}")
                                .then()
                                .statusCode(200)
                                .assertThat().body(matchesJsonSchemaInClasspath("reservation-schema.json"));
        }

        @Test(groups = { "api", "regression" })
        public void getBookingByInvalidId() {
                given()
                                .baseUri(API_URL)
                                .pathParam("id", 99999) // Assuming this ID does not exist
                                .when()
                                .get("/booking/{id}")
                                .then()
                                .statusCode(404);
        }

        @Test(groups = { "api", "smoke" })
        public void createBooking() {
                Booking bookingPayload = TestDataGenerator.createBooking();
                Response response = given()
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

                int bookingId = response.jsonPath().getInt("bookingid"); // Extract the created booking ID
                String token = extractToken(); // Extract token for cleanup
                System.out.println("Response time is " + response.time()); // Checking response time

                // Cleanup: Delete the created booking and test the delete endpoint
                deleteBooking(bookingId, token);

        }

        @Test(groups = { "api", "regression" })
        public void updateBooking() {
                // First, create a booking to update
                Booking bookingPayload = TestDataGenerator.createBooking();
                String token = extractToken();

                Response response = given()
                                .baseUri(API_URL)
                                .headers("Content-Type", "application/json")
                                .body(bookingPayload)
                                .when()
                                .post("/booking");

                // Update the booking details
                bookingPayload.setFirstname("updatedFirstName");
                bookingPayload.setLastname("updatedLastName");

                // Send the update request and validate the response
                int bookingId = response.jsonPath().getInt("bookingid");
                given()
                                .baseUri(API_URL)
                                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                                .body(bookingPayload)
                                .when()
                                .put("/booking/{id}", bookingId)
                                .then()
                                .statusCode(200)
                                .body("firstname", equalTo("updatedFirstName"))
                                .body("lastname", equalTo("updatedLastName"));

                // Cleanup: Delete the created booking
                deleteBooking(bookingId, token);
        }

        @Test(groups = { "api", "regression" })
        public void partiallyUpdateBooking() {
                // First, create a booking to update
                Booking bookingPayload = TestDataGenerator.createBooking();
                String token = extractToken();

                Response response = given()
                                .baseUri(API_URL)
                                .headers("Content-Type", "application/json")
                                .body(bookingPayload)
                                .when()
                                .post("/booking");

                // Partially update the booking details
                String updatedFirstName = "partiallyUpdatedFirstName";
                given()
                                .baseUri(API_URL)
                                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                                .body("{ \"firstname\": \"" + updatedFirstName + "\" }")
                                .when()
                                .patch("/booking/{id}", response.jsonPath().getInt("bookingid"))
                                .then()
                                .statusCode(200)
                                .body("firstname", equalTo(updatedFirstName));

                // Cleanup: Delete the created booking
                deleteBooking(response.jsonPath().getInt("bookingid"), token);
        }

        private int extractBookingId() {
                Response bookingIds = given()
                                .baseUri(API_URL)
                                .when()
                                .get("/booking");

                return bookingIds.jsonPath().getInt("[0].bookingid");
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

}
