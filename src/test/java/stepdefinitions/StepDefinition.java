package stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.runner.RunWith;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

@RunWith(Cucumber.class)
public class StepDefinition extends Utils {

	ResponseSpecification responseSpec;
	RequestSpecification request;
	TestDataBuild testDataBuild = new TestDataBuild();;
	Response response;
	static String place_id;

	@Given("Add Place Payload {string} {string} {string}")
	public void add_Place_Payload(String name, String language, String address) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		request = given().spec(requestSpecification()).body(testDataBuild.addPlacePayload(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		// Write code here that turns the phrase above into concrete actions
		APIResources resources = APIResources.valueOf(resource);

		responseSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();

		if (method.equalsIgnoreCase("POST"))
			response = request.when().post(resources.getResources());
		else if (method.equalsIgnoreCase("GET"))
			response = request.when().get(resources.getResources());
		// response =
		// request.when().post(resources.getResources()).then().spec(responseSpec).extract().response();
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(int statusCode) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), statusCode);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(getJsonPath(response, keyValue), expectedValue);
	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		place_id = getJsonPath(response, "place_id");
		request = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
	}

	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
		// Write code here that turns the phrase above into concrete actions
		request = given().spec(requestSpecification()).body(testDataBuild.deletePlacePayload(place_id));
	}

}
