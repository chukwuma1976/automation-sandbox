package com.chukwuma.automation.tests.api;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.chukwuma.automation.base.BaseApiTest;
import com.chukwuma.automation.config.ConfigReader;

import static org.hamcrest.Matchers.*;

public class AuthApiTest extends BaseApiTest {
    private String API_URL = ConfigReader.get("BASE_API_URL");

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][] {
                { "admin", "invalidPassword" },
                { "invalidUser", "password123" },
                { "", "" },
                { "admin", "" },
                { "", "password123" }
        };
    }

    @Test(groups = { "api", "smoke" })
    public void login() {
        given()
                .baseUri(API_URL)
                .headers("Content-Type", "application/json")
                .body("{ \"username\": \"admin\", \"password\": \"password123\" }")
                .post("/auth")
                .then()
                .statusCode(200)
                .body("token", notNullValue());
    }

    @Test(dataProvider = "invalidCredentials", groups = { "api", "regression" })
    public void loginWithInvalidCredentials(String username, String password) {
        given()
                .baseUri(API_URL)
                .headers("Content-Type", "application/json")
                .body("{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }")
                .post("/auth")
                .then()
                .statusCode(200) // in this case it is 200 which is npt typical
                .body("reason", equalTo("Bad credentials"));

    }

}
