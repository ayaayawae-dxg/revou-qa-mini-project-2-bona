package com.testing.api.booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreateBooking {
  private Response response;
  private JSONObject requestParams;
  private RequestSpecification spec;
  private RequestSpecification request;

  @Before
  public void setup() {
    requestParams = new JSONObject();
  }

  @Given("The create booking endpoint is {string}")
  public void theCreateBookingEndpointIs(String endpoint) {
    spec = new RequestSpecBuilder().setContentType("application/json").setBaseUri(endpoint).build();
    request = given().spec(spec);
  }

  @When("I send a POST booking request with the following parameters:")
  public void iSendAPOSTBookingRequestWithTheFollowingParameters(DataTable dataTable) {
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

    response = request.body(requestString).when().post();
  }

  @Then("I should receive a detail of the new created booking")
  public void iShouldReceiveADetailOfTheNewCreatedBooking() throws JsonProcessingException {
    response.then()
      .statusCode(200)
      .body("bookingid", notNullValue());

    Map<String, Object> booking = response.then().extract().path("booking");

    ObjectMapper objectMapper = new ObjectMapper();
    String bookingJson = objectMapper.writeValueAsString(booking);

    assertThat(bookingJson, matchesJsonSchemaInClasspath("schemas/api/bookingSchema.json"));
  }

  @Then("I should receive a Bad Request error for create booking")
  public void iShouldReceiveABadRequestErrorForCreateBooking() {
    response.then().statusCode(400);
  }

  @Given("date now is {int}{int}{int}")
  public void dateNowIs(int arg0, int arg1, int arg2) {
  }
}
