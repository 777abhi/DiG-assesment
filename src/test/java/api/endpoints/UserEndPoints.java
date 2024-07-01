package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UserEndPoints {

	public static Response createUser(User user) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(user).when()
				.post(Routes.post_url);
		return response;
	}

	public static Response getUser(String userid) {
		Response response = given().pathParam("userid", userid).when().get(Routes.get_url);
		return response;
	}

	public static Response updateUser(User user, String userid) {
		Response response = given().pathParams("userid", userid).contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(user).when().put(Routes.put_url);
		return response;
	}

	public static Response deleteUser(String userid) {
		Response response = given().pathParam("userid", userid).when().delete(Routes.delete_url);
		return response;
	}
}
