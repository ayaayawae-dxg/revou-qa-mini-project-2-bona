Feature: Checkout Page Functionality

  Background:
    Given User is logged in
    And User on the Sauce Demo inventory page "https://www.saucedemo.com/inventory.html"

  Scenario: User see the checkout page
    When User clicks the add to cart button for "backpack"
      And User click on the cart button
      And User click on the checkout button
    Then User should see the checkout page

  Scenario: User fill out the checkout form with complete information
    When User clicks the add to cart button for "backpack"
      And User click on the cart button
      And User click on the checkout button
      And User fills the form with this information:
        | first_name  | John  |
        | last_name   | Doe   |
        | postal_code | 12345 |
      And User clicks the continue button
    Then User should see the checkout overview page

  Scenario Outline: User fill out the checkout form with incomplete information
    When User clicks the add to cart button for "backpack"
      And User click on the cart button
      And User click on the checkout button
      And User fills the form with this information:
        | first_name  | <first_name>  |
        | last_name   | <last_name>   |
        | postal_code | <postal_code> |
      And User clicks the continue button
    Then User should see the checkout error message

    Examples:
      | first_name | last_name | postal_code |
      | John       |           | 12345       |
      |            | Doe       | 12345       |
      | John       | Doe       |             |

  Scenario: User can finish the checkout process
    When User clicks the add to cart button for "backpack"
      And User click on the cart button
      And User click on the checkout button
      And User fills the form with this information:
        | first_name  | John  |
        | last_name   | Doe   |
        | postal_code | 12345 |
      And User clicks the continue button
      And User clicks the finish button
    Then User should see the checkout complete page

  Scenario: User can go back to inventory page from checkout complete page
    When User clicks the add to cart button for "backpack"
      And User click on the cart button
      And User click on the checkout button
      And User fills the form with this information:
        | first_name  | John  |
        | last_name   | Doe   |
        | postal_code | 12345 |
      And User clicks the continue button
      And User clicks the finish button
      And User clicks the Back Home button
    Then User should be redirected to the inventory page
