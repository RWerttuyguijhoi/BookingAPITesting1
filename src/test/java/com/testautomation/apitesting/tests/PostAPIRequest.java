package com.testautomation.apitesting.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.testautomation.apitesting.utils.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class PostAPIRequest extends BaseTest{
	
	@Test
	public void creatingbooking() {
		
		JSONObject booking = new JSONObject();
		
		booking.put("firstname", "Tester");
		booking.put("lastname", "Talk");
		booking.put("totalprice", 111);
		booking.put("depositpaid", true);
		booking.put("additionalneeds", "super bowls");
			
		JSONObject bookingDates = new JSONObject();
		bookingDates.put("checkin", "2018-01-01");
		bookingDates.put("checkout", "2019-01-0");
        booking.put("bookingdates", bookingDates);

        Response response =
        RestAssured
    .given()
    .contentType(ContentType.JSON)
    .body(booking.toString())
	.baseUri("https://restful-booker.herokuapp.com/booking")
	//.log().body()  //create logs .header()or .All() we can  used
	.when()
	.post()
	.then()
	.assertThat()
	.log().body()
	.statusCode(200)
	.body("booking.firstname",Matchers.equalTo("Tester"))
	.body("booking.totalprice",Matchers.equalTo(111))
	
	.extract().response();
    int bookingId = response.path("bookingid");
    
    RestAssured
    .given()
    .contentType(ContentType.JSON)
    .pathParam("bookingID", bookingId)
    .baseUri("https://restful-booker.herokuapp.com/booking")
    .when()
    .get("{bookingID}")
    .then()
    .assertThat()
    .statusCode(200)
    .body("firstname",Matchers.equalTo("Tester"))
    .body("lastname",Matchers.equalTo("Talk"));
		
}
}