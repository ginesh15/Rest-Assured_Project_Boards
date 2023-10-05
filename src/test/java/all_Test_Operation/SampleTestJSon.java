package all_Test_Operation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



public class SampleTestJSon {

	public static void main(String[] args) {
		
	String jsoncontent =
			 "{\n" +
					    "  \"dashboard\": {\n" +
					    "    \"purchaseAmount\": 1810,\n" +
					    "    \"website\": \"rahulshettyacademy.com\"\n" +
					    "  },\n" +
					    "  \"courses\": [\n" +
					    "    {\n" +
					    "      \"title\": \"Selenium Python\",\n" +
					    "      \"price\": 50,\n" +
					    "      \"copies\": 6\n" +
					    "    },\n" +
					    "    {\n" +
					    "      \"title\": \"Cypress\",\n" +
					    "      \"price\": 40,\n" +
					    "      \"copies\": 4\n" +
					    "    },\n" +
					    "    {\n" +
					    "      \"title\": \"RPA\",\n" +
					    "      \"price\": 45,\n" +
					    "      \"copies\": 10\n" +
					    "    },\n" +
					    "  {\n" +
					    "      \"title\": \"Playwright\",\n" +
					    "      \"price\": 60,\n" +
					    "      \"copies\": 15\n" +
					    "    }\n" +
					    "  ]\n" +
					    "}";
	
	System.out.println(jsoncontent);
	
	try {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsoncontent);
       
        //Print No of courses returned by API
        JsonNode coursesNode = rootNode.get("courses");
        int numberOfCourses = coursesNode.size();
        System.out.println("Number of courses returned by the API: " + numberOfCourses);
        
        //Print Purchase Amount
        int purchaseAmount = rootNode.path("dashboard").path("purchaseAmount").asInt();
        System.out.println("Purchase amount is : " + purchaseAmount);
       
        //Print Title of the first course
        JsonNode firstCourseNode = rootNode.get("courses").get(0);
        String firstCourseTitle = firstCourseNode.get("title").asText();
        System.out.println("Title of the first course: " + firstCourseTitle);
        
        //Print All course titles and their respective Prices
       
        // Iterate over the courses array
        for (JsonNode courseNode : coursesNode) {
            // Extract the title and price values
            String title = courseNode.get("title").asText();
            int price = courseNode.get("price").asInt();

            // Print the title and price
            System.out.println("Title: " + title);
            System.out.println("Price: " + price);
            
          //Print no of copies sold by RPA Course
            if (courseNode.get("title").asText().equals("Playwright")) {
                int rpaCopiesSold = courseNode.get("copies").asInt();
                System.out.println("Number of copies sold by the Playwright course: " + rpaCopiesSold);
                break;
            }
        }

         //Verify if Sum of all Course prices matches with Purchase Amount
        int totalCoursePrice = 0;
        for (JsonNode courseNode : coursesNode) {
            int coursePrice = courseNode.get("price").asInt();
            int coursecopies = courseNode.get("copies").asInt();
            totalCoursePrice += coursePrice*coursecopies;
        }
     // Verify if the sum of course prices matches the purchase amount
        boolean pricesMatch = totalCoursePrice == purchaseAmount;
        
        if (pricesMatch) {
            System.out.println("The sum of all course prices matches the purchase amount.");
        } else {
            System.out.println("The sum of all course prices does not match the purchase amount.");
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
	
 }
	
}
	


