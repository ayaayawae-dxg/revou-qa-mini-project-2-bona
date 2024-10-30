Feature: Retrieve booking IDs

  Background:
    Given The booking ID endpoint is "https://restful-booker.herokuapp.com/booking"

  Scenario: Retrieve booking IDs without any parameters
    When I send a GET request without any parameters
    Then I should receive a list of booking ID

  Scenario: Retrieve booking IDs with valid firstname and lastname
    Given a booking exist with the following details:
      | firstname | John  |
      | lastname  | Smith |
    When I send a GET booking request with the following parameters:
      | firstname | John  |
      | lastname  | Smith |
    Then I should receive a list of booking ID

  Scenario: Retrieve booking IDs with invalid firstname and lastname
    When I send a GET booking request with the following parameters:
      | firstname | invalid_firstname |
      | lastname  | invalid_lastname  |
    Then I should receive an empty list of booking ID

  Scenario: Retrieve booking IDs with valid checkin and checkout dates
    Given a booking exist with the following details:
      | checkin  | 2001-03-13 |
      | checkout | 2024-05-21 |
    When I send a GET booking request with the following parameters:
      | checkin  | 2001-03-13 |
      | checkout | 2024-05-21 |
    Then I should receive a list of booking ID

  Scenario: Retrieve booking IDs with invalid checkin and checkout dates
    When I send a GET booking request with the following parameters:
      | checkin  | 2099-03-13 |
      | checkout | 2100-05-21 |
    Then I should receive an empty list of booking ID

#  Scenario: Retrieve booking IDs with all valid parameters
#    When I send a GET request with the following parameters:
#      | firstname | sally      |
#      | lastname  | brown      |
#      | checkin   | 2014-03-13 |
#      | checkout  | 2014-05-21 |
#    Then the response status code should be 200
#    And the response body should contain booking IDs
#
#  Scenario: Retrieve booking IDs with only firstname
#    When I send a GET request with the following parameters:
#      | firstname | sally |
#    Then the response status code should be 200
#    And the response body should contain booking IDs
#
#  Scenario: Retrieve booking IDs with only lastname
#    When I send a GET request with the following parameters:
#      | lastname | brown |
#    Then the response status code should be 200
#    And the response body should contain booking IDs
#
#  Scenario: Retrieve booking IDs with only checkin date
#    When I send a GET request with the following parameters:
#      | checkin | 2014-03-13 |
#    Then the response status code should be 200
#    And the response body should contain booking IDs
#
#  Scenario: Retrieve booking IDs with only checkout date
#    When I send a GET request with the following parameters:
#      | checkout | 2014-05-21 |
#    Then the response status code should be 200
#    And the response body should contain booking IDs
#
#  Scenario: Retrieve booking IDs with invalid date format for checkin
#    When I send a GET request with the following parameters:
#      | checkin | 13-03-2014 |
#    Then the response status code should be 400
#    And the response body should contain an error message indicating invalid date format
#
#  Scenario: Retrieve booking IDs with non-existent firstname and lastname
#    When I send a GET request with the following parameters:
#      | firstname | nonexist |
#      | lastname  | user     |
#    Then the response status code should be 200
#    And the response body should be an empty list
#
