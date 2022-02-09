Feature: Validating the Place API's 

Scenario Outline: Verify if place is being added using AddPlace API 

	Given Add Place Payload with "<name>" "<language>" "<address>" 
	When user calls "AddPlace" API with "Post" http request 
	Then API Call got success with status code 200 
	And "Status" in response is "OK" 
	And Verify placeid matches with "<name>" using "GetPlace" 
	
	Examples: 
		|name| language |address|
		|Jahanzab| English |Lahore|
		
		
Scenario: Verify DeletePlace API is working fine 

	Given Add Request Payload 
	When user calls "DeletePlace" API with "Post" http request 
	And "Status" in response is "OK" 
	