Feature: Inventory Detail Page Functionality

  Background:
    Given User is logged in
    And User on the Sauce Demo inventory page "https://www.saucedemo.com/inventory.html"

  Scenario: User can add item to cart from detail page
    When User clicks the title of "backpack" item
      And User clicks the add to cart button
    Then the add to cart button should change to remove
      And the cart badge should show 1 items

  Scenario: User can remove item from cart from detail page
    When User clicks the title of "backpack" item
      And User clicks the add to cart button
    Then the add to cart button should change to remove
      And the cart badge should show 1 items
    When User clicks the remove button
    Then the remove button should change to add to cart
      And the cart badge should be empty

  Scenario: User can go back to inventory page from detail page
    When User clicks the title of "backpack" item
      And User clicks the back to products button
    Then User will be redirected to the inventory page
