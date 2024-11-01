Feature: Partial update booking

  Background:
    Given The partial update booking endpoint is "https://restful-booker.herokuapp.com/booking"
    And A valid token "YWRtaW46cGFzc3dvcmQxMjM=" is set in the partial update booking request header

  Scenario: Partial update booking with complete data
    Given a booking exists with the ID 1 and can be updated partially
    When I send a PATCH partial booking request with the following parameters:
      | firstname             | Jim           |
      | lastname              | Brown         |
      | totalprice            | 111           |
      | depositpaid           | true          |
      | bookingdates.checkin  | 2018-01-01    |
      | bookingdates.checkout | 2019-01-01    |
      | additionalneeds       | Breakfast     |
    Then I should receive a detail of the partially updated booking

  Scenario: Partial update booking without name
    Given a booking exists with the ID 1 and can be updated partially
    When I send a PATCH partial booking request with the following parameters:
      | totalprice            | 111           |
      | depositpaid           | true          |
      | bookingdates.checkin  | 2018-01-01    |
      | bookingdates.checkout | 2019-01-01    |
    Then I should receive a detail of the partially updated booking

  Scenario: Partial update booking without booking dates
    Given a booking exists with the ID 1 and can be updated partially
    When I send a PATCH partial booking request with the following parameters:
      | firstname             | Jim           |
      | lastname              | Brown         |
      | totalprice            | 111           |
      | depositpaid           | true          |
      | additionalneeds       | Breakfast     |
    Then I should receive a detail of the partially updated booking

  Scenario: Partial update booking without price and deposit paid
    Given a booking exists with the ID 1 and can be updated partially
    When I send a PATCH partial booking request with the following parameters: 
      | firstname             | Jim           |
      | lastname              | Brown         |
      | bookingdates.checkin  | 2018-01-01    |
      | bookingdates.checkout | 2019-01-01    |
      | additionalneeds       | Breakfast     |
    Then I should receive a detail of the partially updated booking

  Scenario: Partial update booking with past date
    Given a booking exists with the ID 1 and can be updated partially
    And partial update booking date is 2024-10-31
    When I send a PATCH partial booking request with the following parameters:
      | bookingdates.checkin  | 2024-10-01    |
      | bookingdates.checkout | 2024-10-05    |
    Then I should receive a Bad Request error for partial update booking

  Scenario: Partial update booking without checkin
    Given a booking exists with the ID 1 and can be updated partially
    When I send a PATCH partial booking request with the following parameters:
      | bookingdates.checkout | 2024-10-05    |
    Then I should receive a Bad Request error for partial update booking

  Scenario: Partial update booking without checkout
    Given a booking exists with the ID 1 and can be updated partially
    When I send a PATCH partial booking request with the following parameters:
      | bookingdates.checkin | 2024-10-05    |
    Then I should receive a Bad Request error for partial update booking