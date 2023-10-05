package all_Test_Operation;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import Reusable_Methods.Reusable_Data;

import org.testng.annotations.Test;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Board_All_Crud_operation_Test extends Reusable_Data{
	
	static String id = "";
	
	/***Reading the Properties from Configure.Properties file***/ 
	String key = prop.getProperty("key");
	String token = prop.getProperty("token");
	String Boardname = prop.getProperty("Boardname");
	String UpdtBoardname = prop.getProperty("UpdtBoardname");
	
	private static Logger logger = LogManager.getLogger(Board_All_Crud_operation_Test.class);
	
	
	/*********Test Case for Get A Board************/
	@Test(priority=2)
	public void Get() { 
		
		/***Create a Request pointing to the Service Endpoint***/
		RestAssured.baseURI = prop.getProperty("base_Url");
		RequestSpecification request = RestAssured.given();
		
		/***Fetching the Response by get the request***/
		Response responsee = request.headers(Reusable_Data.headerBodyHashMap())
				.queryParam("key",key)
				.queryParam("token",token)
				.when().get("/1/boards/"+id)
				.then().assertThat().statusCode(200)
				//.log().all()
				.extract().response();
         
		JsonPath js = json(responsee);
        String id = js.get("id").toString();
        System.out.println("Get ID is:"+ id);
        //responsee.prettyPeek();		       
		
        logger.info("Board Get Created Successfully");
		
		}
	
	/*********Test Case for Create A Board************/
	@Test(priority=1)
	
	public void post() {
		
		/***Create a Request pointing to the Service Endpoint***/ 
		 RestAssured.baseURI = prop.getProperty("base_Url");
		 RequestSpecification request = RestAssured.given();
				
		 /***Fetching the Response by post the request***/	
		 Response responsee =request.headers(Reusable_Data.headerBodyHashMap())
				  .queryParam("name",Boardname)
				  .queryParam("key",key)
				  .queryParam("token",token)
                  .when().post("/1/boards/")
				  .then().assertThat().statusCode(200)
				  //.log().all()
				  .extract().response();
			
		 JsonPath js = json(responsee);
	     id = js.get("id");
	     System.out.println("Created ID is:"+ id);
		 //responsee.prettyPeek();	
		 
		 logger.info("Board Created Successfully");
	}
	
	/*********Test Case for update A Board************/
	@Test(priority=3)
       public void put() {
		
		/***Create a Request pointing to the Service Endpoint***/
		RestAssured.baseURI = prop.getProperty("base_Url");
        RequestSpecification request = RestAssured.given();
        
        /***Fetching the Response by put the request***/	
        Response responsee =request.headers(Reusable_Data.headerBodyHashMap())
        		.queryParam("name",UpdtBoardname)
				.queryParam("key", key)
				.queryParam("token",token)
				.when().put("/1/boards/"+id)
				.then().assertThat().statusCode(200)
				//.log().all()
				.extract().response();
        
        JsonPath js = json(responsee);
        String id = js.get("id").toString();
        System.out.println("Put ID is:"+ id);
        String nam = js.get("name").toString();
        /**Validate the assertion**/
        Assert.assertEquals(nam, "NagarroBoardMember");
        //responsee.prettyPeek();   
        logger.info("Board Updated Successfully");
        
	} 
	
	/*********Test Case for Delete A Board************/
	//@Test(priority = 4)
	public void delete(){ 
		
		/***Create a Request pointing to the Service Endpoint***/
		RestAssured.baseURI = prop.getProperty("base_Url");
        RequestSpecification request = RestAssured.given();
        
        /***Fetching the Response by delete the request***/	
        Response responsee =request.headers(Reusable_Data.headerBodyHashMap())
        		.queryParam("name",UpdtBoardname)
				.queryParam("key", key)
				.queryParam("token",token)
				.when().delete("/1/boards/"+id);
				
        
        //responsee.prettyPeek();
        
        logger.info("Board Deleted Successfully");
	}

}
