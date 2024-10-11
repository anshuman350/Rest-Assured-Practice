package day4;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
/*This is a sample class which won't work. This is just to showcase how things can be done for XML types of response*/
public class ParsingXMLResponse {
    // Approach 1
    @Test
    void testXMLResponse() {
        given()
                .contentType("application/xml")
                .when()
                .get("https://reqres.in/api/users?page=2") // Replace with a valid XML endpoint
                .then()
                .statusCode(200)
                .body("data.user[0].email", equalTo("michael.lawson@reqres.in")); // Adjust XPath as needed
    }

    // Approach 2 - Using response variable
    @Test
    void testUsingResponse() {
        Response res = given()
                .when()
                .get("https://reqres.in/api/users?page=2"); // Replace with a valid XML endpoint
        Assert.assertEquals(res.getStatusCode(), 200);

        // Using XML path
        String email = res.xmlPath().getString("data.user[0].email"); // Adjust XPath as needed
        Assert.assertEquals(email, "michael.lawson@reqres.in");
    }

    @Test
    void testUsingXPath() {
        Response res = given()
                .when()
                .get("https://reqres.in/api/users?page=2"); // Replace with a valid XML endpoint

        // Using XPath to extract first names
        String firstName = res.xmlPath().getString("data.user[0].first_name"); // Adjust XPath as needed
        System.out.println(firstName);

        // You can loop through if needed, based on your XML structure
        int userCount = res.xmlPath().getInt("data.user.size()");
        for (int i = 0; i < userCount; i++) {
            String firstNameLoop = res.xmlPath().getString("data.user[" + i + "].first_name");
            System.out.println(firstNameLoop);
        }
    }
}
