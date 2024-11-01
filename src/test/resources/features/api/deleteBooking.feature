Feature: Delete Booking

  Background:
    Given The delete booking endpoint is "https://restful-booker.herokuapp.com/booking"
    And A valid token "YWRtaW46cGFzc3dvcmQxMjM=" is set in the delete booking request header

  Scenario: Delete a booking with a valid ID
    Given a booking exist with the ID 1 and can be deleted
    When I send a DELETE booking request with ID 1
    Then I should receive status code 200 of the deleted booking

  Scenario: Delete a booking with an invalid ID
    When I send a DELETE booking request with ID 99999
    Then I should receive status code 404 of the deleted booking

