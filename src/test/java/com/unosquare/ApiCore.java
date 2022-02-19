package com.unosquare;

import java.io.File;

import org.testng.Reporter;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiCore {

	public Response PostLogin(String filePath, String url) {
		
		File jsonDataFile = new File(filePath);

		RestAssured.baseURI = "https://reqres.in/api";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.headers("Content-Type", "application/json");
		httpRequest.body(jsonDataFile);
		Response response = httpRequest.post(url);
		
		return response;
		
	}
	
	public void logResponse(Response response) {

		Reporter.log("Response body: " + response.body().asString());
		Reporter.log("Response code: " + response.statusCode());
		Reporter.log("URL: " + RestAssured.baseURI);
		
	}
}
