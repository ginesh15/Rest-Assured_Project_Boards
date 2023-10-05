package Reusable_Methods;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Reusable_Data {
	
	public static Properties prop;
	
	/***Store the headers by creating Hashmap***/
	public static Map<String, Object> headerBodyHashMap()
	{
	    HashMap<String,Object> header = new HashMap<String,Object>();
	    header .put("Content-Type", "application/json");
	    header.put("Accept","application/json");
	    return header;
	}
	
	public static JsonPath  json(Response responsee) {
		
		 String resp = (responsee).asString();
	        JsonPath js = new JsonPath(resp);
	        return js;
	}
	/***To read the Properties file we set the path***/ 
	 public Reusable_Data (){
			
			try {
				prop = new Properties();
				FileInputStream ip = new FileInputStream("./resources/configure.properties");
				prop.load(ip);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

    	}
	 
	 /***To create the List we need to crate boardID for passing the ID***/
      public static String postfetchId() {

		 RestAssured.baseURI = prop.getProperty("base_Url");
		 RequestSpecification request = RestAssured.given();
				
		 Response responsee =request.header("Content-Type", "application/json")
				  .header("Accept","application/json").queryParam("name", "Boardname")
				  .queryParam("key", "ce065c6eb6c663dbfb61f61bf5a8618b")
				  .queryParam("token", "25ff99bd4a0e0cf7126e4750bce5e8d02ea5598d9531a07a1833a79c6edf2868")
                  .when().post("/1/boards/")
				  .then()
				  //.log().all()
				  .extract().response();
		
		 JsonPath js = json(responsee);
	     String id = js.get("id").toString();
	    	return id;
	     
	}
 

  /***To Crate the Card we need to Crate boardID as well as List ID for passing the ID Dynamically***/
     public static String GetFetchlist_id() {
	 
	 RestAssured.baseURI = prop.getProperty("base_Url");
	 RequestSpecification request = RestAssured.given();
				
	 Response responsee =request.header("Content-Type", "application/json")
			  .header("Accept","application/json").queryParam("name", "{Boardname}")
			  .queryParam("idBoard",Reusable_Data.postfetchId())
			  .queryParam("key", "ce065c6eb6c663dbfb61f61bf5a8618b")
			  .queryParam("token", "25ff99bd4a0e0cf7126e4750bce5e8d02ea5598d9531a07a1833a79c6edf2868")
              .when().post("/1/lists")
			  .then()
			  //.log().all()
			  .extract().response();
	 
	 String resp = (responsee).asString();
     JsonPath js = new JsonPath(resp);
     String id = js.get("id").toString();
        return id;
 }
}
