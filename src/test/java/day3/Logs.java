package day3;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
public class Logs {
    @Test
    void getAllLogs() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all();
    }

    @Test
    void getOnlyBody() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().body();
    }

    @Test
    void getOnlyCookies() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().cookies();
    }

    @Test
    void getOnlyHeaders() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().headers();
    }

}
