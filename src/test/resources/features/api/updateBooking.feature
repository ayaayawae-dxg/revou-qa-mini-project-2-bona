Feature: Update booking

  Background:
    Given The update booking endpoint is "https://restful-booker.herokuapp.com/booking"
    And A valid token "YWRtaW46cGFzc3dvcmQxMjM=" is set in the update booking request header

  Scenario: Update booking with a complete data
    Given a booking exist with the ID 1 and can be updated
    When I send a PUT booking request with the following parameters:
      | firstname             | Jim           |
      | lastname              | Brown         |
      | totalprice            | 111           |
      | depositpaid           | true          |
      | bookingdates.checkin  | 2018-01-01    |
      | bookingdates.checkout | 2019-01-01    |
      | additionalneeds       | Breakfast     |
    Then I should receive a detail of the updated booking

  Scenario: Update booking without name
    Given a booking exist with the ID 1 and can be updated
    When I send a PUT booking request with the following parameters:
      | totalprice            | 111           |
      | depositpaid           | true          |
      | bookingdates.checkin  | 2018-01-01    |
      | bookingdates.checkout | 2019-01-01    |
    Then I should receive a Bad Request error for update booking

  Scenario: Update booking without booking dates
    Given a booking exist with the ID 1 and can be updated
    When I send a PUT booking request with the following parameters:
      | firstname             | Jim           |
      | lastname              | Brown         |
      | totalprice            | 111           |
      | depositpaid           | true          |
      | additionalneeds       | Breakfast     |
    Then I should receive a Bad Request error for update booking

  Scenario: Update booking without price and deposit paid
    Given a booking exist with the ID 1 and can be updated
    When I send a PUT booking request with the following parameters:
      | firstname             | Jim           |
      | lastname              | Brown         |
      | bookingdates.checkin  | 2018-01-01    |
      | bookingdates.checkout | 2019-01-01    |
      | additionalneeds       | Breakfast     |
    Then I should receive a Bad Request error for update booking

  Scenario: Update booking with past date
    Given a booking exist with the ID 1 and can be updated
      And update booking date is 2024-10-31
    When I send a PUT booking request with the following parameters:
      | firstname             | Jim           |
      | lastname              | Brown         |
      | totalprice            | 111           |
      | depositpaid           | true          |
      | bookingdates.checkin  | 2024-10-01    |
      | bookingdates.checkout | 2024-10-05    |
      | additionalneeds       | Breakfast     |
    Then I should receive a Bad Request error for update booking