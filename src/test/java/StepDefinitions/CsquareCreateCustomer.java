package StepDefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.testng.Assert;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.properties.ConfigurationReader;
import com.properties.PropertiesWriter;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import json_file_reader_and_writer.JsonFile_reader_Writer;

public class CsquareCreateCustomer {

	static Response response;
	static ArrayList<String> fc = new ArrayList<>();
	static ArrayList<Object> partyContactsList = new ArrayList<>();
	static HashMap<String, String> partyContacts = new HashMap<>();
	static int statusCode;
	static int returnCode;
	
	static String payLoadFilePath = System.getProperty("user.dir")+"./Json_Payload_and_Response/CreateCustomer/payload.json";
	static String responseBodyFilePath = System.getProperty("user.dir")+"./Json_Payload_and_Response/CreateCustomer/response.json";
	
	@Given("I am on an new customer Create API")
	public void i_am_on_an_new_customer_create_api() throws IOException, ConfigurationException {

		payloadCreate();
	}

	@Given("I create Csquare Customer")
	public void i_create_csquare_customer() {

		statusCode = response.getStatusCode();
	}

	@Then("I Validate expected response code with {int}")
	public void i_validate_expected_response_code_with(Integer int1) {

		System.out.println(statusCode);

		Assert.assertEquals(returnCode, int1);
	}

	@SuppressWarnings("unused")
	public static void payloadCreate() throws IOException, ConfigurationException {
		
		PropertiesWriter writer = new PropertiesWriter();
		
		RestAssured.baseURI = ConfigurationReader.getCR().getFRM().getHosturl();
		RequestSpecification httprequest = RestAssured.given().auth().basic("admin", "Rwos@2017");
		
		JsonObject jsonReader = JsonFile_reader_Writer.jsonReader(payLoadFilePath);
		
		JsonFile_reader_Writer.jsonUpdateNewValue("partyId", ConfigurationReader.getCR().getFRM().getPartyId());
		
		JsonFile_reader_Writer.jsonUpdateNewValue("partyName", ConfigurationReader.getCR().getFRM().getPartyName());
		
		JsonFile_reader_Writer.jsonUpdateNewValue("partyShortName", ConfigurationReader.getCR().getFRM().getPartyShortName());
		
		JsonFile_reader_Writer.jsonUpdateNewValue("customerRefId", ConfigurationReader.getCR().getFRM().getCustomerRefId());
		
		httprequest.header("Content-Type", "application/json");

		httprequest.body(jsonReader.toJson());

		response = httprequest.request(Method.POST, ConfigurationReader.getCR().getFRM().getPathurl());

		String bodyData = response.getBody().asString();
		System.out.println(bodyData);
		
		returnCode = response.jsonPath().getInt("returnCode");
		
		JsonFile_reader_Writer.jsonPayloadWriter(jsonReader, payLoadFilePath);
		
		JsonFile_reader_Writer.jsonResponseBodyWriter(response, responseBodyFilePath);
		
		
		
	}
	
	
		
	
}
