package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	//String url="https://reqres.in/api/users";
	
	//1 GET Method
	
	public void get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient= HttpClients.createDefault();
		HttpGet httpGet=new HttpGet(url);  //http GET request
		CloseableHttpResponse httpResponse= httpClient.execute(httpGet); //hit the GET url and fetch the response
		
	//2 Status code
		
		int statusCode= httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code is ----> "+statusCode);
		
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

}
