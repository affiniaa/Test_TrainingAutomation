package Auth;

import Base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Login400 extends BaseTest {
    @Test
    public void UnsuccessLogin(){
        RequestSpecification request = given();
        JSONObject params = new JSONObject();
        params.put("email", "peter@klaven");
        params.put("password", "");

        request.body(params.toString());

        request.header("Content-Type", "application/json");
        Response response = request.post("/api/login");

        response.then().assertThat()
                .statusCode(400)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/Resource/LoginUnsuccess.json")))
        ;

        System.out.println(response.asString());
    }
}
