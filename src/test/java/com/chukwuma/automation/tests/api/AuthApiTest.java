package com.chukwuma.automation.tests.api;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.chukwuma.automation.base.BaseApiTest;
import com.chukwuma.automation.config.ConfigReader;

import static org.hamcrest.Matchers.notNullValue;

public class AuthApiTest extends BaseApiTest {
    private String API_URL = ConfigReader.get("BASE_API_URL");

    @Test
    void Login() {
        given()
                .baseUri(API_URL)
                .headers("Content-Type", "application/json")
                .body("{ \"username\": \"admin\", \"password\": \"password123\" }")
                .post("/auth")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract().response();
    }

}
