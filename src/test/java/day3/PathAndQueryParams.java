package day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PathAndQueryParams {
    private final String baseUrl = "https://reqres.in/api"; // Base URL for the API

    @Test
    void testQueryAndPathParams() {
        given()
                .baseUri(baseUrl) // Set the base URI
                .pathParam("path", "users")
                .queryParam("page", 2)
                .queryParam("id", 5)
                .when()
                .get("/{path}") // Use the path parameter
                .then()
                .statusCode(200)
                .log().all();
    }
}
