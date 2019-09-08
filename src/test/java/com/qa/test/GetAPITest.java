package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;


public class GetAPITest extends TestBase{
	
	TestBase base;
	RestClient restClient;
	String URI;
	CloseableHttpResponse httpResponse;
	
	
	@BeforeMethod
	public void setUp() {
		base=new TestBase();
		String endpointURL=prop.getProperty("endpointURL");
		String serviceURL=prop.getProperty("serviceURL");
		URI=endpointURL+serviceURL;		
	}
	
	@Test(priority=1)
	public void getAPITestWithOUTHeader() throws ClientProtocolException, IOException {
		restClient= new RestClient();
		httpResponse=restClient.get(URI);
		
		
		//2 Status code
			
			int statusCode= httpResponse.getStatusLine().getStatusCode();
			System.out.println("Status code is ----> "+statusCode);
			Assert.assertEquals(RESPONSE_STATUS_CODE_200,statusCode,"Status code is not 200");
			
		//3 Header String
			
			Header[] headerArray=httpResponse.getAllHeaders();
			HashMap<String, String> allHeader=new HashMap<String, String>();
			for (Header header: headerArray) {
				allHeader.put(header.getName(), header.getValue());			
			}
			System.out.println("Header String is ---> " +allHeader);
			
		//4 JSON response
			
			String responseString=EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
			
			JSONObject responseJson=new JSONObject(responseString);
			System.out.println("JSON Response from the API is ---> "+responseJson);
	}
	
	@Test(priority=2)
	public void getAPITestWithHeader() throws ClientProtocolException, IOException {
		restClient= new RestClient();
		HashMap<String, String> addHeader= new HashMap<String, String>();
		addHeader.put("Content-Type", "application/json");
		addHeader.put("username", "karandeep");
		addHeader.put("password", "test@123");
		httpResponse=restClient.get(URI,addHeader);
		
		
		//2 Status code
			
			int statusCode= httpResponse.getStatusLine().getStatusCode();
			System.out.println("Status code is ----> "+statusCode);
			Assert.assertEquals(RESPONSE_STATUS_CODE_200,statusCode,"Status code is not 200");
			
		//3 Header String
			
			Header[] headerArray=httpResponse.getAllHeaders();
			HashMap<String, String> allHeader=new HashMap<String, String>();
			for (Header header: headerArray) {
				allHeader.put(header.getName(), header.getValue());			
			}
			System.out.println("Header String is ---> " +allHeader);
			
		//4 JSON response
			
			String responseString=EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
			
			JSONObject responseJson=new JSONObject(responseString);
			System.out.println("JSON Response from the API is ---> "+responseJson);
			
			//Single value assertion
			
			String s=TestUtil.getValueByJPath(responseJson, "/per_page"); // this will give us the value associated with this Key: per_page, which is 6 in out example
			System.out.println("Value of per page is ---->" +s);
			Assert.assertEquals(Integer.parseInt(s), 6);
			
			// getting the value from JSON array
			
			String id=TestUtil.getValueByJPath(responseJson, "/data[0]/id");
			String email=TestUtil.getValueByJPath(responseJson, "/data[0]/email");
			String first_name=TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
			String last_name=TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
			
			System.out.println("Value of id at data[0] is --->" +id);
			Assert.assertEquals(Integer.parseInt(id), 1);
			System.out.println("Value of email at data[0] is --->" +email);
			Assert.assertEquals(email, "george.bluth@reqres.in");
			System.out.println("Value of first_name at data[0] is --->" +first_name);
			Assert.assertEquals(first_name, "George");
			System.out.println("Value of last_name at data[0] is --->" +last_name);
			Assert.assertEquals(last_name, "Bluth");
						
	}
	
	

}
