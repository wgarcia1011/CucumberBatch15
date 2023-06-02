package APIStepDefinitions;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GenerateToken {
    String baseURL= RestAssured.baseURI="http://hrm.syntaxtechs.net/syntaxapi/api";
    public static String token;
    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        RequestSpecification generateTokenRequest=
                given().header("Content-Type","application/json").body("{\n" +
                        "  \"email\": \"string\",\n" +
                        "  \"password\": \"string\"\n" +
                        "}");
        //hitting the endpoint
        Response response=generateTokenRequest.when().post("/generateToken.php");

        //storing the token in global variable
        token="Bearer " + response.jsonPath().getString("token");
        System.out.println(token);

    }
}
