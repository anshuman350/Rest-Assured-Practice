package day1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HTTPRequests {
    int id; // Class variable to store the user ID

    // GET request
    @Test(priority = 0)
    void getUsers() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();
    }

    // POST request
    @Test(priority = 1)
    void createUsers() {
        HashMap<String, String> data = new HashMap<>(); // Use generics
        data.put("name", "Anshuman");
        data.put("job", "sdet");

        id = given() // Store the extracted ID in the class variable
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().all()
                .extract().jsonPath().getInt("id");
    }

    // PUT request
    @Test(priority = 2, dependsOnMethods = {"createUsers"})
    void updateUser() {
        HashMap<String, String> data = new HashMap<>(); // Use generics
        data.put("name", "Pratyush");
        data.put("job", "SDET2");

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .put("https://reqres.in/api/users/" + id) // Now id has the correct value
                .then()
                .statusCode(200)
                .log().all();
    }

    // DELETE request
    @Test(priority = 3, dependsOnMethods = {"createUsers"})
    void deleteUser() {
        given()
                .when()
                .delete("https://reqres.in/api/users/" + id) // Now id has the correct value
                .then()
                .statusCode(204);
    }
}
