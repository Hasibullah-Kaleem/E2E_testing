@e2e
Feature: TC02 Database Room Validation
  Scenario: Testing the room via DB
    Given Admin Connects to the Database
    When sends query for created room
    Then validates created room from resultset