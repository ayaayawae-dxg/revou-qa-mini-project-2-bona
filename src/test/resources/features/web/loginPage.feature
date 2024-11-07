 Feature: Login Page Functionality

  Background:
    Given User on the Sauce Demo login page "https://www.saucedemo.com"

  Scenario Outline: Successful login with valid credentials
    When User enter username "<username>"
    And User enter password "<password>"
    And User click the login button
    Then User should be redirected to the inventory page "https://www.saucedemo.com/inventory.html"

    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | locked_out_user         | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |
      | error_user              | secret_sauce |
      | visual_user             | secret_sauce |

  Scenario Outline: Failed login with invalid credentials
    When User enter username "<username>"
    And User enter password "<password>"
    And User click the login button
    Then User should see a login error message "Username and password do not match any user in this service"

    Examples:
      | username                | password         |
      | standard_user           | wrong_password   |
      | locked_out_user         | wrong_password_2 |
      | problem_user            | wrong_password_3 |
      | performance_glitch_user | asdasdn123asdc   |
      | error_user              | secret_sauce1    |
      | visual_user             | secret_sauce2    |

  Scenario: Close the error message
    Given User see a login error message
    When User click the close error message button
    Then User should not see a login error message
