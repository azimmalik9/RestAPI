package files;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class commonFunctions {

	public static XmlPath rawToXML(Response r) {
		String response=r.asString();
		XmlPath x = new XmlPath(response);
		return x;
	}
	
	public static JsonPath rawToJson(Response r) {
		String response=r.asString();
		JsonPath x = new JsonPath(response);
		return x;
	}
	
	public static String getSessionKey()
	{
		RestAssured.baseURI = "http://localhost:8080";
		Response res = given().header("Content-Type", "application/json").
		body("{\"username\": \"azim.malik9\", \"password\": \"people\"}").
		when().
		post("/rest/auth/1/session").then().statusCode(200).
		extract().response();
		JsonPath js = commonFunctions.rawToJson(res);
		String sessionId = js.get("session.value");
		return sessionId;
	}
	

	
}
