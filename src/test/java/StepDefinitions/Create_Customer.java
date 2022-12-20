package StepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.github.cliftonlabs.json_simple.JsonObject;

import json_file_reader_and_writer.JsonFile_reader_Writer;

public class Create_Customer {
	
//	this class created for testing purpose not related to any-other classes
	
	static String filePath = System.getProperty("user.dir")+"/Json_Payload_and_Response/jsonReaderAndWriter/payload.json";
	
	public static void payload() throws StreamReadException, DatabindException, IOException {
		
		JsonObject jsonReader = JsonFile_reader_Writer.jsonReader(filePath);
		
		JsonFile_reader_Writer.jsonUpdateNewValue("partyId", "1512202102");
		
		JsonFile_reader_Writer.jsonUpdateNewValue("partyName", "NMS15 MEDICAL LIMITED [SP NMS15 MEDICAL LIMITED]");
		
		JsonFile_reader_Writer.jsonUpdateNewValue("partyShortName", "NMS15 MEDICAL LIMITED [SP NMS15 MEDICAL LIMITED]");
		
		JsonFile_reader_Writer.jsonUpdateNewValue("customerRefId", "NMS1512202102");
		
		@SuppressWarnings("unchecked")
		List<Map<Object, Object>> map = (List<Map<Object, Object>>) jsonReader.get("partyContacts");
		
		for (Map<Object, Object> map2 : map) {
			
			map2.put("city", "CHENNAI");
		}
		
		JsonFile_reader_Writer.jsonUpdateNewValue("partyContacts", map);
		
		System.out.println(map);
		
		JsonFile_reader_Writer.jsonPayloadWriter(jsonReader, filePath);
		
	}
	
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		
		payload();
	}

}
