package com.testing.api.booking;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.*;

public class DeleteBooking {
  private Response response;
  private JSONObject requestParams;
  private RequestSpecification spec;
  private RequestSpecification request;

  @Before
  public void setup() {
    requestParams = new JSONObject();
  }

  @Given("The delete booking endpoint is {string}")
  public void theDeleteBookingEndpointIs(String endpoint) {
    spec = new RequestSpecBuilder().setContentType("application/json").setBaseUri(endpoint).build();
    request = given().spec(spec);
  }

  @Given("a booking exist with the ID {int} and can be deleted")
  public void aBookingExistWithTheIDAndCanBeDeleted(int id) {
    RequestSpecification requestLocal = given().spec(spec);

    requestLocal
      .pathParams("id", id)
      .when()
      .get("/{id}")
      .then()
      .statusCode(200);
  }

  @And("A valid token {string} is set in the delete booking request header")
  public void aValidTokenIsSetInTheDeleteBookingRequestHeader(String token) {
    request.header("Authorization", "Basic " + token);
  }

  @When("I send a DELETE booking request with ID {int}")
  public void iSendADeleteBookingRequestWithID(int id) {
    response = request.pathParams("id", id).when().delete("/{id}");
  }
  
  @Then("I should receive status code {int} of the deleted booking")
  public void iShouldReceiveStatusCodeOfTheDeletedBooking(int statusCode) {
    response.then().statusCode(statusCode);
  }
}
