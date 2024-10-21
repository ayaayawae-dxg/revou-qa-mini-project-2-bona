package com.testing.api.auth;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreateToken {
  private static final String AUTH_ENDPOINT = "/auth";

  @BeforeAll
  public static void setup() {
    RestAssured.baseURI = "https://restful-booker.herokuapp.com" + AUTH_ENDPOINT;
    RestAssured.requestSpecification = new RequestSpecBuilder()
      .setContentType("application/json")
      .setAccept("application/json")
      .build();
  }

  @Test
  public void createTokenWithValidCredentials() {
    JSONObject requestParams = new JSONObject();
    requestParams.put("username", "admin");
    requestParams.put("password", "password123");
    String requestString = requestParams.toJSONString();

    given()
      .body(requestString)
      .when()
        .post()
      .then()
        .statusCode(200)
        .body("token", notNullValue());
  }

  @Test
  public void createTokenWithInvalidCredentials() {
    JSONObject requestParams = new JSONObject();
    requestParams.put("username", "user");
    requestParams.put("password", "user1");
    String requestString = requestParams.toJSONString();

    given()
      .body(requestString)
      .when()
      .post()
      .then()
      .statusCode(200)
      .body("reason", equalTo("Bad credentials"));
  }
}
