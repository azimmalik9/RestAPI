import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import files.commonFunctions;
public class basics4 {

	@Test
	public void postData() throws IOException
	{	
		String postdata = GenerateStringFromResource("C:\\Users\\Azim\\Documents\\postdata.xml");
		RestAssured.baseURI = "http://216.10.245.166";
		Response res = given().
		
		queryParam("key", "qaclick123").
		body(postdata).
		when().
		post("/maps/api/place/add/xml").
		then().assertThat().statusCode(200).and().contentType(ContentType.XML).extract().
		response();
		
		XmlPath x=commonFunctions.rawToXML(res);
		
		String output = x.get("response.place_id");
		System.out.println(output);
		// Create a place = response (place id)
		
			// and delete Place = (Request - Place id)
		
	}
	
	public static String GenerateStringFromResource(String path) throws IOException
	{
		
		return new String(Files.readAllBytes(Paths.get(path)));
		
	}
}
