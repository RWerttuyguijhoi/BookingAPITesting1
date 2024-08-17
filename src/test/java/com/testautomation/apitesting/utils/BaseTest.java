package com.testautomation.apitesting.utils;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class BaseTest {
@Test	
public void BeforeMethod() {
	
	RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	
}
}
