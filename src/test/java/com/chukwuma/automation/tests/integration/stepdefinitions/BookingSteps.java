package com.chukwuma.automation.tests.integration.stepdefinitions;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import com.chukwuma.automation.config.ConfigReader;
import com.chukwuma.automation.models.Booking;
import com.chukwuma.automation.utils.TestDataGenerator;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;

public class BookingSteps {
    private String API_URL = ConfigReader.get("BASE_API_URL");
    private int createdBookingId;
    private String token;
    private Response response;
    private Booking bookingPayload;

    @Given("I have a booking request payload")
    public void createPayload() {
        bookingPayload = TestDataGenerator.createBooking();
    }

    @When("I send a POST request to create a booking")
    public void createBooking() {
        response = given()
                .baseUri(API_URL)
                .headers("Content-Type", "application/json")
                .body(bookingPayload)
                .when()
                .post("/booking");
    }

    @Then("the status code should be 200 with valid response")
    public void verifyResponseAfterCreation() {
        response.then()
                .statusCode(200)
                .body("bookingid", notNullValue())
                .assertThat().body(matchesJsonSchemaInClasspath("created-booking-schema.json"))
                .extract().response();
    }

    @And("a booking ID should be generated")
    public void generateBookingId() {
        createdBookingId = response.jsonPath().getInt("bookingid");
    }

    @When("I update the payload")
    public void updatePayload() {
        bookingPayload.setFirstname("updatedFirstName");
        bookingPayload.setLastname("updatedLastName");
    }

    @And("send a PUT request to update the booking")
    public void updateBooking() {
        token = extractToken();
        response = given()
                .baseUri(API_URL)
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .body(bookingPayload)
                .when()
                .put("/booking/{id}", createdBookingId);
    }

    @Then("the booking details should match the updated payload")
    public void verifyUpdatedBooking() {
        response.then()
                .statusCode(200)
                .body("firstname", equalTo("updatedFirstName"))
                .body("lastname", equalTo("updatedLastName"));
    }

    @And("should be able to retrieve updated booking request")
    public void retrieveUpdatedBooking() {
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

    @Then("delete booking request")
    public void deleteBooking() {
        token = extractToken();
        given()
                .baseUri(API_URL)
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .pathParam("id", createdBookingId)
                .when()
                .delete("/booking/{id}")
                .then()
                .statusCode(201);
    }

    @Then("should be able to retrieve created booking request")
    public void retrieveCreatedBooking() {
        given()
                .baseUri(API_URL)
                .pathParam("id", createdBookingId)
                .when()
                .get("/booking/{id}")
                .then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchemaInClasspath("reservation-schema.json"));
    }

    @And("should not be able to retrieve deleted booking request")
    public void retrieveDeletedBooking() {
        given()
                .baseUri(API_URL)
                .pathParam("id", createdBookingId)
                .when()
                .get("/booking/{id}")
                .then()
                .statusCode(404);
    }

    private String extractToken() {
        Response token = given()
                .baseUri(API_URL)
                .headers("Content-Type", "application/json")
                .body("{ \"username\": \"admin\", \"password\": \"password123\" }")
                .post("/auth");

        return token.jsonPath().get("token");
    }

}
