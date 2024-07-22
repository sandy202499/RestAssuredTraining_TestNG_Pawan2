package day4;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class ParsingJSONResponseData {
	
	//@Test(priority=1)
	void testJsonResponse()
	{
		
		// Approach-1
		
	/*	given()
			.contentType("ContentType.JSON")
		
		.when()
			.get("http://localhost:3000/store")
			
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json")
			.body("book[3].title", equalTo("The Lord of the Rings"));
		// String value = body.book[3].title;
		//assert.assertEquals(book[3].title, null);
		 * 
		 */
		
		// Approach-2
		
		Response res=given()
				.contentType(ContentType.JSON)
				
			.when()
				.get("http://localhost:3000/store");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/json");
		
		// System.out.println("The Json Path is:" +res.jsonPath().get("book[3].title"));
		
		String bookname=res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookname, "The Lord of the Rings");
		
		
	}
	
	
	@Test(priority=2)
	void testJsonResponseBodyData()
	{
		
				
		// Approach-2
		
		Response res=
			given()
				.contentType(ContentType.JSON)
				
			.when()
				.get("http://localhost:3000/store");
		
		/*
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/json");
		
		// System.out.println("The Json Path is:" +res.jsonPath().get("book[3].title"));
		
		String bookname=res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookname, "The Lord of the Rings");
		*/
		
		// Using JSONObject class
		
		JSONObject jo=new JSONObject(res.asString());   // converting response to json object type
		
		//Print all titles of books
		/* for(int i=0; i<jo.getJSONArray("book").length(); i++)
		{
			String booktitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println(booktitle);
							
		} */
		
		//searching for title of the book in json - validation 1
		boolean status=false;
		
		for(int i=0; i<jo.getJSONArray("book").length(); i++)
		{
			String booktitle=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			if(booktitle.equals("The Lord of the Rings"))
			{
				System.out.println("The book title is:" +booktitle);
				//Assert.assertEquals(status, true);
				status=true;
				break;
			}
										
		} 
		
		Assert.assertEquals(status, true);
		
		
		// validate total price of books - validation 2
		
		 double totalprice=0;
		 for(int i=0; i<jo.getJSONArray("book").length(); i++)
			{
				String price=jo.getJSONArray("book").getJSONObject(i).get("price").toString();
				
				double prices=Double.parseDouble(price);   // to convert string to number format
				totalprice=totalprice+(prices);     
											
			} 
		 System.out.println("total price of books is:" +totalprice);
		 Assert.assertEquals(totalprice, 526.0);
	}


}
