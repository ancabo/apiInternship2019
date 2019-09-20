Feature: Add new order
@SmokeTest
Scenario Outline: Check status code returned when adding new order and check the createdorder
Given I have the API url "<url>"
When I send a post request with body "addOrder.json"
Then I get the responseCode as 200
Given I have the API url as "<url>/<orderID>"
When I send the request
Then I get the responseCode as 200
And The order with ID <orderID> is returned

Examples:
| url			 					|orderID | 
|/store/order    		| 		1	 |


Scenario Outline: Delete order
When Delete the order "<orderID>"
Given I have the API url as "<url>/<orderID>"
When I send the request
Then I get the responseCode as 404

Examples:
| url			 					|orderID | 
|/store/order    		| 		1	 |

      

