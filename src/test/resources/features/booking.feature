Feature: Booking API Integration Tests

    Scenario: Create, Edit, Retrieve, Delete Booking
        Given I have a booking request payload
        When I send a POST request to create a booking
        Then the status code should be 200 with valid response
        And a booking ID should be generated
        When I update the payload
        And send a PUT request to update the booking
        Then the booking details should match the updated payload
        And should be able to retrieve updated booking request
        Then delete booking request
        
    Scenario: Create, Retrieve, Delete Booking
        Given I have a booking request payload
        When I send a POST request to create a booking
        Then the status code should be 200 with valid response
        And a booking ID should be generated
        Then should be able to retrieve created booking request
        Then delete booking request
        And should not be able to retrieve deleted booking request