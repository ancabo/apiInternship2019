Feature: Add new pet
Scenario: Check status code returned when adding new pet
Given I have the API url "/pet"
When I send a post request with body "addPet.json"
Then I get the responseCode as 200
