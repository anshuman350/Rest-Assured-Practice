package day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Cookies {
    // this is a useless method as cookie value changes always so asserting against a particular value
    // is useless as the test will always fail rather we should match against a pattern
    @Test
    void testCookies(){
        given()
                .when()
                .get("https://www.google.com")
                .then()
                .cookie("AEC", "AVYB7cpyV7rwkOZALbGuez-8XWKri2wAVVroR-mWeP8awwOj5js_-dhMBao")
                .log().all();

    }
    @Test
    void getCookiesInfo(){
        Response res = given()
                .when()
                .get("https://www.google.com");
        String cookie_value = res.getCookie("AEC");
        System.out.println("Cookie is ----> "+cookie_value);
        // similarly we can extract all cookies as well using getCookies()
    }

    // Adding cookie or modifying
    @Test
    void addCookiesToRequest() {
        Response response = given()
                .cookie("myCookie", "cookieValue") // Add a single cookie
                .cookie("AEC", "anotherValue") // modify value of a cookie
                .when()
                .get("https://example.com");

        System.out.println("Response: " + response.asString());
    }

}
