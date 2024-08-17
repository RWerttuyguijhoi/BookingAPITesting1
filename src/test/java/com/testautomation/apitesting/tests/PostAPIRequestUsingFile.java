package com.testautomation.apitesting.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.testautomation.apitesting.utils.FileNameConstants;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;



public class PostAPIRequestUsingFile {
	@Test
	public void postAPIRequest() {
		try {
    String postAPIRequestBody = FileUtils.readFileToString(new File(FileNameConstants.POST_API_REQUEST_BODY),"UTF-8");
    Response response=
   RestAssured
   .given()
   .contentType(ContentType.JSON)
   .body(postAPIRequestBody)
   .baseUri("https://restful-booker.herokuapp.com/booking")
	.when()
	.post()
	.then()
	.assertThat()
	.statusCode(200)
	.extract()
	.response();
    
    JSONArray jsonArray = JsonPath.read(response.body().asString(),"$.booking..firstname");
	String firstname = (String)jsonArray.get(0);
     Assert.assertEquals(firstname, "Tester");
     
     JSONArray jsonArraylastname = JsonPath.read(response.body().asString(),"$.booking..lastname");
 	String lasttname = (String)jsonArraylastname.get(0);
      Assert.assertEquals(lasttname,"Talk");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			 RestAssured
			    .given()
			    .contentType(ContentType.JSON)
			     .baseUri("https://restful-booker.herokuapp.com/booking")
			    .when()
			    .get("{bookingID}")
			    .then()
			    .assertThat()
			    .statusCode(200);
			
		}
	}

}
