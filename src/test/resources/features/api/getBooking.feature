Feature: Retrieve booking IDs

  Background:
    Given The booking endpoint is "https://restful-booker.herokuapp.com/booking"

  Scenario: Retrieve a booking with a valid ID
    Given a booking exist with the ID 1
    When I send a GET booking request with ID 1
    Then I should receive a booking detail

  Scenario: Retrieve a booking with an invalid ID
    When I send a GET booking request with ID 99999
    Then I should receive status code 404