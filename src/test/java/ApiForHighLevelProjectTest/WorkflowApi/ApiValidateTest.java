package ApiForHighLevelProjectTest.WorkflowApi;


import ApiProjectHighLevel.JsonRead;
import ApiProjectHighLevel.api.ValidatorOperation;
import ApiProjectHighLevel.basicApi.Auth;
import ApiProjectHighLevel.basicApi.PlacesApi;
import com.aventstack.extentreports.Status;

import io.restassured.response.Response;

import listeners.Listeners;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.io.FileNotFoundException;
import java.io.IOException;

import static ApiProjectHighLevel.Log.info1;


/** run this class through testng.xml so that extent reports will generating  for api results */
public class ApiValidateTest {

    Response response;
    String placeId;


    /**
     * reference API Doc: https://restful-booker.herokuapp.com/apidoc/index.html
     */


    @Test
    public void validLoginTest() throws FileNotFoundException {
        Listeners.extestTest.get().info("Description: Valid Login Scenario with username and password.");
        Auth response = new Auth();
        Response loginTokenResponse = response.getLoginToken("admin", "password123");
        info1("loginTokenResponse is = " + loginTokenResponse.asString());


        try {
            //ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " +response.url)
            response.assertIt(200);
            Listeners.extestTest.get().log(Status.PASS, "Description: Valid Login Scenario with username and password.");
            Listeners.extestTest.get().info("Asserting response code");


            response.assertIt("token", null, ValidatorOperation.NOT_EMPTY);
            Listeners.extestTest.get().info("Asserting response value not empty case");
            response.assertIt("token", null, ValidatorOperation.NOT_NULL);
            Listeners.extestTest.get().info("Asserting response value not null case");

        } catch (AssertionError e) {
            Listeners.extestTest.get().log(Status.FAIL, "Assertion Failure: " + e.getMessage());

        }

    }

    @Test
    public void invalidLoginTest() throws FileNotFoundException {
        Listeners.extestTest.get().info("Description: InValid Login Scenario with username and password.");
        Auth response = new Auth();
        Response loginTokenResponse = response.getLoginToken("admibgvn", "pasnxbnxsword123");
        Listeners.extestTest.get().log(Status.FAIL,"InvalidLoginTokenResponse is = " + loginTokenResponse.asString());

        try {
            //ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " +response.url);
            response.assertIt(200);
            Listeners.extestTest.get().info("Asserting response code");


            response.assertIt("reason", "Bad credentials", ValidatorOperation.EQUALS);
            Listeners.extestTest.get().info("Asserting response value == Bad credentials");


        } catch (AssertionError e) {
            Listeners.extestTest.get().log(Status.FAIL, "Assertion Failure: " + e.getMessage());
        }

    }

    @Test(priority = 1)
    public void AddPlaceApiTest() throws IOException, ParseException {
        PlacesApi placesApi = new PlacesApi();
        response = placesApi.AddPlaceApi();
        placesApi.assertIt(200);
        Object obj = new JSONParser().parse(response.asString());
        JSONObject jsonObject = (JSONObject) obj;

        placeId = JsonRead.getValue(jsonObject, "place_id");
        info1("addPlace response := " + response.asString());
        info1("addPlace response of  placeId value   := " + placeId);


    }

    @Test(priority = 2)
    public void UpdatePlaceApiTest() throws IOException {
        PlacesApi placesApi = new PlacesApi();
        response= placesApi.UpdatePlaceApi(placeId);
        info1("UpdatePlace response is =" + response.asString());
    }

    @Test(priority = 3)
    public void GetPlaceApiTest() throws IOException {
        PlacesApi placesApi = new PlacesApi();
        response = placesApi.GetPlaceApi(placeId);
        info1("GetPlace response is =" + response.asString());
        String address = placesApi.getJson_Path(response, "address");
        info1("GetPlace response of address value is =" + address);
        Assert.assertEquals(address, "Summer Walk, Africa");
    }

    @Test(priority = 4)
    public void DeletePlaceApiTest() throws IOException {
        PlacesApi placesApi = new PlacesApi();
        response = placesApi.DeletePlaceApi(placeId);
        info1("UpdatePlace response is =" + response.asString());
    }


    @Test
    public void AddPlaceApiWithPojoTest() throws IOException {
        PlacesApi placesApi = new PlacesApi();
        response = placesApi.AddPlaceApiWithSerialize();
        info1("AddPlace pojo approach response is = " + response.asString());
    }
}
