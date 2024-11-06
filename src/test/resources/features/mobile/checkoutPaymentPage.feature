Feature: Checkout Payment Page Functionality

  Background:
    Given User login with username "bob@example.com" and password "10203040"
      And User add items "Sauce Labs Backpack" to the cart with quantity 3 and color "blue circle"
      And User click on the cart button
      And User click on the checkout button
      And User should see checkout page
      And User fill shipping address with this information:
        | full_name      | John Doe    |
        | address_line_1 | address 1   |
        | address_line_2 | address 2   |
        | city           | Jakarta     |
        | state_region   | DKI Jakarta |
        | zip_code       | 10110       |
        | country        | Indonesia   |
      And User click on the to payment button
      Then User should see checkout payment page

  Scenario: Navigate to checkout review order page with complete payment information
    When User fill payment fields with this information:
      | full_name      | John Doe         |
      | card_number    | 1234567890123456 |
      | expiry_date    | 12/24            |
      | security_code  | 123              |
    And User click on the review order button 1 times
    Then User should see checkout review order page

  Scenario: Navigate to checkout review order page with complete payment information
    When User fill payment fields with this information:
      | full_name      | John Doe         |
      | card_number    | 1234567890123456 |
      | expiry_date    | 12/24            |
      | security_code  | 123              |
    And User click on the review order button 2 times
    Then User should see checkout review order page