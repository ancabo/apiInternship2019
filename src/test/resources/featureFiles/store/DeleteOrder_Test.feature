Feature: Play with orders
Scenario Outline: Delete order
Given I have the API url "<url>"
When I send a post request with body "addOrder.json"
Then I get the responseCode as <responseCode>
Given I have the API url "<url>/<orderID>"
When I send the request
Then I get the responseCode as <responseCode>
And The order with ID <orderID> is returned
When Delete the order <orderID>
And I send the request
Then I get the responseCode as 404

Examples:
| url			 																				|orderID |responseCode  | 
|https://petstore.swagger.io/v2/store/order    		| 		1	 |200				 		| 