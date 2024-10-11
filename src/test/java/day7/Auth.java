package day7;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class Auth {
    @Test(priority = 1)
    void basicAuth(){
        given()
                .auth().basic("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200);
    }
    @Test(priority = 2)
    void digestAuth(){
        given()
                .auth().digest("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200);
    }
    @Test(priority = 3)
    void preemptiveAuth(){
        given()
                .auth().preemptive().basic("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200);
    }
    @Test(priority = 4)
    void bearerTokenAuth(){
        String bearerToken = "<YOUR_BEARER_TOKEN>";
        given()
                .headers("Authorization", "Bearer "+bearerToken)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200);
    }
    // This is a dummy test written to showcase OAUTH 1
    @Test(priority = 5)
    void oauthAuth(){

        given()
                .auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret")
                .when()
                .get("")
                .then();

    }
    // the no. of parameters are reduced in oauth2 from oauth 1. here we just have to pass this token
    @Test(priority = 6)
    void oauth2Auth(){
        String token = "<YOUR_TOKEN>";
        given()
                .auth().oauth2(token)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200);

    }
    //API key can be passed either in the query param or header. Here we are passing in the query param.
    @Test(priority = 7)
    void apiKeyAuth(){
        given()
                .queryParam("appid","fb6d72966332f9270886b5ec1a87928e")
                .when()
                .get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
                .then()
                .statusCode(200);
    }
}
