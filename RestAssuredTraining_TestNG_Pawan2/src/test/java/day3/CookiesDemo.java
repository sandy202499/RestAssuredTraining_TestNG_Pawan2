package day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;


public class CookiesDemo {
	
	//@Test(priority=1)
	void testCookies()
	{
		
		given()
		
		.when()
			.get("https://www.google.com/")
		.then()
			.cookie("AEC", "AVYB7coTOoLsgWaux_dd5s3R2rcF1C_qLCXaNswZGZ7pNeYXTBTC2dQJGQ")
			.log().all();
		
	}
	
	
	@Test(priority=2)
	void getCookiesInfo()
	{
		
		Response res=given()
		
		.when()
			.get("https://www.google.com/");
		
		// get single cookie info
		
		// String cookie_value=res.getCookie("AEC");
		// System.out.println("Value of the cookie is ===>" +cookie_value);
		
		// get all cookie info
		
		Map<String,String> cookies_values=res.getCookies();     // Map is a type of variable nothing but HashMap and will return key and value pairs
		
		//System.out.println(cookies_values.keySet());      // It will return only keys
		
		for(String k: cookies_values.keySet())
		{
			String cookie_value = res.getCookie(k);
			System.out.println(k+":  "+cookie_value);
		}
		
	}

}
