import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.commonFunctions;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class basics6 {

	Properties prop = new Properties();

	@BeforeTest
	public void getData() throws IOException {

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Azim\\eclipse-workspace\\RestAPI\\src\\env.properties");
		prop.load(fis);

	}
	
	@Test
	public void JiraAPI()
	{
		//Creating Issue/Defect
		
		RestAssured.baseURI = prop.getProperty("JIRAHOST");
		Response res = given().header("Content-Type", "application/json").
				header("cookie", "JSESSIONID="+ commonFunctions.getSessionKey()).
						body("{\r\n" + 
								"    \"fields\": {\r\n" + 
								"       \"project\":\r\n" + 
								"       {\r\n" + 
								"          \"key\": \"AZIM\"\r\n" + 
								"       },\r\n" + 
								"       \"summary\": \"Issue 16 for adding comment\",\r\n" + 
								"       \"description\": \"Creating my second bug\",\r\n" + 
								"       \"issuetype\": {\r\n" + 
								"          \"name\": \"Bug\"\r\n" + 
								"       }\r\n" + 
								"   }\r\n" + 
								"}").when().
						post("/rest/api/2/issue").then().statusCode(201).
						extract().response();
		
		JsonPath js = commonFunctions.rawToJson(res);
		String id = js.get("id");
		System.out.println(id);
	
		
		
	}
}
