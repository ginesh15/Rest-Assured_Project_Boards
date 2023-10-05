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

public class List_All_Crud_operation extends Reusable_Data {

    static String id = "";
    
    /***Reading the Properties from Configure.Properties file***/
    String key = prop.getProperty("key");
	String token = prop.getProperty("token");
	String Listname = prop.getProperty("Listname");
	String UpdtListname = prop.getProperty("UpdtListname");
	
    private static Logger logger = LogManager.getLogger(List_All_Crud_operation.class);
   
    /***Call the method from reusuabledata class to crate Id before Test***/ 
	@BeforeTest
	public static void postfetchid() {
	 id = Reusable_Data.postfetchId();
	}
	
	/*********Test Case for Get A List************/
	@Test(priority=2)
	public void Get() { 
		
		/***Create a Request pointing to the Service Endpoint***/
		RestAssured.baseURI = prop.getProperty("base_Url");
		RequestSpecification request = RestAssured.given();
		
		/***Fetching the Response by get the request***/
		Response responsee = request.headers(Reusable_Data.headerBodyHashMap())
				.queryParam("key",key)
				.queryParam("token",token)
				.when().get("/1/lists/"+id)
				.then().assertThat().statusCode(200)
				//.log().all()
				.extract().response();
         
		JsonPath js = json(responsee);
        String id1 = js.get("id").toString();
        System.out.println("Get ID is:"+ id1);
        //responsee.prettyPeek();	
        logger.info("List Get Created Successfully");
				
		}
	/*********Test Case for Create A List************/
    @Test(priority=1)
	public void postList() {
    	
    	/***Create a Request pointing to the Service Endpoint***/
		 RestAssured.baseURI = prop.getProperty("base_Url");
		 RequestSpecification request = RestAssured.given();
		 
		 /***Fetching the Response by post the request***/			
		 Response responsee =request.headers(Reusable_Data.headerBodyHashMap())
				  .queryParam("name",Listname)
				  .queryParam("idBoard",id)
				  .queryParam("key",key)
				  .queryParam("token",token)
                  .when().post("/1/lists/")
				  .then().assertThat().statusCode(200)
				  //.log().all()
				  .extract().response();
			
		 JsonPath js = json(responsee);
	     id = js.get("id");
	     System.out.println("Created ID is:"+ id);
		 //responsee.prettyPeek();
		 logger.info("List Created Successfully");
	}
    /*********Test Case for Update A List************/
     @Test(priority=3)
     public void put() {
    	 
    	 /***Create a Request pointing to the Service Endpoint***/
	     RestAssured.baseURI = prop.getProperty("base_Url");
         RequestSpecification request = RestAssured.given();
 
         /***Fetching the Response by put the request***/	
         Response responsee =request.headers(Reusable_Data.headerBodyHashMap())
        	.queryParam("name",UpdtListname)
			.queryParam("key",key)
			.queryParam("token",token)
			.when().put("/1/lists/"+id)
			.then().assertThat().statusCode(200)
			//.log().all()
			.extract().response();
 
         JsonPath js = json(responsee);
         String id1 = js.get("id").toString();
         System.out.println("Put ID is:"+ id1);
         String nam = js.get("name").toString();
         /**Validate the assertion**/
         Assert.assertEquals(nam, "NagarroU");
         //responsee.prettyPeek(); 
         logger.info("List updated Successfully");
 
        } 
	
}
