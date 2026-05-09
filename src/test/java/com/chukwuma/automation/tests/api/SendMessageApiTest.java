package com.chukwuma.automation.tests.api;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import com.chukwuma.automation.base.BaseApiTest;
import com.chukwuma.automation.config.ConfigReader;
import com.chukwuma.automation.utils.TestDataGenerator;

import static org.hamcrest.Matchers.equalTo;

public class SendMessageApiTest extends BaseApiTest {
    private String API_URL = ConfigReader.get("BASE_API_URL_2");

    @Test
    public void sendAMessage() {

        Map<String, String> messagePayload = TestDataGenerator.messagePayload();

        given()
                .baseUri(API_URL)
                .headers("Content-Type", "application/json")
                .body(messagePayload)
                .post("/api/message")
                .then()
                .statusCode(200)
                .body("success", equalTo(true));

    }
}
