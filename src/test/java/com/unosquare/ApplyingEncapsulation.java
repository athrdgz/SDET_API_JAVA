package com.unosquare;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import bsh.ParseException;
import io.restassured.response.Response;

@Test
public class ApplyingEncapsulation {
	public void PostLogin() throws IOException, ParseException {

		ApiCore apiCore = new ApiCore();
		Response test = apiCore.POSTmethod("src\\main\\resources\\login.json", "/login");
		
		Assert.assertEquals(200, test.statusCode());
		
		test.then().assertThat().body("$", hasKey("token"));
		
		apiCore.logResponse(test);
	}
	
	public void PostCreate() throws IOException, ParseException {

		ApiCore apiCore = new ApiCore();
		Response test = apiCore.POSTmethod("src\\main\\resources\\users.json", "/users");
		
		Assert.assertEquals(201, test.statusCode());
		
		test.then().assertThat().body("$", hasKey("createdAt"));
		
		apiCore.logResponse(test);
	}
	
	public void PostRegister() throws IOException, ParseException {

		ApiCore apiCore = new ApiCore();
		Response test = apiCore.POSTmethod("src\\main\\resources\\login.json", "/register");
		
		Assert.assertEquals(200, test.statusCode());
		
		test.then().assertThat().body("$", hasKey("token"));
		
		apiCore.logResponse(test);
	}
	
	public void PostRegisterUnsuccessfu() throws IOException, ParseException {

		ApiCore apiCore = new ApiCore();
		Response test = apiCore.POSTmethod("src\\main\\resources\\users.json", "/register");
		
		Assert.assertEquals(400, test.statusCode());
		
		test.then().assertThat().body("$", hasKey("error"));
		
		apiCore.logResponse(test);
	}
	
	public void GetListUsers() throws IOException, ParseException {

		ApiCore apiCore = new ApiCore();
		Response test = apiCore.GETmethod("/users");
		
		Assert.assertEquals(200, test.statusCode());

		test.then().assertThat().body("$", hasKey("total"));
		test.then().assertThat().body("$", hasKey("data"));
		
		apiCore.logResponse(test);
	}
	
	public void GetUser() throws IOException, ParseException {

		ApiCore apiCore = new ApiCore();
		Response test = apiCore.GETmethod("/users/5");
		
		Assert.assertEquals(200, test.statusCode());

		test.then().assertThat().body("$", hasKey("data"));
		
		apiCore.logResponse(test);
	}
	
	public void GetUserNotFound() throws IOException, ParseException {

		ApiCore apiCore = new ApiCore();
		Response test = apiCore.GETmethod("/users/23");
		
		Assert.assertEquals(404, test.statusCode());
		
		apiCore.logResponse(test);
	}
	
	public void GetUserResource() throws IOException, ParseException {

		ApiCore apiCore = new ApiCore();
		Response test = apiCore.GETmethod("/unknown/2");
		
		Assert.assertEquals(200, test.statusCode());

		test.then().assertThat().body("$", hasKey("data")); 
		
		apiCore.logResponse(test);
	}
	
	public void PutUpdate() throws IOException, ParseException {

		ApiCore apiCore = new ApiCore();
		Response test = apiCore.PUTmethod("src\\main\\resources\\users.json", "/users/2");
		
		Assert.assertEquals(200, test.statusCode());

		test.then().assertThat().body("$", hasKey("updatedAt"));
		
		apiCore.logResponse(test);
	}
		

	@BeforeSuite
	public void beforeSuite() {
		
	}

}

