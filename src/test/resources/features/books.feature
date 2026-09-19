Feature: Books API

  Scenario: Check the books response
    Given the books API is configured
    When I request the books
    Then the status code should be 200
    And the response time should be below 3000 milliseconds
    And the response body size should be greater than 0 bytes
    And the response should contain 6 books
    And the first book should be "The Russian"
    And the book with ID 4 should be "The Midnight Library"
    And there should be 4 fiction books
    And there should be 5 available books
    And the book with ID 2 should be unavailable
