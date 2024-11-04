Feature: Login Page Functionality

  Background:
    Given User on the login page

  # Scenario: Successful login with valid credentials
  #   When User enter username "bob@example.com"
  #   And User enter password "10203040"
  #   And User click the login button
  #   Then User should be redirected to the product page

  # Scenario: Failed login with invalid credentials
  #   When User enter username "user1"
  #   And User enter password "password"
  #   And User click the login button
  #   Then User should see an error message "Provided credentials do not match any user in this service."

  # Scenario: Failed login with no credentials
  #   When User click the login button
  #   Then User should see username error message "Username is required"
  #   And User should see password error message "Password is required"

#  Scenario: Failed login with missing username
#    When User enter password "10203040"
#    And User click the login button
#    Then User should see username error message "Username is required"
#
#  Scenario: Failed login with missing password
#    When User enter username "bob@example.com"
#    And User click the login button
#    Then User should see password error message "Password is required"

  # Scenario: Failed login with locked out user
  #   When User enter username "alice@example.com"
  #   And User enter password "10203040"
  #   And User click the login button
  #   Then User should see an error message "Sorry, this user has been locked out."
