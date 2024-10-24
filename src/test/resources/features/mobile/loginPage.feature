Feature: Login Page Functionality

  Background:
    Given User on the login page

  Scenario: Successful login with valid credentials
    When User enter username "bob@example.com"
    And User enter password "10203040"
    And User click the login button
    Then User should be redirected to the product page

#  Scenario: Failed login with invalid credentials
#    When User enter username "user1"
#    And User enter password "password"
#    And User click the login button
#    Then User should see an error message "Username and password do not match any user in this service"