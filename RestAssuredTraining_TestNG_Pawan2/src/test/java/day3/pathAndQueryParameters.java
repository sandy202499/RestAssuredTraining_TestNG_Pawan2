package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class pathAndQueryParameters {
	
	
	// https://reqres.in/api/users?page=2&id=5
	
	@Test
	void testQueryAndPathParameters()
	{
		given()
			.pathParam("mypath", "users")
			.queryParam("page", 2)
			.queryParam("id", 5)
		
		.when()
			.get("https://reqres.in/api/{mypath}")        // Here only specify path param variable, no need to specify query param 
			                                              // it will go along with the request
		.then()
			.statusCode(200)
			.log().all(); 
		
		
	}

}
