package xmlCode;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class xmlcode {
    public static void main(String[] args) {
        String xmlPayload = "<bookstore>\n" +
                "<book>\n" +
                "<title lang='eng'>Harry Potter</title>\n" +
                "<author>J. K. Rowling</author>\n" +
                "<year>2005</year>\n" +
                "<price>29.99</price>\n" +
                "</book>\n" +
                "<book>\n" +
                "<title lang='eng'>Learning XML</title>\n" +
                "<author>Erik T. Ray</author>\n" +
                "<year>2003</year>\n" +
                "<price>39.95</price>\n" +
                "</book>\n" +
                "</bookstore>";
        RestAssured.baseURI = "https://example.com";
        RestAssured.basePath = "/api/endpoint";
        Response response = RestAssured
                .given()
                .contentType(ContentType.XML)
                .body(xmlPayload)
                .post();
        int statusCode = response.getStatusCode();
        System.out.println("Status code: " + statusCode);
        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);
    }
}
