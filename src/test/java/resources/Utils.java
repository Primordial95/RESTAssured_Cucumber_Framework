package resources;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
		public static RequestSpecification request;
		
		public RequestSpecification requestSpecification() {
			try {
				if (request==null) {
				PrintStream printStream = new PrintStream("logging.txt");
				request = new RequestSpecBuilder().setBaseUri(loadGlobal("baseuri"))
						.addHeader("Authorization", "Bearer "+loadGlobal("auth")).noContentType()
						.addFilter(RequestLoggingFilter.logRequestTo(printStream))
						.addFilter(ResponseLoggingFilter.logResponseTo(printStream)).build();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return request;		
		}
		
		public static String loadGlobal(String key) {
			Properties properties = new Properties();
			try {
				FileInputStream fileInputStream = new FileInputStream("../StripeApiFramework/src/test/java/resources/global.properties");				
				properties.load(fileInputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return properties.getProperty(key);
		}
		
		public String getJsonPath(Response response,String name) {
			JsonPath jsonPath = new JsonPath(response.asString());
			return jsonPath.getString(name);
		}
}
