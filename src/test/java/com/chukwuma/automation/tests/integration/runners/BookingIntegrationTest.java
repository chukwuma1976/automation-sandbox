package com.chukwuma.automation.tests.integration.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/booking.feature", glue = "com.chukwuma.automation.tests.integration.stepdefinitions", plugin = {
        "pretty", "html:target/cucumber-reports.html" }, monochrome = true)

public class BookingIntegrationTest extends AbstractTestNGCucumberTests {

}
