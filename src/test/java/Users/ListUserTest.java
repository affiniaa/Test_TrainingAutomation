package Users;

import Base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class ListUserTest extends BaseTest {
    @Test
    public void SuccessGetList(ITestContext context){
        RequestSpecification request = given();

        request.param("page", 1);
        Response response = request.get("/api/users");

        response.then().assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/Resource/ListSchema.json")))
                .log().all()
        ;

//        System.out.println(response.jsonPath().getString("data[1].id"));
        context.setAttribute("id_user" , response.jsonPath().getInt("data[1].id"));

    }
}
