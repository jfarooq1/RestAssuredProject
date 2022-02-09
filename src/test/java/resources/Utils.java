package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {

	static RequestSpecification req;
	static ResponseSpecification res;
	static Properties pr;
	static FileInputStream fs;

	// Loading Property File to read the global parameters
	public static String getGlobalValue(String key) throws IOException {
		pr = new Properties();
		fs = new FileInputStream(
				"/Users/jahanzaibfarooq/eclipse-workspace/APIAutomation/src/test/java/resources/global.properties");
		pr.load(fs);
		return pr.getProperty(key);

	}

	// RequestSpectBuilder
	public RequestSpecification requestSpecification() throws IOException {
		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
			System.out.println("-------------------------------------------------------------");

			return req;
		}
		return req;
	}

	// ResponseSpecBuilder
	public ResponseSpecification responseSpecification() {
		res = new ResponseSpecBuilder().expectStatusCode(200).build();
		return res;
	}
}

//Started