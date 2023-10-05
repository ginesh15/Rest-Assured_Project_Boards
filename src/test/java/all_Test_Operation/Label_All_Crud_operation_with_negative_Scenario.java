package all_Test_Operation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Reusable_Methods.Reusable_Data;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Label_All_Crud_operation_with_negative_Scenario extends Reusable_Data {
	
	 static String id = "";
	 String key = prop.getProperty("key");
	 
	 /***Reading the Properties from Configure.Properties file***/
		String token = prop.getProperty("token");
		String Labelname = prop.getProperty("Labelname");
		String Colorname = prop.getProperty("Colorname");
		String UpdtColorname = prop.getProperty("UpdtColorname");
	 
	 private static Logger logger = LogManager.getLogger(Label_All_Crud_operation_with_negative_Scenario.class);
		@BeforeTest
		public static void postfetchid() {
		 id = Reusable_Data.postfetchId();
		}
		
		/*********Negative Scenario Test Case for Get A Label************/
		@Test(priority=2)
		public void Get_negative_scenario() { 
			
			/***Create a Request pointing to the Service Endpoint***/
			RestAssured.baseURI = prop.getProperty("base_Url");
			RequestSpecification request = RestAssured.given();
			
			/***Fetching the Response by get the request***/
			Response responsee = request.headers(Reusable_Data.headerBodyHashMap())
					.queryParam("wrongkey", key)
					.queryParam("token", token)
					.when().get("/1/labels/"+id)
					.then().assertThat().statusCode(401)
					//.log().all()
					.extract().response();
	         
	        //responsee.prettyPeek();		       
	        logger.info("Label Get Created Successfully");		
			}
		
		/*********Test Case for Create A Label************/
		 @Test(priority=1)
			public void postList() {
		       				
			 /***Create a Request pointing to the Service Endpoint***/
				 RestAssured.baseURI = prop.getProperty("base_Url");
				 RequestSpecification request = RestAssured.given();
					
				 /***Fetching the Response by post the request***/	
				 Response responsee =request.headers(Reusable_Data.headerBodyHashMap())
						  .queryParam("color", Colorname)
						  .queryParam("name", Labelname)
						  .queryParam("idBoard",id)
						  .queryParam("key", key)
						  .queryParam("token", token)
		                  .when().post("/1/labels/")
						  .then().assertThat().statusCode(200)
						  //.log().all()
						  .extract().response();
					
				 JsonPath js = json(responsee);
			     id = js.get("id");
			     System.out.println("Created ID is:"+ id);
				 //responsee.prettyPeek();
				 logger.info("Label  Created Successfully");
			}

		 /*********Test Case for Update A Label************/
		 @Test(priority=3)
	     public void put() {
		
			 /***Create a Request pointing to the Service Endpoint***/
		     RestAssured.baseURI = prop.getProperty("base_Url");
	         RequestSpecification request = RestAssured.given();
	 
	         /***Fetching the Response by put the request***/	
	         Response responsee =request.headers(Reusable_Data.headerBodyHashMap())
				.queryParam("key", key)
				.queryParam("token", token)
				.queryParam("color", UpdtColorname)
				.when().put("/1/labels/"+id)
				.then().assertThat().statusCode(200)
                //.log().all()
                .extract().response();
	 
	         JsonPath js = json(responsee);
	         String id = js.get("id").toString();
	         System.out.println("Put ID is:"+ id);
	         String colour = js.get("color").toString();
	         /**Validate the assertion**/
	         Assert.assertEquals(colour, "yellow");
	         //responsee.prettyPeek(); 
	         logger.info("Label updated Successfully");
	 
	        } 
		 
		 /*********Test Case for Delete A Label************/
		 @Test(priority=4)
			public void Delete() { 
				
			 /***Create a Request pointing to the Service Endpoint***/
				RestAssured.baseURI = prop.getProperty("base_Url");
				RequestSpecification request = RestAssured.given();
				
				 /***Fetching the Response by delete the request***/	
				Response responsee = request.headers(Reusable_Data.headerBodyHashMap())
						.queryParam("key", key)
						.queryParam("token", token)
						.when().delete("/1/labels/"+id);
						
						//responsee.prettyPeek();  
						logger.info("Label Delete Successfully");
			

		 }
}
