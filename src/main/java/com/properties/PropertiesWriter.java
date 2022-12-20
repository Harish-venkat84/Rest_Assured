package com.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class PropertiesWriter {

	static Properties properties;
	
	static String path = System.getProperty("user.dir")+"./src/main/java/com/properties/Csquare.Properties";

	static String newpartyId;
	static String newPartyname;
	static String partyShortName;
	static String customerRefId;
	
	public PropertiesWriter() throws IOException, ConfigurationException {
	
		String partyId = ConfigurationReader.getCR().getFRM().getPartyId();
		
		int parseInt = Integer.parseInt(partyId);
		
		parseInt = parseInt+1;		
		
		newpartyId = Integer.toString(parseInt);
		
		String partyName = ConfigurationReader.getCR().getFRM().getPartyName(); 
		String[] split = partyName.split(" ");
		
		for (int i = 0; i < split.length; i++) {
			
			if (i == 0) {
				
				newPartyname = "NMS"+newpartyId;
				
			}else if (i == 4) {
				
				newPartyname = newPartyname+" "+"NMS"+newpartyId;
			
			}else {
				
				newPartyname = newPartyname+" "+split[i];
			}
			
		}
		
		partyShortName = newPartyname;
		
		customerRefId = "NMS"+newpartyId;
		
		write(newpartyId, newPartyname, partyShortName, customerRefId);
	}
	
	
	public static void write(String partyId, String partyName,String partyShortName, String customerRefId ) throws IOException, ConfigurationException {
		
		FileInputStream in = new FileInputStream(path);
		
		properties = new Properties();
		
		properties.load(in);
    	
		in.close();
    	
//		we can use below format for write or updating new data on the properties file. this is safes-way to use, without interfering other data
		
		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
		    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
		    .configure(params.properties()
		        .setFileName(path));
		Configuration config = builder.getConfiguration();
		
		config.setProperty("partyId", partyId); 
		config.setProperty("partyName", partyName);
		config.setProperty("partyShortName", partyShortName);
		config.setProperty("customerRefId", customerRefId);
		builder.save();
	}
	
	public static void main(String[] args) throws IOException, ConfigurationException {
		
		@SuppressWarnings("unused")
		PropertiesWriter aa = new PropertiesWriter();
		
//		write(newpartyId, newPartyname, partyShortName, customerRefId);
	}
}
