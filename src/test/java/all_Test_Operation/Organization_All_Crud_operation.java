package all_Test_Operation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import Reusable_Methods.Reusable_Data;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Organization_All_Crud_operation extends Reusable_Data {
	
    static String id = "";
    
    /***Reading the Properties from Configure.Properties file***/
    String key = prop.getProperty("key");
	String token = prop.getProperty("token");
	String Organizationname = prop.getProperty("Organizationname");
	String UpdtOrganizationname = prop.getProperty("UpdtOrganizationname");
    
    private static Logger logger = LogManager.getLogger(Organization_All_Crud_operation.class);
    
    /*********Test Case for Get A Organization************/
	@Test(priority=2)
	public void Get() { 
		
		/***Create a Request pointing to the Service Endpoint***/
		RestAssured.baseURI = prop.getProperty("base_Url");
		RequestSpecification request = RestAssured.given();
		
		/***Fetching the Response by get the request***/
		Response responsee = request.headers(Reusable_Data.headerBodyHashMap())
				.queryParam("key",key)
				.queryParam("token",token)
				.when().get("/1/organizations/"+id)
				.then().assertThat().statusCode(200)
				//.log().all()
				.extract().response();
         
		JsonPath js = json(responsee);
        String id = js.get("id").toString();
        System.out.println("****Get ID of Organization is:"+ id);
        //responsee.prettyPeek();	
        logger.info("Organization Get Created Successfully");
		
		}
	
	/*********Test Case for Create A Organization************/
     @Test(priority=1)
	
	 public void post() {
       				
    	 /***Create a Request pointing to the Service Endpoint***/
		 RestAssured.baseURI = prop.getProperty("base_Url");
		 RequestSpecification request = RestAssured.given();
			
		 /***Fetching the Response by post the request***/	
		 Response responsee =request.headers(Reusable_Data.headerBodyHashMap())
				  .queryParam("displayName",Organizationname)
				  .queryParam("key",key)
				  .queryParam("token",token)
                  .when().post("/1/organizations/")
				  .then().assertThat().statusCode(200)
				  //.log().all()
				  .extract().response();
			
		 JsonPath js = json(responsee);
	     id = js.get("id");
	     System.out.println("****Created ID of Organization is:"+ id);
		 //responsee.prettyPeek();
		 logger.info("Organization  Created Successfully");
	 }
     /*********Test Case for Update A Organization************/

     @Test(priority=3)
     public void put() {
	
    	 /***Create a Request pointing to the Service Endpoint***/
	      RestAssured.baseURI = prop.getProperty("base_Url");
          RequestSpecification request = RestAssured.given();
 
          /***Fetching the Response by put the request***/	
          Response responsee = request.headers(Reusable_Data.headerBodyHashMap())
			.queryParam("key",key)
			.queryParam("token",token)
			.queryParam("name",UpdtOrganizationname)
			.when().put("/1/organizations/"+id)
			.then().assertThat().statusCode(200)
			//.log().all()
			.extract().response();
 
          JsonPath js = json(responsee);
          String id = js.get("id").toString();
          System.out.println("****Put ID of Organization is:"+ id);
          String nam = js.get("name").toString();
          /**Validate the assertion**/
          Assert.assertEquals(nam,"nagarroltd_ggn");
          //responsee.prettyPeek();     
          logger.info("Organization updated  Successfully");
 
         }
     /*********Test Case for Delete A Organization************/
      @Test(priority = 4)
      public void delete(){ 
    	
    	  /***Create a Request pointing to the Service Endpoint***/
	  RestAssured.baseURI = prop.getProperty("base_Url");
      RequestSpecification request = RestAssured.given();
    
      /***Fetching the Response by delete the request***/	
      Response responsee =request.headers(Reusable_Data.headerBodyHashMap())
			.queryParam("key",key)
			.queryParam("token",token)
			.when().delete("/1/organizations/"+id);
    
     //responsee.prettyPeek();
     logger.info("Organization Delete  Successfully");
    }

}
