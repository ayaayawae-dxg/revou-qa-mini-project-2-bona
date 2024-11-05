Feature: Cart Page Functionality

  Background:
    Given User on the products page

  Scenario Outline: User can see the cart items
    Given User add items "<product_name>" to the cart with quantity <quantity> and color "<color>"
    When User click on the cart button
    Then User should see the cart items "<product_name>" with quantity <quantity> and color "<color>"

    Examples:
      | product_name        | quantity | color        |
      | Sauce Labs Backpack | 3        | black circle |
      | Sauce Labs Onesie   | 2        | red circle   |

  
  Scenario Outline: User can remove items from the cart
    Given User add items "<product_name>" to the cart with quantity <quantity> and color "<color>"
    When User click on the cart button
    Then User should see the cart items "<product_name>" with quantity <quantity> and color "<color>"
    When User click on the remove button with product name "<product_name>" and color "<color>"
    Then User should not see the cart items "<product_name>" and color "<color>"

    Examples:
      | product_name        | quantity | color        |
      | Sauce Labs Backpack | 3        | black circle |