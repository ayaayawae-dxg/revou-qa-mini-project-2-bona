package com.testing.api.auth;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreateToken {
  private Response response;
  private JSONObject requestParams;

  @Before
  public void setup() {
    requestParams = new JSONObject();
  }

  @Given("the authentication endpoint is {string}")
  public void setAuthEndpoint(String endpoint) {
    RestAssured.baseURI = endpoint;
  }

  @Given("the request content type is {string}")
  public void setRequestContentType(String contentType) {
    RestAssured.requestSpecification = new RequestSpecBuilder()
      .setContentType(contentType)
      .build();
  }

  @Given("the response content type is {string}")
  public void setResponseContentType(String contentType) {
    RestAssured.requestSpecification = new RequestSpecBuilder()
      .setAccept(contentType)
      .build();
  }

  @When("I send a POST request with the following credentials:")
  public void createTokenWithCredentials(DataTable dataTable) {
    Map<String, String> credentials = dataTable.asMap(String.class, String.class);

    credentials.forEach((key, value) -> requestParams.put(key, value));

    String requestString = requestParams.toJSONString();

    response = given()
      .body(requestString)
      .when()
      .post();
  }

  @Then("the response status code should be {int}")
  public void verifyResponseStatusCode(int expectedStatusCode) {
    response.then().statusCode(expectedStatusCode);
  }

  @Then("the response should contain a valid token")
  public void verifyValidToken() {
    response.then().body("token", notNullValue());
  }

  @Then("the response should contain reason {string}")
  public void verifyErrorReason(String expectedReason) {
    response.then().body("reason", equalTo(expectedReason));
  }
}
