package Register;

import Base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class RegisterSuccess extends BaseTest {
    @Test
    public void register(){
        RequestSpecification request = given();
        JSONObject params = new JSONObject();
        params.put("email", "eve.holt@reqres.in");
        params.put("password", "pistol");

        request.body(params.toString());

        request.header("Content-Type", "application/json");
        Response response = request.post("/api/register");

        response.then().assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/Resource/RegisterSuccess.json")))
        ;

        System.out.println(response.asString());
    }
}
