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

public class Card_All_Crud_operation extends Reusable_Data {
	
	static String id = "";
	
	/***Reading the Properties from Configure.Properties file***/
	String key = prop.getProperty("key");
	String token = prop.getProperty("token");
	String Cardname = prop.getProperty("Cardname");
	String UpdtCardname = prop.getProperty("UpdtCardname");
	
	private static Logger logger = LogManager.getLogger(Card_All_Crud_operation.class);
	
	 /***Call the method from reusuabledata class to crate Id before Test***/ 
	@BeforeTest
	public static void postfetchid123() {
		 id = Reusable_Data.GetFetchlist_id();
		 
	}
	
	/*********Test Case for Get A Card************/
	@Test(priority=2)
	public void Get() { 
		
		/***Create a Request pointing to the Service Endpoint***/
		RestAssured.baseURI = prop.getProperty("base_Url");
		RequestSpecification request = RestAssured.given();
		
		/***Fetching the Response by get the request***/
		Response responsee = request.headers(Reusable_Data.headerBodyHashMap())
				.queryParam("key",key)
				.queryParam("token", token)
				.when().get("/1/cards/"+id)
				.then().assertThat().statusCode(200)
				//.log().all()
				.extract().response();
         
		JsonPath js = json(responsee);
        String id = js.get("id").toString();
        System.out.println("Get ID is:"+ id);
        //responsee.prettyPeek();		       
        logger.info("Cards Get Created Successfully");		
		}
	
	/*********Test Case for Create A Card************/
     @Test(priority=1)
	
	 public void postcard() {
    	 
    	 /***Create a Request pointing to the Service Endpoint***/		 
		 RestAssured.baseURI = prop.getProperty("base_Url");
		 RequestSpecification request = RestAssured.given();
					
		 /***Fetching the Response by post the request***/	
		 Response responsee =request.headers(Reusable_Data.headerBodyHashMap())
				  .queryParam("name", Cardname)
				  .queryParam("idList",id)
				  .queryParam("key",key)
				  .queryParam("token",token)
                  .when().post("/1/cards")
				  .then().assertThat().statusCode(200)
				  //.log().all()
				  .extract().response();
			
		 JsonPath js = json(responsee);
	     id = js.get("id");
	     System.out.println("Created ID is:"+ id);
		 //responsee.prettyPeek();
		 logger.info("Cards  Created Successfully");	
	  }
     
     /*********Test Case for Update A Card************/
     @Test(priority=3)
     public void put() {
    	 
    	 /***Create a Request pointing to the Service Endpoint***/
	     RestAssured.baseURI = prop.getProperty("base_Url");
         RequestSpecification request = RestAssured.given();
 
         /***Fetching the Response by put the request***/	
         Response responsee =request.headers(Reusable_Data.headerBodyHashMap())
			.queryParam("key",key)
			.queryParam("token",token)
			.queryParam("name",UpdtCardname)
			.when().put("/1/cards/"+id)
			.then().assertThat().statusCode(200)
			//.log().all()
			.extract().response();
 
          JsonPath js = json(responsee);
          String id = js.get("id");
          System.out.println("Put ID is:"+ id);
          String nam = js.get("name").toString();
          /**Validate the assertion**/
          Assert.assertEquals(nam, "NagarroC");
          //responsee.prettyPeek(); 
          logger.info("Cards updated Successfully");	
          } 
     /*********Test Case for Delete A Card************/
      @Test(priority = 4)
      public void delete(){ 
    	  
    	  /***Create a Request pointing to the Service Endpoint***/
	      RestAssured.baseURI = prop.getProperty("base_Url");
          RequestSpecification request = RestAssured.given();
    
          /***Fetching the Response by delete the request***/	
          Response responsee =request.headers(Reusable_Data.headerBodyHashMap())
			.queryParam("key",key)
			.queryParam("token",token)
			.when().delete("/1/cards/"+id);
    
          //responsee.prettyPeek();
          logger.info("Cards Delete Successfully");	
        }
}
