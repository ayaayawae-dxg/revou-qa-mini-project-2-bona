Feature: Checkout Page Functionality

  Background:
    Given User login with username "bob@example.com" and password "10203040"
    And User add items "Sauce Labs Backpack" to the cart with quantity 3 and color "blue circle"
    And User click on the cart button
    And User click on the checkout button
    And User should see checkout page

  Scenario: Fill shipping address fields with complete information
    When User fill shipping address with this information:
      | full_name      | John Doe    |
      | address_line_1 | address 1   |
      | address_line_2 | address 2   |
      | city           | Jakarta     |
      | state_region   | DKI Jakarta |
      | zip_code       | 10110       |
      | country        | Indonesia   |
    And User click on the to payment button
    Then User should see checkout payment page

  Scenario: Fill shipping address fields with incomplete information
    When User click on the to payment button
    Then User should see error message for fields:
      | full_name      |
      | address_line_1 |
      | city           |
      | zip_code       |
      | country        |
