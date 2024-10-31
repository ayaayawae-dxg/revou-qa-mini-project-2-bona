Feature: Create booking

  Background:
    Given The create booking endpoint is "https://restful-booker.herokuapp.com/booking"

  Scenario: Create booking with a complete data
    When I send a POST booking request with the following parameters:
      | firstname             | Jim           |
      | lastname              | Brown         |
      | totalprice            | 111           |
      | depositpaid           | true          |
      | bookingdates.checkin  | 2018-01-01    |
      | bookingdates.checkout | 2019-01-01    |
      | additionalneeds       | Breakfast     |
    Then I should receive a detail of the new created booking

  Scenario: Create booking without name
    When I send a POST booking request with the following parameters:
      | totalprice            | 111           |
      | depositpaid           | true          |
      | bookingdates.checkin  | 2018-01-01    |
      | bookingdates.checkout | 2019-01-01    |
    Then I should receive a Bad Request error for create booking

  Scenario: Create booking without booking dates
    When I send a POST booking request with the following parameters:
      | firstname             | Jim           |
      | lastname              | Brown         |
      | totalprice            | 111           |
      | depositpaid           | true          |
      | additionalneeds       | Breakfast     |
    Then I should receive a Bad Request error for create booking

  Scenario: Create booking without price and deposit paid
    When I send a POST booking request with the following parameters:
      | firstname             | Jim           |
      | lastname              | Brown         |
      | bookingdates.checkin  | 2018-01-01    |
      | bookingdates.checkout | 2019-01-01    |
      | additionalneeds       | Breakfast     |
    Then I should receive a Bad Request error for create booking

  Scenario: Create booking with past date
    Given date now is 2024-10-31
    When I send a POST booking request with the following parameters:
      | firstname             | Jim           |
      | lastname              | Brown         |
      | totalprice            | 111           |
      | depositpaid           | true          |
      | bookingdates.checkin  | 2024-10-01    |
      | bookingdates.checkout | 2024-10-05    |
      | additionalneeds       | Breakfast     |
    Then I should receive a Bad Request error for create booking