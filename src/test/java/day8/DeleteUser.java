package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class DeleteUser {
    @Test
    void delete(ITestContext context) {
        String bearerToken = "068ef92d2719585dd407e9719e18221e7eb310c314bcfb65a6da27d8743121cc";
        int id = (int) context.getAttribute("user_id");
        given()
                .headers("Authorization", "Bearer " + bearerToken)
                .pathParam("id", id)
                .when()
                .delete("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(204);
    }
}
