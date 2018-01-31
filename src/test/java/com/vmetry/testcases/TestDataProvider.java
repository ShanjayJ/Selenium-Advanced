package com.vmetry.testcases;

import org.testng.annotations.DataProvider;

public class TestDataProvider {
	
	@DataProvider(name = "LoginVerification Data")
	public Object[][] getData() {
		
		Object data[][] = new Object[3][6];
		
		data[0][0] = "Normal";
    	data[0][1] = "username1";
    	data[0][2] = "userMailID1@gmail.com";
    	data[0][3] = "pass123";
    	data[0][4] = "fbuserMailID@gmail.com";
    	data[0][5] = "fbpass123";
    	
    	data[1][0] = "Normal";
    	data[1][1] = "username2";
    	data[1][2] = "userMailID2@gmail.com";
    	data[1][3] = "pass1234";
    	data[1][4] = "fbuserMailID2@gmail.com";
    	data[1][5] = "fbpass1234";
    	
    	data[2][0] = "Facebook";
    	data[2][1] = "username3";
    	data[2][2] = "userMailID3@gmail.com";
    	data[2][3] = "pass12345";
    	data[2][4] = "fbuserMailID3@gmail.com";
    	data[2][5] = "fbpass12345";

		
		return data;
		
	}

}
