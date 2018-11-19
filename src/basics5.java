import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import files.commonFunctions;

public class basics5 {

	@Test
	public void Test()
	{
		// TODO Auto-generated method stub

		//BaseURL or Host
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		Response res = given().
	       param("location","-33.8670522,151.1957362").
	       param("radius","500").
	       param("key","AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y").log().all().
	       when().
	       get("/maps/api/place/nearbysearch/json").
	       then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
	       body("results[0].name",equalTo("Sydney")).and().
	       body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
	       header("Server","scaffolding on HTTPServer2").log().body().
	       extract().response();
		commonFunctions.rawToJson(res);
		JsonPath js = commonFunctions.rawToJson(res);
		
		int count = js.get("results.size()");
		String name = "";
		for(int i=0; i<count; i++)
		{
			String x = js.get("results[" + i +"].name");
			System.out.println(x);
		}
		System.out.println(name);
		
		//Status code of the response
				//Content type 
				//Body
				//Header responses
	}

}
