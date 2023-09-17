package com.assignment.tests;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.assignment.models.LoginModel;
import com.assignment.properties.EndPoints;
import com.assignment.properties.Path;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import static org.hamcrest.Matchers.lessThan;

import java.util.concurrent.TimeUnit;

public class APITest {
	
	public String getToken;
	public int bookingId;
	
	RequestSpecBuilder requestBuilder;
	static RequestSpecification  requestSpec;
	
	ResponseSpecBuilder responseBuilder;
	static ResponseSpecification responseSpec;
	
	@BeforeClass
	public void setUp() {
		  requestBuilder =new RequestSpecBuilder();
		  requestBuilder.setBaseUri(Path.BASE_URI); 
		  requestBuilder.addQueryParam("cookie",getToken); 
		  requestSpec = requestBuilder.build();
		  
		  responseBuilder= new ResponseSpecBuilder();
		  responseBuilder.expectStatusCode(200);
		  responseBuilder.expectResponseTime(lessThan(9L), TimeUnit.SECONDS);
		  responseSpec = responseBuilder.build();
	}
	
	@Test
	public void generateToken() {
		LoginModel login = new LoginModel();
		login.setUsername("admin");
		login.setPassword("password123");
		Response res =
		given()
			.contentType("application/json")
			.body(login)
	   .when()
			.post(EndPoints.AUTH);
		getToken= res.jsonPath().getString("token");
	}
	
	@Test(dependsOnMethods="generateToken")
	public void createBookingDetails() {
		Response res=
		given()
			.spec(requestSpec)
			.contentType("application/json")
			.log().all()
			.body("{"
					   + "\"firstname\" : \"Jim\","
					   +"\"lastname\" : \"Brown\","
					   + "\"totalprice\" : 111,"
					   +"\"depositpaid\" : true,"
					   + "\"bookingdates\" : {"
					   +   "\"checkin\" : \"2018-01-01\","
					   +  "\"checkout\" : \"2019-01-01\""
					   + "},"
					   + "\"additionalneeds\" : \"Breakfast\""
					   +"}")
			.cookie("token", getToken)
	   .when()
			.post(EndPoints.CREATE)
	   .then()	
	   		.spec(responseSpec)
	   		.extract()
	   		.response();

		bookingId = res.path("bookingid");
	}
	
	@Test(dependsOnMethods="createBookingDetails")
	public void getBookingDetails() {
		
		given()
		     .spec(requestSpec)
			 .contentType("application/json")
			 .log().all()
	   .when()
			.get(EndPoints.GET+bookingId)
	   .then()	
	   		.spec(responseSpec)
		    .log().all();
		
	}
	
	@Test(dependsOnMethods="getBookingDetails")
	public void updateBookingDetails() {
		
		given()
			 .contentType("application/json")
			 .cookie("token", getToken)
			 //.log().all()
			 .body("{"
					   + "\"firstname\" : \"Neville\","
					   +"\"lastname\" : \"Menezes\","
					   + "\"totalprice\" : 111,"
					   +"\"depositpaid\" : true,"
					   + "\"bookingdates\" : {"
					   +   "\"checkin\" : \"2018-01-01\","
					   +  "\"checkout\" : \"2019-01-01\""
					   + "},"
					   + "\"additionalneeds\" : \"Breakfast\""
					   +"}")
	   .when()
			.put(EndPoints.UPDATE+bookingId)
	   .then()	
		   	.statusCode(200)
		 	.log().all();
	}
	
	
	@Test(dependsOnMethods="updateBookingDetails")
	public void deleteBookingDetails() {
		
		given()
			 .contentType("application/json")
			 .cookie("token", getToken)
			 .log().all()
	   .when()
			.delete(EndPoints.DELETE+bookingId)
	   .then()	
		   	.statusCode(201)
		 	.log().all();
		
	}
	
	

}
