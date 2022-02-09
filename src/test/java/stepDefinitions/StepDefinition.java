package stepDefinitions;

import static io.restassured.RestAssured.given;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.junit.Assert.*;
import pojo.AddPlace;
import pojo.Location;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {

	
	RequestSpecification r;
	ResponseSpecification res;
	Response re;
	TestDataBuild td = new TestDataBuild();
	static String place_id;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String string, String string2, String string3) throws IOException {
		r = given().spec(requestSpecification()).body(td.addPlacePayLoad(string, string2, string3));
	}

	@When("user calls {string} API with {string} http request")
	public void user_calls_api_with_http_request(String resource, String method) {
		ApiResources resources = ApiResources.valueOf(resource);
		if (method.equalsIgnoreCase("post")) {
			re = r.when().post(resources.getResources());
		} else if (method.equalsIgnoreCase("get")) {
			re = r.when().get(resources.getResources());
		}

	} 

	@Then("API Call got success with status code {int}")
	public void api_call_got_success_with_status_code(Integer int1) {
		assertEquals(re.getStatusCode(), 200);
	}

	@And("^\"([^\"]*)\" in response is \"([^\"]*)\"$")
	public void something_in_response_is_something(String strArg1, String strArg2) throws Throwable {
		JsonPath js = new JsonPath(re.asString());
		place_id = js.get("place_id");
		System.out.println(place_id);

		assertEquals(js.get(strArg1), js.get(strArg2));
	}
	
	

	@And("Verify placeid matches with {string} using {string}")
	public void verify_placeid_matches_with_using(String name, String resource) throws IOException {
		r = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_api_with_http_request(resource, "GET");
		JsonPath js = new JsonPath(re.asString());
		String actualname = js.get("name");
		assertEquals(actualname, name);
	}

	@Given("Add Request Payload")
	public void add_request_payload() throws IOException {

		// System.out.println(place_id);
		r = given().spec(requestSpecification()).body("{\n" + "    \"place_id\":\"" + place_id + "\"\n" + "}");
		user_calls_api_with_http_request("DeletePlace", "Post");
		JsonPath js = new JsonPath(re.asString());
		String status = js.get("status");
		assertEquals(status, "OK");

	}

}

// AddPlaceApi()