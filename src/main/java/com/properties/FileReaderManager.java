package com.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileReaderManager {

	public static Properties properties;

	public FileReaderManager() throws IOException {

		String path = System.getProperty("user.dir") + File.separator
				+ "src\\main\\java\\com\\properties\\Csquare.Properties";

		File file = new File(path);

		FileInputStream fileinput = new FileInputStream(file);

		properties = new Properties();

		properties.load(fileinput);
	}

	public String getHosturl() {

		String url = properties.getProperty("hosturl");

		return url;

	}

	public String getPathurl() {

		String pathurl = properties.getProperty("pathurl");

		return pathurl;
	}

	public String getPartyId() {

		String partyId = properties.getProperty("partyId");

		return partyId;
	}

	public String getPartyName() {

		String partyName = properties.getProperty("partyName");

		return partyName;
	}

	public String getPartyShortName() {

		String partyShortName = properties.getProperty("partyShortName");

		return partyShortName;
	}

	public String getGstin() {

		String gstin = properties.getProperty("gstin");

		return gstin;
	}

	public String getPan() {

		String pan = properties.getProperty("pan");

		return pan;
	}

	public String getCustomerRefId() {

		String customerRefId = properties.getProperty("customerRefId");

		return customerRefId;
	}

	public String getStateCode() {

		String stateCode = properties.getProperty("stateCode");

		return stateCode;
	}

	public String getMappedFcs() {

		String mappedFcs = properties.getProperty("mappedFcs");

		return mappedFcs;
	}

	public String getdlNum1() {

		String dlNum1 = properties.getProperty("dlNum1");

		return dlNum1;
	}

	public String getdlNum2() {

		String dlNum2 = properties.getProperty("dlNum2");

		return dlNum2;
	}

	public String getAddressLine1() {

		String addressLine1 = properties.getProperty("addressLine1");

		return addressLine1;
	}

	public String getAddressLine2() {

		String addressLine2 = properties.getProperty("addressLine2");

		return addressLine2;
	}

	public String getCity() {

		String city = properties.getProperty("city");

		return city;
	}

	public String getStateName() {

		String stateName = properties.getProperty("stateName");

		return stateName;
	}

	public String getCountry() {

		String country = properties.getProperty("country");

		return country;
	}

	public String getPincode() {

		String pincode = properties.getProperty("pincode");

		return pincode;
	}

	public String getMobileNum1() {

		String mobileNum1 = properties.getProperty("mobileNum1");

		return mobileNum1;
	}
// C2- customer order;

	public String getC2partyId() {

		String C2partyId = properties.getProperty("C2partyId");

		return C2partyId;
	}

	public String getcustomerOrderRefId() {

		String customerOrderRefId = properties.getProperty("customerOrderRefId");

		return customerOrderRefId;

	}

	public String gettotalItemCount() {

		String totalItemCount = properties.getProperty("totalItemCount");

		return totalItemCount;

	}

	public String getbuId() {

		String buId = properties.getProperty("buId");

		return buId;

	}

	public String getitemId() {

		String itemId = properties.getProperty("itemId");

		return itemId;

	}

	public String getquantity() {

		String quantity = properties.getProperty("quantity");

		return quantity;

	}

	public String getseqNum() {

		String seqNum = properties.getProperty("seqNum");

		return seqNum;

	}

	public String getCopathURL() {

		String CopathURL = properties.getProperty("CopathURL");

		return CopathURL;

	}

	public String getauUsername() {

		String auUsername = properties.getProperty("auUsername");

		return auUsername;

	}

	public String getauPassword() {

		String auPassword = properties.getProperty("auPassword");

		return auPassword;

	}
	
	public String getc2picklistURL() {
		
		String pathurl = properties.getProperty("c2picklistURL");
		
		return pathurl;
	}

}
