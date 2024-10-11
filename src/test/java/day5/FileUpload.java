package day5;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.File;

public class FileUpload {
    @Test
    void singleFileUpload(){
        File myFile = new File("/Users/anshuman/Documents/temp.html");
        given()
                .contentType("multipart/form-data")
                .multiPart("file", myFile)
                .when()
                .post("https://api.escuelajs.co/api/v1/files/upload")
                .then()
                .statusCode(201)
                .body("originalname", equalTo("temp.html"));
    }
    @Test
    void multipleFileUpload(){
        // upload multiple file (note that this dummy api does not support uploading multiple file hence test will fail but the process remains like this)
        File myFile1 = new File("/Users/anshuman/Documents/temp.html");
        File myFile2 = new File("/Users/anshuman/Documents/gatling_logs.log");
        given()
                .contentType("multipart/form-data")
                .multiPart("file",myFile1)
                .multiPart("file",myFile2)
                .when()
                .post("https://api.escuelajs.co/api/v1/files/upload")
                .then()
                .statusCode(201)
                .body("[0].originalname", equalTo("temp.html"))
                .body("[0].originalname", equalTo("gatling_logs.log"));

    }


}
