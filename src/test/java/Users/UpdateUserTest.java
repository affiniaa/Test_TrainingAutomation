package Users;

import Base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UpdateUserTest extends BaseTest {
    @Test
    public void SuccessUpdate(ITestContext context) {
        RequestSpecification request = given();

        JSONObject bodyrequest = new JSONObject();
        bodyrequest.put("name", "morpheus");
        bodyrequest.put("job", "zion resident");

        request.header("Content-type", "Application/json");
        request.body(bodyrequest.toString());

        Response response = request.put("/api/users/2"+context.getAttribute("id_user"));

        response.then().assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/Resource/UpdateSchema.json")))
//                .log().all()
        ;
        System.out.println(response.asString());
    }
}
