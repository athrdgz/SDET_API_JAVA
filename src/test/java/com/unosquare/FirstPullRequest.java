package com.unosquare;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;


public class FirstPullRequest {
  @Test
  public void http_objects() {
	RestAssured.baseURI = "https://reqres.in/api/";
	RequestSpecification httpRequest = RestAssured.given();
	Response response = httpRequest.get("/unknown/2");
	
	int statusCode = response.getStatusCode();

	// Assert that correct status code is returned.
	Assert.assertEquals(statusCode,200);
	Reporter.log("Sucess 200 validation");
	
	response.then().
					body("data.id", equalTo(2)).
					body("data.name", equalTo("fuchsia rose")).
					body("data.year", equalTo(2001)).
					body("data.color", equalTo("#C74375")).
					body("data.pantone_value", equalTo("17-2031"));
							
	Reporter.log(response.body().asString());
  }
  
  @Test
  public void given_when_then() {
	  
	  given()
	  .when()
	  	.get("https://reqres.in/api/unknown/2")
	  		.then().assertThat().statusCode(200).assertThat()
	  														 .contentType(ContentType.JSON)
	  														 .body("data.id", equalTo(2))
	  														 .body("data.name", equalTo("fuchsia rose"))
	  														 .body("data.year", equalTo(2001))
	  														 .body("data.color", equalTo("#C74375"))
	  														 .body("data.pantone_value", equalTo("17-2031"));
	  
	  Reporter.log("Sucess 200 validation");
}
  @BeforeMethod
  public void beforeMethod() {
  }

}
