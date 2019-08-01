Feature: Add new user
Scenario: Check status code returned when adding new pet
Given I have the API url "/user"
When I send a post request with body "addUser.json"
Then I get the responseCode as 200