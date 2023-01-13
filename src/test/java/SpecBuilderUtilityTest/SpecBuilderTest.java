package SpecBuilderUtilityTest;

import Pojo.AddPlace;
import Pojo.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilderTest {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //RestAssured.baseURI="https://rahulshettyacademy.com";

        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setLanguage("French-IN");
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName("Frontline house");
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);

        /* Build   -Request Spec Builder:*/
        RequestSpecification requestspec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();

        /* Build Response Spec Builder:*/
        ResponseSpecification responsespec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();


        /* *//* this frist approach for the getting response using spec builders and
       this Rewriting Test with Request and Response Spec Builder :*//*
        String AddPlaceResponse = given().spec(requestspec).body(p).when().post("/maps/api/place/add/json").
                then().spec(responsespec).extract().response().asString();
        System.out.println(AddPlaceResponse);*/
        


        /* this second approach for the getting response using spec builders */
        RequestSpecification res = given().spec(requestspec)
                .body(p);

        Response response = res.when().post("/maps/api/place/add/json").
                then().spec(responsespec).extract().response();

        String responseString = response.asString();
        System.out.println(responseString);


    }
}
