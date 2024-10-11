package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/*
 * Different Ways to create POST request body
 * Hashmap
 * Org.JSON
 * POJO
 * external JSON file
 * */
public class PostRequestBody {
    // 1) using hash map
    @Test(priority = 0)
    void postUsingHash() {
        HashMap data = new HashMap();
        data.put("name", "Anshuman");
        data.put("job", "sdet");

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Anshuman"))
                .body("job", equalTo("sdet"))
                .log().all()
                .extract().jsonPath().getInt("id");
    }

    // 2) Using org.json
    @Test(priority = 1)
    void postUsingOrgJson() {
        JSONObject data = new JSONObject();
        data.put("name", "Anshuman");
        data.put("job", "sdet");
        given()
                .contentType("application/json")
                .body(data.toString()) // should be converted to string because it only accepts string and later converts to json on its own.
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Anshuman"));
    }
    // 3) Using POJO class
    @Test(priority = 2)
    void postUsingPOJOClass(){
        POJOPostRequest data = new POJOPostRequest();
        data.setName("Anshuman");
        data.setJob("SDET");

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("job", equalTo("SDET"));
    }
    // 4) Using external JSON file using nio class
    @Test(priority = 3)
    void postUsingJSONFileUsingNIO() throws IOException {
        String jsonBody = new String(Files.readAllBytes(Paths.get("src/test/java/day2/body.json")));

        given()
                .contentType("application/json")
                .body(jsonBody.toString()) // should be converted to string because it only accepts string and later converts to json on its own.
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Anshuman"));
    }
    // 5) Using external JSON file WITHOUT using nio class
    @Test(priority = 4)
    void postUsingJSONFile() throws FileNotFoundException {
        File f = new File("/Users/anshuman/IdeaProjects/restassured/src/test/java/day2/body.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data = new JSONObject(jt);

        given()
                .contentType("application/json")
                .body(data.toString()) // should be converted to string because it only accepts string and later converts to json on its own.
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Anshuman"));
    }

    //6) If the file size is very big, then we use BufferedReader
    @Test(priority = 6)
    void postUsingLargeJSONFile() throws IOException {
        StringBuilder jsonBody = new StringBuilder();

        // Use BufferedReader to read the file line by line
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/java/day2/body.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }
        }

        // Use the constructed string as the body for the POST request
        given()
                .contentType("application/json")
                .body(jsonBody.toString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Anshuman"));
    }
}
