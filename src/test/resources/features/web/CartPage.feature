Feature: Cart Page Functionality

  Background:
    Given User is logged in
    And User on the Sauce Demo inventory page "https://www.saucedemo.com/inventory.html"

  Scenario: User can see the cart items
    When User click on the cart button
    Then User will be redirected to the cart page

  Scenario: User can see added items in the cart
    When User clicks the add to cart button for "backpack"
      And User click on the cart button
    Then User can see the "backpack" item in the cart

  Scenario: User can remove items from the cart
    When User clicks the add to cart button for "backpack"
      And User click on the cart button
    When User clicks the remove button for "backpack"
    Then User should not see the "backpack" item in the cart
