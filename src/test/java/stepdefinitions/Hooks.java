package stepdefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		StepDefinition stepDefinition = new StepDefinition();
		if (StepDefinition.place_id == null) {
			stepDefinition.add_Place_Payload("Rahul", "English", "Asia");
			stepDefinition.user_calls_with_http_request("AddPlaceAPI", "POST");
			stepDefinition.verify_place_Id_created_maps_to_using("Rahul", "GetPlaceAPI");
		}
	}
}
