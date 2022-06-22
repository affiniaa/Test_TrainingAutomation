package Auth;

import Base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Login200 extends BaseTest {
    @Test
    public void SuccessLogin(){
        RequestSpecification request = given();
        JSONObject params = new JSONObject();
        params.put("email", "eve.holt@reqres.in");
        params.put("password", "cityslicka");

        request.body(params.toString());

        request.header("Content-Type", "application/json");
        Response response = request.post("/api/login");

        response.then().assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/Resource/LoginSucess.json")))
        ;

        System.out.println(response.asString());
    }
}
