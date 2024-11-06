# Feature: Cart Page Functionality

#   Background:
#     Given User on the products page

#  Scenario Outline: User can see the cart items
#    Given User add items "<product_name>" to the cart with quantity <quantity> and color "<color>"
#    When User click on the cart button
#    Then User should see the cart items "<product_name>" with quantity <quantity> and color "<color>"
#      And total quantity is should match with cart items quantity
#      And total price is should match with cart items price
#
#    Examples:
#      | product_name        | quantity | color        |
#      | Sauce Labs Backpack | 3        | black circle |
#      | Sauce Labs Onesie   | 2        | red circle   |
#
#
#  Scenario Outline: User can remove items from the cart
#    Given User add items "<product_name>" to the cart with quantity <quantity> and color "<color>"
#    When User click on the cart button
#    Then User should see the cart items "<product_name>" with quantity <quantity> and color "<color>"
#    When User click on the remove button with product name "<product_name>" and color "<color>"
#    Then User should not see the cart items "<product_name>" and color "<color>"
#
#    Examples:
#      | product_name          | quantity | color        |
#      | Sauce Labs Backpack   | 3        | blue circle  |
#      | Sauce Labs Bike Light | 3        | black circle |

  # Scenario Outline: User redirected to login if not logged in on checkout
  #   Given User add items "<product_name>" to the cart with quantity <quantity> and color "<color>"
  #   When User click on the cart button
  #   Then User should see the cart items "<product_name>" with quantity <quantity> and color "<color>"
  #   When User click on the checkout button
  #   Then User should see the login page

  #   Examples:
  #     | product_name        | quantity | color        |
  #     | Sauce Labs Backpack | 2        | black circle |

  # Scenario Outline: User can see checkout page
  #   Given User login with username "bob@example.com" and password "10203040"
  #     And User add items "<product_name>" to the cart with quantity <quantity> and color "<color>"
  #     And User click on the cart button
  #     And User click on the checkout button
  #   Then User should see checkout page

  #   Examples:
  #     | product_name        | quantity | color      |
  #     | Sauce Labs Backpack | 2        | red circle |