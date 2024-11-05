# Feature: Products Detail Page Functionality

#   Background:
#     Given User on the products page

#  Scenario Outline: User can add multiple quantities of a product to cart
#    When User click on the product "<product_name>"
#    Then User should see the product details for "<product_name>"
#      And User should see "<color>" color selection
#      And the quantity should be 1
#    When User increase the quantity by <add_quantity>
#      And User select "<color>" color
#      And User click Add to Cart button
#    Then the cart badge should show <total_quantity> items

#    Examples:
#      | product_name        | add_quantity | color        | total_quantity |
#      | Sauce Labs Backpack | 3            | black circle | 4              |
#      | Sauce Labs Backpack | 2            | blue circle  | 3              |
#      | Sauce Labs Backpack | 1            | gray circle  | 2              |
#      | Sauce Labs Backpack | 4            | red circle   | 5              |

#   Scenario: Verify quantity selector behavior
#     When User click on the product "Sauce Labs Backpack"
#     Then User should see the product details for "Sauce Labs Backpack"
#       And the quantity should be 1
#     When User decrease the quantity by 1
#     Then the quantity should be 0
#       And the add to cart button should be disabled
#     When User increase the quantity by 1
#     Then the quantity should be 1
#       And the add to cart button should be enabled
#     When User decrease the quantity by 2
#       Then the quantity should be 0
#       And the add to cart button should be disabled

#   Scenario Outline: Verify add to cart button behavior
#     When User scroll to the product "<product_name>"
#       And User click on the product "<product_name>"
#     Then User should see the product details for "<product_name>"
#       And the quantity should be 1
#     When User increase the quantity by <add_quantity>
#       And User click Add to Cart button
#     Then the cart badge should show <total_quantity> items

#     Examples:
#       | product_name        | add_quantity | total_quantity |
#       | Sauce Labs Backpack | 3            | 4              |
#       | Sauce Labs Onesie   | 2            | 3              |