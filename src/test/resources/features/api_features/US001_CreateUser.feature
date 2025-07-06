@api
Feature: US001 Create User
  @api01
  Scenario: TC01 I should be able to successfully create a user in the database
    Given the base URL "https://reqres.in" and path parameter "/api/users" is used
    And a payload is created with name "bob smith" and job "QA Tester"
    When a post request is sent and a response is received
    Then the status code should be 201
    And the response content type should be "application/json; charset=utf-8"
    And the response name should be "bob smith" and job should be "QA Tester"