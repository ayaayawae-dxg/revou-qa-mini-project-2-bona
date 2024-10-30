package com.testing.api.booking;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetBookingId {
  private Response response;
  private JSONObject requestParams;
  private RequestSpecification spec;
  private RequestSpecification request;

  @Before
  public void setup() {
    requestParams = new JSONObject();
  }

  @Given("The booking ID endpoint is {string}")
  public void theBookingIDEndpointIs(String endpoint) {
    spec = new RequestSpecBuilder().setBaseUri(endpoint).build();
    request = given().spec(spec);
  }

  @Given("a booking exist with the following details:")
  public void aBookingExistWithTheFollowingDetails(DataTable dataTable) {
    RequestSpecification requestLocal = given().spec(spec);
    Map<String, String> params = dataTable.asMap(String.class, String.class);

    params.forEach(requestLocal::queryParam);

    requestLocal
      .when()
      .get()
      .then()
      .statusCode(200)
      .body("size()", greaterThan(0));
  }

  @When("I send a GET booking request with the following parameters:")
  public void iSendAGETBookingRequestWithTheFollowingParameters(DataTable dataTable) {
    RequestSpecification requestLocal = given().spec(spec);
    Map<String, String> params = dataTable.asMap(String.class, String.class);

    params.forEach(requestLocal::queryParam);

    response = requestLocal.when().get();
  }

  @When("I send a GET request without any parameters")
  public void iSendAGETRequestWithoutAnyParameters() {
    response = request.when().get();
  }

  @Then("I should receive a list of booking ID")
  public void iShouldReceiveAListOfBookingID() {
    response.then()
      .statusCode(200)
      .body("bookingid", everyItem(notNullValue()))
      .body("size()", greaterThan(0));
  }

  @Then("I should receive an empty list of booking ID")
  public void iShouldReceiveAnEmptyListOfBookingID() {
    response.then()
      .statusCode(200)
      .body("", hasSize(0));
  }
}
