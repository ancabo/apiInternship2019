Feature: Status Code
Scenario Outline: Response Code check
Given I have the API url as "<url>"
When I send the request
Then I get the responseCode as <responseCode>

Examples:
| url			 																				 | responseCode  | 
|https://petstore.swagger.io/v2/store/inventory    | 200				 	 | 
|https://petstore.swagger.io/v2/store/inventors    | 404				 	 |