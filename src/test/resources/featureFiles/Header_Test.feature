Feature: Response headers
Scenario Outline: Content type check
Given I have the API url as "<url>"
When I send the request
Then I get the contentType as "<contentType>"

Examples:
| url			 																				 | contentType  					| 
|https://petstore.swagger.io/v2/store/inventory    | application/json				| 
|https://petstore.swagger.io/v2/store/inventors    | application/xhtml+xml	|

Scenario Outline: Header options check
Given I have the API url as "<url>"
When I send the request
Then I get all the headers
And I check allowHeaders as "<allowHeaders>" and allowMethods as "<allowMethods>"

Examples:
| url			 																				 | allowHeaders  												| allowMethods						|
|https://petstore.swagger.io/v2/store/inventory    | Content-Type, api_key, Authorization	| GET, POST, DELETE, PUT	|
|https://petstore.swagger.io/v2/store/inventors    | Content-Type, api_key, Authorization	|	GET, POST, DELETE, PUT	|