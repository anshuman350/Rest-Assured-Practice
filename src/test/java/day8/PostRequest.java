package day8;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;
public class PostRequest {
    @Test
    void post(ITestContext context){
        String bearerToken = "068ef92d2719585dd407e9719e18221e7eb310c314bcfb65a6da27d8743121cc";
        HashMap reqbody = new HashMap();
        reqbody.put("name", "Roger");
        reqbody.put("gender", "Male");
        reqbody.put("email", "roger@yopmail.com");
        reqbody.put("status", "inactive");
        int id = given()
                .headers("Authorization", "Bearer "+bearerToken)
                .contentType("application/json")
                .body(reqbody)
                .when()
                .post("https://gorest.co.in/public/v2/users")
                .jsonPath().getInt("id");
        context.setAttribute("user_id", id);
    }
}
