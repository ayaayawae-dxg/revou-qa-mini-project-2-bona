package com.testing.api.booking;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class GetBooking {
  private Response response;
  private JSONObject requestParams;
  private RequestSpecification spec;
  private RequestSpecification request;

  @Before
  public void setup() {
    requestParams = new JSONObject();
  }

  @Given("The booking endpoint is {string}")
  public void theBookingEndpointIs(String endpoint) {
    spec = new RequestSpecBuilder().setBaseUri(endpoint).build();
    request = given().spec(spec);
  }

  @Given("a booking exist with the ID {int}")
  public void aBookingExistWithTheID(int id) {
    RequestSpecification requestLocal = given().spec(spec);

    requestLocal
      .pathParams("id", id)
      .when()
      .get("/{id}")
      .then()
      .statusCode(200);
  }

  @When("I send a GET booking request with ID {int}")
  public void iSendAGETBookingRequestWithID(int id) {
    response = request.pathParams("id", id).when().get("/{id}");
  }

  @Then("I should receive a booking detail")
  public void iShouldReceiveABookingDetail() {
    response.then()
      .statusCode(200)
      .body(matchesJsonSchemaInClasspath("schemas/api/bookingSchema.json"));
  }

  @Then("I should receive status code {int}")
  public void iShouldReceiveStatusCode(int statusCode) {
    response.then().statusCode(statusCode);
  }
}
