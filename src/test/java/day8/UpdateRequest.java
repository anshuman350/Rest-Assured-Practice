package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class UpdateRequest {
    @Test
    void update(ITestContext context) {
        String bearerToken = "068ef92d2719585dd407e9719e18221e7eb310c314bcfb65a6da27d8743121cc";
        HashMap reqbody = new HashMap();
        reqbody.put("name", "Kevin");
        reqbody.put("gender", "Male");
        reqbody.put("email", "kevin@yopmail.com");
        reqbody.put("status", "inactive");
        int id = (int) context.getAttribute("user_id");
        given()
                .headers("Authorization", "Bearer " + bearerToken)
                .contentType("application/json")
                .body(reqbody)
                .pathParam("id", id)
                .when()
                .put("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(200);

    }
}
