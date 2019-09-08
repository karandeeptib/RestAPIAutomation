package com.qa.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetAPITest extends TestBase{
	
	TestBase base;
	RestClient restClient;
	String URI;
	
	
	@BeforeMethod
	public void setUp() {
		base=new TestBase();
		String endpointURL=prop.getProperty("endpointURL");
		String serviceURL=prop.getProperty("serviceURL");
		URI=endpointURL+serviceURL;		
	}
	
	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		restClient= new RestClient();
		restClient.get(URI);
	}
	
	

}
