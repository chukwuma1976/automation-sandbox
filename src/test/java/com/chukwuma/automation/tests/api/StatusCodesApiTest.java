package com.chukwuma.automation.tests.api;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.chukwuma.automation.config.ConfigReader;

import io.restassured.response.Response;

public class StatusCodesApiTest {
    private String API_URL = ConfigReader.get("HTTP_BIN_API_URL") + "/status/";

    @DataProvider(name = "statusCodes")
    public Object[] statusCodes() {
        return new Object[] {
                "200",
                "201",
                "202",
                "204",
                // "301",
                // "302",
                "304",
                "400",
                "401",
                "403",
                "404",
                "405",
                "409",
                "415",
                "422",
                "429",
                "500",
                "502",
                "503",
                "504" };
    }

    @Test(dataProvider = "statusCodes", groups = { "api" })
    public void testStatusCodes(String statusCode) {
        Response response = given()
                .baseUri(API_URL)
                .when()
                .get(statusCode)
                .then()
                .statusCode(Integer.parseInt(statusCode))
                .extract().response();
        System.out.println(response.statusLine());
    }

}
