package api.test;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

import static api.utilities.Constants.*;

public class UserTestCases {

	User userPayload;

	@BeforeClass
	public void setupData() {
		userPayload = new User();
		userPayload.setName(USER_NAME);
		userPayload.setJob(JOB);

	}

	@Test
	public void testCreateUser() {
		SoftAssert softassertion = new SoftAssert();
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		softassertion.assertEquals(response.getStatusCode(), CREATED_201);
		JSONObject json = (JSONObject) JSONValue.parse(response.getBody().asString());
		softassertion.assertEquals(json.get("name"), USER_NAME);
		softassertion.assertEquals(json.get("job"), JOB);
		softassertion.assertAll();

	}

	@Test
	public void testGetuser() {
		SoftAssert softassertion = new SoftAssert();
		Response response = UserEndPoints.getUser(TWO);
		response.then().log().all();
		softassertion.assertEquals(response.getStatusCode(), SUCCESS_200);
		JSONObject json = (JSONObject) JSONValue.parse(response.getBody().asString());
		JSONObject userData = (JSONObject) json.get("data");
		softassertion.assertEquals(userData.get("first_name"), JANET);
		softassertion.assertEquals(userData.get("last_name"), WEAVER);
		softassertion.assertEquals(String.valueOf(userData.get("id")), TWO);
		softassertion.assertAll();
	}

	@Test
	public void testDeleteUser() {
		SoftAssert softassertion = new SoftAssert();
		Response response = UserEndPoints.deleteUser(TWO);
		response.then().log().all();
		softassertion.assertEquals(response.getStatusCode(), NO_CONTENT_204);
		softassertion.assertAll();
	}

	@Test
	public void testUpdateUser() {
		SoftAssert softassertion = new SoftAssert();
		Response response = UserEndPoints.updateUser(userPayload, TWO);
		response.then().log().all();
		softassertion.assertEquals(response.getStatusCode(), SUCCESS_200);
		JSONObject json = (JSONObject) JSONValue.parse(response.getBody().asString());
		softassertion.assertEquals(json.get("name"), USER_NAME);
		softassertion.assertEquals(json.get("job"), JOB);
		softassertion.assertAll();
	}

}
