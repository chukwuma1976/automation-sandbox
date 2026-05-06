package com.chukwuma.automation.tests.api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.chukwuma.automation.base.BaseApiTest;
import com.chukwuma.automation.config.ConfigReader;

import static org.hamcrest.Matchers.equalTo;

public class SendMessageApiTest extends BaseApiTest {
    private String API_URL = ConfigReader.get("BASE_API_URL_2");

    @Test
    public void sendAMessage() {

        Map<String, String> messagePayload = new HashMap<>();

        messagePayload.put("name", "Paul Uzoma");
        messagePayload.put("email", "paul.uzoma@gmail.com");
        messagePayload.put("phone", "(800) 123-4567");
        messagePayload.put("subject", "Thank you");
        messagePayload.put("description", "Thank you for allowing me to do API testing using RestAssured");

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
