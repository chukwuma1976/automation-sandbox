package com.chukwuma.automation.tests.api;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.chukwuma.automation.config.ConfigReader;

public class HealthCheckApiTest {
    String BASE_API = ConfigReader.get("BASE_API_URL");

    @Test(groups = { "api", "smoke" })
    public void testHealthCheck() {
        given()
                .baseUri(BASE_API)
                .when().get("/ping")
                .then()
                .statusCode(201);
        System.out.println("Health check test executed successfully.");
    }
}
