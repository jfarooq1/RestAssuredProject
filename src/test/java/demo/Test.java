package demo;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://5e898060-0b97-43cc-82c5-339f41a560f8.mock.pstmn.io";
		given().header("x-mock-response-code", "200").when().get().then().log().all()
				.body(matchesJsonSchemaInClasspath("schema.json"));

	}

}
