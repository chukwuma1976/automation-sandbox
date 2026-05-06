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
}
