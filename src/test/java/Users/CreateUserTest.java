package Users;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class CreateUserTest {
    @Test
    public void SuccessCreate() {
        RestAssured.baseURI = "https://reqres.in/api/users";
        RequestSpecification request = given();

        JSONObject bodyrequest = new JSONObject();
        bodyrequest.put("name", "morpheus");
        bodyrequest.put("job", "leader");

        request.header("Content-type", "Application/json");
        request.body(bodyrequest.toString());

        Response response = request.post("/api/users");

        response.then().assertThat()
                .statusCode(201)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/Resource/CreateSchema.json")))
        ;
        System.out.println(response.asString());
    }
}
