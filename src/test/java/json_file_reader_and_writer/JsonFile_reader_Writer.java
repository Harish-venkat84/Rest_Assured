package json_file_reader_and_writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cliftonlabs.json_simple.JsonObject;

import io.restassured.response.Response;

public class JsonFile_reader_Writer {

	static String filePath = System.getProperty("user.dir")+"/Json_Payload_and_Response/jsonReaderAndWriter/payload.json";
	
	static JsonObject json;
	
	static ObjectMapper mapper = new ObjectMapper();;
	
	public static JsonObject jsonReader(String filePath) throws StreamReadException, DatabindException, IOException {
		
		@SuppressWarnings("unchecked")
		Map<String,Object> readValue = mapper.readValue(Paths.get(filePath).toFile(),Map.class);
		
		json = new JsonObject(readValue);
		
		return json;
		
	}
	
	public static void jsonPayloadWriter(Object object,String filePath) throws IOException {
		
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath),object);
	}
	
	public static void jsonResponseBodyWriter(Response response, String filePath) throws  IOException {
		
		FileWriter file = new FileWriter(filePath);
		file.write(response.getBody().asString());
        file.close();
		
	}
	
	public static void jsonUpdateNewValue(String key, Object value) {
		
		json.put(key, value);
	}
	
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		
//		jsonReader(filePath);
	}
}
