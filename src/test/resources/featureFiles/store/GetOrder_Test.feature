Feature: Get all pets
Scenario Outline: Check all statuses of the pets 
Given I have the API url as "<url><petStatus>"
When I send the request
Then I get the responseCode as <responseCode>
And Check pets more than <pets>


Examples:
| url			 										| responseCode  | pets | petStatus |
|/pet/findByStatus?status=    | 	200				  |   1	 | available |