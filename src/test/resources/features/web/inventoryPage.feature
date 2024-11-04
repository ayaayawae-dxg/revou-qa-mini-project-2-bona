Feature: Inventory Page Functionality

  Background:
    Given User is logged in
    And User on the Sauce Demo inventory page "https://www.saucedemo.com/inventory.html"

  Scenario: Open the sidebar
    When User click the menu button
    Then User should see the sidebar

  Scenario Outline: Add a single items to cart
    Given list of items exist in the inventory page
    When User clicks the add to cart button for "<item_name>"
    Then the add to cart button for "<item_name>" should change to remove

    Examples:
      | item_name  |
      | backpack   |
      | bike-light |