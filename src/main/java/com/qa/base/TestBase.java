package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	public int RESPONSE_STATUS_CODE_200=200;
	public static Properties prop;
	
	public TestBase() {
		
		 prop=new Properties();
		try {
			FileInputStream file=new FileInputStream("D:\\workspace\\RestAPITest\\restapi\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found ");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO exception occured");
		} 
		
	}
}
