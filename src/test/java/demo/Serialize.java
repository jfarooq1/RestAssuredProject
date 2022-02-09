package demo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class Serialize {

	public static void main(String[] args) {

		

		
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress("29, side layout, cohen 09");
		ap.setLanguage("French-IN");
		ap.setName("Frontline house");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");

		List<String> li = new ArrayList<>();
		li.add("shoe park");
		li.add("shop");
		ap.setTypes(li);

		Location l = new Location();
		l.setLat(38.383494);
		l.setLng(35.383494);
		ap.setLocation(l);
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").build();

		RequestSpecification r = given().spec(req).body(ap);
		
		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).build();
		Response re = r.when().post("maps/api/place/add/json").then().spec(res).extract().response();

	}

}
