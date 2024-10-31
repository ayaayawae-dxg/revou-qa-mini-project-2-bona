package com.testing.api.booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class UpdateBooking {
  private Response response;
  private JSONObject requestParams;
  private RequestSpecification spec;
  private RequestSpecification request;

  @Before
  public void setup() {
    requestParams = new JSONObject();
  }

  @Given("The update booking endpoint is {string}")
  public void theUpdateBookingEndpointIs(String endpoint) {
    spec = new RequestSpecBuilder().setContentType("application/json").setBaseUri(endpoint).build();
    request = given().spec(spec);
  }

  @Given("a booking exist with the ID {int} and can be updated")
  public void aBookingExistWithTheIDAndCanBeUpdated(int id) {
    RequestSpecification requestLocal = given().spec(spec);

    requestLocal
      .pathParams("id", id)
      .when()
      .get("/{id}")
      .then()
      .statusCode(200);

    request = request.basePath("/" + id);
  }

  @And("A valid token {string} is set in the update booking request header")
  public void aValidTokenIsSetInTheUpdateBookingRequestHeader(String token) {
    request.header("Authorization", "Basic " + token);
  }

  @When("I send a PUT booking request with the following parameters:")
  public void iSendAPUTBookingRequestWithTheFollowingParameters(DataTable dataTable) {
    Map<String, String> params = dataTable.asMap(String.class, String.class);
    JSONObject bookingDates = new JSONObject();

    bookingDates.put("checkin", params.get("bookingdates.checkin"));
    bookingDates.put("checkout", params.get("bookingdates.checkout"));

    requestParams.put("firstname", params.get("firstname"));
    requestParams.put("lastname", params.get("lastname"));
    if (params.get("totalprice") != null) requestParams.put("totalprice", Double.parseDouble(params.get("totalprice")));
    if (params.get("depositpaid") != null) requestParams.put("depositpaid", Boolean.parseBoolean(params.get("depositpaid")));
    requestParams.put("bookingdates", bookingDates);
    requestParams.put("additionalneeds", params.get("additionalneeds"));

    String requestString = requestParams.toJSONString();

    response = request.body(requestString).when().put();
  }

  @Then("I should receive a detail of the updated booking")
  public void iShouldReceiveADetailOfTheUpdatedBooking() {
    response.then()
      .statusCode(200)
      .body(matchesJsonSchemaInClasspath("schemas/api/bookingSchema.json"));
  }

  @Then("I should receive a Bad Request error for update booking")
  public void iShouldReceiveABadRequestErrorForUpdateBooking() {
    response.then().statusCode(400);
  }

  @And("update booking date is {int}{int}{int}")
  public void dateNowIs(int arg0, int arg1, int arg2) {
  }
}
