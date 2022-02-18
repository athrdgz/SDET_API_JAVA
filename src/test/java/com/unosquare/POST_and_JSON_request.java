package com.unosquare;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import java.io.File;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Test
public class POST_and_JSON_request {
	public void create() {

		File jsonDataFile = new File("src\\main\\resources\\createUser.json");

		RestAssured.baseURI = "https://reqres.in/api";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.headers("Content-Type", "application/json");
		httpRequest.body(jsonDataFile);
		Response response = httpRequest.post("/users");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode,201);
		
		response.then().assertThat()
						.body("name", equalTo("Athziri"))
						.body("job", equalTo("QA"));
		
		Reporter.log("Response body: " + response.body().asString());
		Reporter.log("Response code: " + statusCode);
		Reporter.log("JSON body: " + jsonDataFile);
		Reporter.log("URL: " + RestAssured.baseURI);

	}

	public void login() {

		File jsonDataFile = new File("src\\main\\resources\\login.json");

		RestAssured.baseURI = "https://reqres.in/api";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.headers("Content-Type", "application/json");
		httpRequest.body(jsonDataFile);
		Response response = httpRequest.post("/login");

		int statusCode = response.getStatusCode();
		
		response.then().assertThat()
	      .statusCode(200)
	      .body("$", hasKey("token"));
		
		Reporter.log("Response body: " + response.body().asString());
		Reporter.log("Response code: " + statusCode);
		Reporter.log("JSON body: " + jsonDataFile);
		Reporter.log("URL: " + RestAssured.baseURI);

	}

	@BeforeMethod
	public void beforeMethod() {
	}

}
