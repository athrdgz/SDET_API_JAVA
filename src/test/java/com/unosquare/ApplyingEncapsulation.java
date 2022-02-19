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

public class ApplyingEncapsulation {
	@Test
	public void PostLogin() throws IOException, ParseException {

		ApiCore apiCore = new ApiCore();
		Response test = apiCore.PostLogin("src\\main\\resources\\login.json", "/login");
		
		Assert.assertEquals(200, test.statusCode());
		
		test.then().assertThat().body("$", hasKey("token"));
		
		apiCore.logResponse(test);
	}

	@BeforeSuite
	public void beforeSuite() {
		
	}

}

