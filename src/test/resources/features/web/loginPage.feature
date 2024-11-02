Feature: Login Page Functionality

  Background:
    Given User on the Sauce Demo login page "https://www.saucedemo.com"
    And the Chrome browser is configured

  Scenario: Successful login with valid credentials
    When User enter username "standard_user"
    And User enter password "secret_sauce"
    And User click the login button
    Then User should be redirected to the inventory page "https://www.saucedemo.com/inventory.html"

  Scenario: Failed login with invalid credentials
    When User enter username "user1"
    And User enter password "password"
    And User click the login button
    Then User should see a login error message "Username and password do not match any user in this service"