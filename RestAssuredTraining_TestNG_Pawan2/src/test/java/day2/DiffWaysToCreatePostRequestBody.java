package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/*
 * Different ways to create POST request body
 * 1) Post request body using Hashmap
 * 2) Post request body creation using Org.JSON
 * 3) Post request body creation using POJO class
 * 4) Post request using external json file data
 */




public class DiffWaysToCreatePostRequestBody {
	
	
	//int id;
	
	
	// 1) Post request body using Hashmap ***************************
	
	//@Test(priority=1)
	void testPostUsingHashMap()
	{
	
		HashMap data = new HashMap();
		
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "2353256");
		
		String courseArr[] = {"C", "C++"};
		
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("2353256"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", "application/json")
			.log().all();
		
		
	}
	

	            //2) Post request body creation using Org.JSON **************************************
	           // For this we have to add json dependency in POM.XML
	
	//@Test(priority=1)
	void testPostUsingJsonLibrary()
	{
	
		JSONObject data = new JSONObject();
		data.put("name", "David");
		data.put("location", "France");
		data.put("phone", "4128859234");
		
		String coursesArr[] = {"C", "C++"};
		data.put("courses", coursesArr);
		
		given()
			.contentType("application/json")
			.body(data.toString())
			
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("David"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("4128859234"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", "application/json")
			.log().all();
		
		
	}
	
	// 3) Post request body creation using POJO class*******************************
	      // Getter and Setter's methods
	
	//@Test(priority=1)
	void testPostUsingPOJO()
	{
	
		Pojo_PostRequest data = new Pojo_PostRequest();
		data.setName("Scott David1");
		data.setLocation("SC");
		data.setPhone("42455626");
		
		String coursesArr[] = {"C", "C++"};
		data.setCourses(coursesArr);
		
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott David1"))
			.body("location", equalTo("SC"))
			.body("phone", equalTo("42455626"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", "application/json")
			.log().all();
		
		System.out.println("Name of the variable is***************:" +data.getName());
	}
	
			// 4) Post request using external json file data ****************************
	
	@Test(priority=1)
	void testPostUsingTxternalJsonFile() throws FileNotFoundException
	{
	
		File f = new File(".\\body.json");
		
		FileReader fr =new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
		given()
			.contentType("application/json")
			.body(data.toString())           // We have to convert Json object data into string
			
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("Smith John D"))
			.body("location", equalTo("NC"))
			.body("phone", equalTo("78813524"))
			.body("courses[0]", equalTo("Java"))
			.body("courses[1]", equalTo("Selenium"))
			.header("Content-Type", "application/json")
			.log().all();
		
		//System.out.println("Name of the variable is***************:" +data.getName());
	}
	
	
	
	       //deleting student record
	
	//@Test(priority=2)
	void testDelete()
	{
		given()
		
		.when()
			.delete("http://localhost:3000/students/")
			
		.then()
			.statusCode(200)
			.log().all();
			
		
		
	}

}
