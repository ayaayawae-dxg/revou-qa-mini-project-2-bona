package com.testing.api.booking;

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

public class UpdatePartialBooking {
  private Response response;
  private JSONObject requestParams;
  private RequestSpecification spec;
  private RequestSpecification request;

  @Before
  public void setup() {
    requestParams = new JSONObject();
  }

  @Given("The partial update booking endpoint is {string}")
  public void thePartialUpdateBookingEndpointIs(String endpoint) {
    spec = new RequestSpecBuilder().setContentType("application/json").setBaseUri(endpoint).build();
    request = given().spec(spec);
  }

  @Given("a booking exists with the ID {int} and can be updated partially")
  public void aBookingExistsWithTheIDAndCanBeUpdatedPartially(int id) {
    RequestSpecification requestLocal = given().spec(spec);

    requestLocal
      .pathParams("id", id)
      .when()
      .get("/{id}")
      .then()
      .statusCode(200);

    request = request.basePath("/" + id);
  }

  @And("A valid token {string} is set in the partial update booking request header")
  public void aValidTokenIsSetInThePartialUpdateBookingRequestHeader(String token) {
    request.header("Authorization", "Basic " + token);
  }

  @When("I send a PATCH partial booking request with the following parameters:")
  public void iSendAPATCHPartialBookingRequestWithTheFollowingParameters(DataTable dataTable) {
    Map<String, String> params = dataTable.asMap(String.class, String.class);
    JSONObject bookingDates = new JSONObject();

    bookingDates.put("checkin", params.get("bookingdates.checkin"));
    bookingDates.put("checkout", params.get("bookingdates.checkout"));

    if (params.get("firstname") != null) requestParams.put("firstname", params.get("firstname"));
    if (params.get("lastname") != null) requestParams.put("lastname", params.get("lastname"));
    if (params.get("totalprice") != null) requestParams.put("totalprice", Double.parseDouble(params.get("totalprice")));
    if (params.get("depositpaid") != null) requestParams.put("depositpaid", Boolean.parseBoolean(params.get("depositpaid")));
    if (params.get("bookingdates.checkin") != null || params.get("bookingdates.checkout") != null) requestParams.put("bookingdates", bookingDates);
    if (params.get("additionalneeds") != null) requestParams.put("additionalneeds", params.get("additionalneeds"));

    String requestString = requestParams.toJSONString();

    System.out.println("bbb" + requestString);

    response = request.body(requestString).when().patch();
  }

  @Then("I should receive a detail of the partially updated booking")
  public void iShouldReceiveADetailOfThePartiallyUpdatedBooking() {
    response.then()
      .statusCode(200)
      .body(matchesJsonSchemaInClasspath("schemas/api/bookingSchema.json"));
  }

  @Then("I should receive a Bad Request error for partial update booking")
  public void iShouldReceiveABadRequestErrorForPartialUpdateBooking() {
    response.then().statusCode(400);
  }

  @And("partial update booking date is {int}{int}{int}")
  public void partialUpdateBookingDateIs(int arg0, int arg1, int arg2) {
  }
}
