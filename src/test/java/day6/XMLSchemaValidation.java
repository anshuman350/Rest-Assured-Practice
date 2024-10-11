package day6;
import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.File;
public class XMLSchemaValidation {
    @Test
    void xmlSchemaValidate(){
        given()
                .when()
                .get("https://mocktarget.apigee.net/xml")
                .then()
                .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xml_schema.xsd"));
    }
}
