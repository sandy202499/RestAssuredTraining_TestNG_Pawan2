package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 given()
 	Body, Content type, set cookies, add auth, add param, set headers info etc.........
 	
 when()
 	get, post, put, delete
 	
 then()
 	validate status code, extract response, extract headers cookies & response body.........
 	
 */

public class HTTPRequests {
	
	int id;
	
	@Test (priority=1)
	void getUsers()
	{
				
		//To validate list of users
		
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
	}
	
		// To validate Post request / Creating user
	
	@Test (priority=2)
	void createUser()
	{
		HashMap data=new HashMap();
		data.put("name", "Sanju");
		data.put("job", "Trainer");
		
		id=given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("https://reqres.in/api/users")
			
	/*	.then()
			.statusCode(201)
			.log().all();        // This will capture all the body */
		
		// If we want to capture responce then use below method
			
			.jsonPath().getInt("id");
			
			System.out.println("Generated id is:" +id);
			
	}
	
	    // To validate update user / put request
	
	@Test (priority=3, dependsOnMethods= {"createUser"})
	void updateUser()
	{
		HashMap data=new HashMap();
		data.put("name", "Saanvi");
		data.put("job", "Teacher");
		
		given()
				.contentType("application/json")
				.body(data)
				
			.when()
				.put("https://reqres.in/api/users/"+id)
				
			.then()
				.statusCode(200)
				.log().all();  
			
			// If we want to capture responce then use below method
					
			System.out.println("Updated id is:" +id);
		
	}
	
		// To validate delete user / delete request
	
	@Test (priority=4)
	void deleteUser()
	{
		
		given()
		
		.when()
			.delete("https://reqres.in/api/users/"+id)
		.then()
		.statusCode(204).log().all();
		
		System.out.println("Deleted id is:" +id);
		
	}

}
