package day6;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.File;
public class JSONSchemaValidation {
    @Test
    void jsonSchemaValidate(){
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json_schema.json"));

    }
}
