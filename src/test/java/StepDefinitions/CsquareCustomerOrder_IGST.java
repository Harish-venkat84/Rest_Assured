package StepDefinitions;

import java.io.IOException;
import java.util.ArrayList;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.properties.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import json_file_reader_and_writer.JsonFile_reader_Writer;

public class CsquareCustomerOrder_IGST {

	static Response response;
	
	static String createOrderPayload = System.getProperty("user.dir")+"/Json_Payload_and_Response/CreateOrder/payload.json";
	static String createOrderResponse = System.getProperty("user.dir")+"/Json_Payload_and_Response/CreateOrder/response.json";
	
	static String picklistPayload = System.getProperty("user.dir")+"/Json_Payload_and_Response/pick_list/payload.json";
	static String picklistResponse = System.getProperty("user.dir")+"/Json_Payload_and_Response/pick_list/response.json";

	static String username;
	static String password;

	static String C2orderID;
	static String batchId;
	static String customerOrderRefId;

	static RequestSpecification httprequest;

	public static void baseURI() throws IOException {

		RestAssured.baseURI = ConfigurationReader.getCR().getFRM().getHosturl();
	}

	@Test(priority = 1)
	public static void C2order() throws IOException {

		username = ConfigurationReader.getCR().getFRM().getauUsername();
		password = ConfigurationReader.getCR().getFRM().getauPassword();

		JsonObject jsonReader = JsonFile_reader_Writer.jsonReader(createOrderPayload);

		baseURI();

		httprequest = RestAssured.given().auth().basic(username, password);

		httprequest.header("Content-Type", "application/json");

		httprequest.body(jsonReader.toJson());

		response = httprequest.post(ConfigurationReader.getCR().getFRM().getCopathURL());

		String body = response.getBody().asString();

		System.out.println(body);

		C2orderID = response.jsonPath().get("returnData.transactionId");

		System.out.println(C2orderID);
		
		JsonFile_reader_Writer.jsonPayloadWriter(jsonReader, createOrderPayload);
		
		JsonFile_reader_Writer.jsonResponseBodyWriter(response, createOrderResponse);
	}

	@Test(priority = 2)
	public void generatepicklist() throws IOException {

		ArrayList<String> txnid = new ArrayList<>();
		txnid.add(C2orderID);

		JsonObject jsonReader = JsonFile_reader_Writer.jsonReader(picklistPayload);
		
		jsonReader.put("customerOrderTxnIds", txnid);

		httprequest.header("Content-Type", "application/json");

		httprequest.body(jsonReader.toJson());

		response = httprequest.post(ConfigurationReader.getCR().getFRM().getc2picklistURL());

		String body = response.getBody().asString();

		System.out.println(body);

		batchId = response.jsonPath().get("returnData[0].itemDetails[0].batchDetails[0].batchId");

		System.out.println(batchId);
		
		JsonFile_reader_Writer.jsonPayloadWriter(jsonReader, picklistPayload);
		
		JsonFile_reader_Writer.jsonResponseBodyWriter(response, picklistResponse);
		
	}
	
	public void makeinvoice() throws IOException {

		customerOrderRefId = ConfigurationReader.getCR().getFRM().getcustomerOrderRefId();

		String pathurl = "/picklists/" + customerOrderRefId + "/invoice?branchCode=768&cmd=create&partShipmentOrderId="
				+ customerOrderRefId + "-S1";

		String getquantity = ConfigurationReader.getCR().getFRM().getquantity();

		int qt = Integer.parseInt(getquantity);

		ArrayList<Object> inv = new ArrayList<>();

		JsonObject invoice = new JsonObject();
		invoice.put("itemId", ConfigurationReader.getCR().getFRM().getitemId());
		invoice.put("batchId", batchId);
		invoice.put("qty", qt);

		inv.add(invoice);

		baseURI();

		response = given().auth().basic(username, password).basePath(pathurl).contentType("application/json").body(inv)
				.when().post();

		String body = response.getBody().asString();

		System.out.println(body);
	}

}
