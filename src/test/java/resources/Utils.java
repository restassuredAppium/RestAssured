package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification requestSpec;

	// return requestSpecification
	public RequestSpecification requestSpecification() throws IOException {
		if (requestSpec == null) {
			PrintStream printStream = new PrintStream(new FileOutputStream("logging.txt"));
			requestSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUri"))
					.addFilter(RequestLoggingFilter.logRequestTo(printStream))
					.addFilter(ResponseLoggingFilter.logResponseTo(printStream)).addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON).build();
			return requestSpec;
		}
		return requestSpec;
	}

	// Get value of key from properties file
	public static String getGlobalValue(String propertyKey) throws IOException {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(
				"D:\\APPIUM\\appium_workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
		properties.load(fis);
		return properties.getProperty(propertyKey);
	}

	// Get value of given key from json using JsonPath
	public String getJsonPath(Response response, String key) {
		String responseString = response.asString();
		JsonPath jsonPath = new JsonPath(responseString);
		return jsonPath.getString(key);
	}
}
