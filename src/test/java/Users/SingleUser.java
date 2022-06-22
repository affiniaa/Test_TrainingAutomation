package Users;

import Base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;
import java.sql.ResultSet;

import static io.restassured.RestAssured.given;

public class SingleUser extends BaseTest {
    @Test
    public void single(ITestContext context)
    {
        RequestSpecification request = given();
        request.param("page", 1);

        Response response = request.get("api/users/"+context.getAttribute("id_user"));

        response.then().assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/Resource/SingleSchema.json")));

        System.out.println(response.asString());
    }
}
