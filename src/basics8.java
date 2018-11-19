import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.commonFunctions;
import io.restassured.RestAssured;

public class basics8 {

	Properties prop = new Properties();

	@BeforeTest
	public void getData() throws IOException {

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Azim\\eclipse-workspace\\RestAPI\\src\\files\\env.properties");
		prop.load(fis);

	}
	
	@Test
	public void JiraAPI()
	{
		//Creating Issue/Defect
		
		RestAssured.baseURI = prop.getProperty("JIRAHOST");
		given().header("Content-Type", "application/json").
				header("cookie", "JSESSIONID="+ commonFunctions.getSessionKey()).
						body("{\r\n" + 
								"	\r\n" + 
								"	\"body\": \"Updating comment from the automation code\",\r\n" + 
								"	\"visibility\": {\r\n" + 
								"		\"type\": \"role\",\r\n" + 
								"		\"value\": \"Administrators\"\r\n" + 
								"	}\r\n" + 
								"}").when().
						put("/rest/api/2/issue/10106/comment/10105").then().statusCode(200).
						extract().response();
		
		
	
		
		

	}
}
