Feature: Validating Place APIs 

@AddPlace @Regression
Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI 
	Given Add Place Payload "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "post" http request 
	Then the API call got success with status code 200 
	And "status" in response body is "OK" 
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "GetPlaceAPI"
	
Examples:
		|name     |language  |address  |
		|A1House   |English   |USA      |
		|B1House   |Marathi   |MH       |


@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working
	Given DeletePlace Payload
	When user calls "DeletePlaceAPI" with "post" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"