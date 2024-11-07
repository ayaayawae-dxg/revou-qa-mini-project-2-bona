# Feature: Inventory Page Functionality

#   Background:
#     Given User is logged in
#     And User on the Sauce Demo inventory page "https://www.saucedemo.com/inventory.html"

#   Scenario: Open the sidebar
#     When User click the menu button
#     Then User should see the sidebar

#   Scenario Outline: Add a single items to cart
#     Given list of items exist in the inventory page
#     When User clicks the add to cart button for "<item_name>"
#     Then the add to cart button for "<item_name>" should change to remove
#     And the cart badge should show 1 items

#     Examples:
#       | item_name  |
#       | backpack   |
#       | bike-light |

#   Scenario Outline: Add multiple items to cart
#     Given list of items exist in the inventory page
#     When User clicks the add to cart button for "<item_name_1>"
#     And User clicks the add to cart button for "<item_name_2>"
#     Then the add to cart button for "<item_name_1>" should change to remove
#     And the add to cart button for "<item_name_2>" should change to remove
#     And the cart badge should show 2 items

#     Examples:
#       | item_name_1  | item_name_2   |
#       | backpack     | bike-light    |
#       | bolt-t-shirt | fleece-jacket |

#   Scenario Outline: Remove an item from cart
#     When User clicks the add to cart button for "<item_name>"
#     Then the add to cart button for "<item_name>" should change to remove
#     And the cart badge should show 1 items
#     When User clicks the remove button for "<item_name>"
#     Then the remove button for "<item_name>" should change to add to cart
#     And the cart badge should be empty

#     Examples:
#       | item_name  |
#       | backpack   |
#       | bike-light |

#   Scenario Outline: User wants to see the detail of an item
#     Given list of items exist in the inventory page
#     When User clicks the title of "<item_name>" item
#     Then User will be redirected to the detail page of "<item_name>" item

#     Examples:
#       | item_name  |
#       | backpack   |
#       | bike-light |