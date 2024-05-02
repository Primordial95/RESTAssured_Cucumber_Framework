package stepdefinitions;

import static org.junit.Assert.*;
import io.cucumber.java.en.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.ApiResources;
import resources.Conversion;
import resources.TestData;
import resources.Utils;

import static io.restassured.RestAssured.*;

public class stepDefinitions extends Utils {
	RequestSpecification req;
	TestData tData = new TestData();
	Response response;
	ResponseSpecification res;
	static String customer_Id;
	Conversion conversion = new Conversion();

	@Given("Add customer payload {string} {string}")
	public void add_customer_payload(String name, String email) {
		req = given().spec(requestSpecification()).contentType(ContentType.URLENC)
				.formParams(conversion.convertFormParam(name, email))
				.formParams(conversion.convertFormParamInt());
	}

	@When("user calls {string} using {string} request")
	public void user_calls_using_request(String resource, String httpRequest) {	
		res=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		String apiEnumValue;
		if(httpRequest.equalsIgnoreCase("post")) {
			ApiResources apiResources=ApiResources.valueOf(resource);
		response = req.when().post(apiResources.getResource());
		}
		else if (httpRequest.equalsIgnoreCase("Get")) {
			apiEnumValue=ApiResources.GETCUSTOMERAPI.getResource().replace("{path}", customer_Id);
			response=req.when().get(apiEnumValue);
		}
		else if (httpRequest.equalsIgnoreCase("delete")) {
			apiEnumValue=ApiResources.DELETE_CUSTOMER_API.getResource().replace("{path}", customer_Id);
			response=req.when().delete(apiEnumValue);
		}
	}

	@Then("the api calls get success with status code {int}")
	public void the_api_calls_get_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String variable, String value) {
		String object=getJsonPath(response, variable);
		assertEquals(object, value);
	}
	
	@Then("verify using customerid that {string} is created using {string} request")
	public void verify_using_customerid_that_is_created_using_request(String name, String resource) {
		customer_Id=getJsonPath(response, "id");
		req=given().spec(requestSpecification());
		user_calls_using_request(resource, "get");
		String nameString=getJsonPath(response, "name");
		assertEquals(name, nameString);
	}
	
	@Then("delete created customer using customerid using {string}")
	public void delete_created_customer_using_customerid_using(String resource) {
		user_calls_using_request(resource,"delete");
		JsonPath jsonPath = new JsonPath(response.asString());
		boolean expected=jsonPath.getBoolean("deleted");
		assertTrue(expected);
	}
}
