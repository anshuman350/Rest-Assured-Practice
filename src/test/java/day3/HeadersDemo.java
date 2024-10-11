package day3;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class HeadersDemo {
    @Test
    void testHeaders(){
        given()
                .when()
                .get("https://www.google.com")
                .then()
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .header("server", "gws")
                .header("Content-Encoding", "gzip");
    }

    @Test
    void getHeaders(){
        Response res = given()
                .when()
                .get("https://www.google.com");

        String headerValue = res.getHeader("Content-Type");
        System.out.println("The content-type is: "+headerValue);
    }
// this is useless because we get all header info using .log().all()
    @Test
    void getAllHeaders(){
        Response res = given()
                .when()
                .get("https://www.google.com");

        Headers headerValues = res.getHeaders();
        for(Header hd: headerValues){
            System.out.println(hd.getName()+"  "+hd.getValue());
        }
    }
}
