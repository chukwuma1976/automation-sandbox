package com.chukwuma.automation.tests.api;

import org.testng.annotations.Test;

import com.chukwuma.automation.base.BaseApiTest;
import com.chukwuma.automation.config.ConfigReader;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class RoomsApiTest extends BaseApiTest {
    private String API_URL = ConfigReader.get("BASE_API_URL_2");

    @Test
    public void getRooms() {
        given()
                .baseUri(API_URL)
                .when()
                .get("/api/room")
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Test
    public void getRoomById() {
        given()
                .baseUri(API_URL)
                .pathParam("id", 1)
                .when()
                .get("/api/room/{id}")
                .then()
                .statusCode(200)
                .assertThat().body(matchesJsonSchemaInClasspath("room-schema.json"));
    }

    @Test
    public void getRoomByInvalidId() {
        given()
                .baseUri(API_URL)
                .pathParam("id", 99999) // Assuming this ID does not exist
                .when()
                .get("/api/room/{id}")
                .then()
                .statusCode(500); // The API returns 500 for non-existent room IDs, which is not ideal but we are
                                  // testing based on the current behavior
    }
}
