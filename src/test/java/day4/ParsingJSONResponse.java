package day4;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class ParsingJSONResponse {
    //Approach 1
    @Test
    void testJSONResponse() {
        given()
                .contentType("application/json")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data[0].email", equalTo("michael.lawson@reqres.in"));
    }

    //Approach 2 - Using response variable
    @Test
    void testUsingResponse() {
        Response res = given()
                .when()
                .get("https://reqres.in/api/users?page=2");
        Assert.assertEquals(res.getStatusCode(),200);
        // any on them can be used
//        String email = res.jsonPath().get("data[0].email").toString();
        String email = res.jsonPath().getString("data[0].email");
        Assert.assertEquals(email, "michael.lawson@reqres.in");


    }
    @Test
    void testUsingJSONObject(){
        Response res = given()
                .when()
                .get("https://reqres.in/api/users?page=2");
        JSONObject jo = new JSONObject(res.asString());
        for (int i = 0; i < jo.getJSONArray("data").length();i++){
            String firstName = jo.getJSONArray("data").getJSONObject(i).get("first_name").toString();
            System.out.println(firstName);
        }
    }
}