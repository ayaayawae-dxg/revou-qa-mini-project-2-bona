Feature: Create token for login

  Background:
    Given the authentication endpoint is "https://restful-booker.herokuapp.com/auth"
    And the request content type is "application/json"
    And the response content type is "application/json"

  Scenario: Create token with valid credentials
    When I send a POST request with the following credentials:
      | username | admin       |
      | password | password123 |
    Then the response status code should be 200
    And the response should contain a valid token

  Scenario: Create token with invalid credentials
    When I send a POST request with the following credentials:
      | username | user  |
      | password | user1 |
    Then the response status code should be 200
    And the response should contain reason "Bad credentials"

  Scenario: Create token with missing username
    When I send a POST request with the following credentials:
      | password | user1 |
    Then the response status code should be 200
    And the response should contain reason "Bad credentials"

  Scenario: Create token with missing password
    When I send a POST request with the following credentials:
      | username | user |
    Then the response status code should be 200
    And the response should contain reason "Bad credentials"