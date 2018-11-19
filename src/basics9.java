import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
public class basics9 {

	String ConsumerKey="q5QOyZ3hzFJOSt6jItd0dVBam";
	String ConsumerSecret="vbm3XU7mwgOAp7NALqe4vgZoIYR98kcCznzWOCLB1M5doZ7zp4";
	String AccessToken="365407383-BZGLiv9IxGrLBGKukZZPQfQCbtiFrjEB0QbxWfKu";
	String TokenSecret="goj2qYvrJVrulQReFTECjEZpiQ1Zviqkf3EfvzVTI6Kjz";
	String id;
	@Test
	public void getLatestTweet()
	{
		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response res = given().auth().oauth(ConsumerKey, ConsumerSecret, AccessToken, TokenSecret).
		queryParam("count", 1)
		.when().get("/home_timeline.json").then().extract().response();
		
		String response = res.asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);		
		System.out.println(js.get("text").toString());
		System.out.println(js.get("id").toString());
		
		
	}
	
	@Test
	public void createTweet()
	{
		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response res = given().auth().oauth(ConsumerKey, ConsumerSecret, AccessToken, TokenSecret).
		queryParam("status", "I am creating this tweet with Automation")
		.when().post("/update.json").then().extract().response();
		
		String response = res.asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		System.out.println("Below is the tweet added");		
		//System.out.println(js.get("text").toString());
		System.out.println(js.get("id").toString());
		id = js.get("id").toString();
	}
	
	@Test
	public void E2E()
	{
		createTweet();
		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response res = given().auth().oauth(ConsumerKey, ConsumerSecret, AccessToken, TokenSecret)
		.when().post("/destroy/"+ id + ".json").then().extract().response();
		
		String response = res.asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		System.out.println("Tweet which got deleted with automation is below");
		System.out.println(js.get("text").toString());
		System.out.println(js.get("truncated").toString());
	}
}
