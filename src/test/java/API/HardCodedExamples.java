package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.devtools.v85.network.model.ResponseReceived;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

String baseURL=RestAssured.baseURI="http://hrm.syntaxtechs.net/syntaxapi/api";
String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODUyODUzMDYsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY4NTMyODUwNiwidXNlcklkIjoiNTI2MyJ9.4tvbBf_FMEfANaygq-UgaQWYGdMsv56kIKjBVpWYdQw";
static String employee_id;

@Test
    public void bgetCreatedEmployee(){
    //preparing request
RequestSpecification preparedRequest=given().
        header("Content-Type","application/json").
        header("Authorization",token).
        queryParam("employee_id",employee_id);
//hitting endpoint
    Response response=preparedRequest.when().get("/getOneEmployee.php");
    response.prettyPrint();

    //verify the response
    response.then().assertThat().statusCode(200);

    String TempEmpId=response.jsonPath().getString("employee.employee_id");
    //we have 2 emp id, one is local the other is global
    Assert.assertEquals(employee_id,TempEmpId);
    }
@Test
public void acreateEmployee(){
    //prepare the request
    RequestSpecification prepareRequest= given().header("Content-Type","application/json").header("Authorization",token).body("{\n" +
            "    \"emp_firstname\": \"will\",\n" +
            "  \"emp_lastname\": \"garcia\",\n" +
            "  \"emp_middle_name\": \"drake\",\n" +
            "  \"emp_gender\": \"M\",\n" +
            "  \"emp_birthday\": \"2000-11-10\",\n" +
            "  \"emp_status\": \"Confirmed\",\n" +
            "  \"emp_job_title\": \"Engineer\" \n" +
            "}");

    //hitting the endpoint/send the request
    Response response=prepareRequest.when().post("/createEmployee.php");

    //verifying the assertions//get response
    response.then().assertThat().statusCode(201);

    //this will print the output in the console
    response.prettyPrint();

    //we are capturing employee id from the response
    employee_id=response.jsonPath().getString("Employee.employee_id");
    System.out.println(employee_id);

    //verifying the first name in the response body
    response.then().assertThat().
            body("Employee.emp_firstname",equalTo("will"));
    response.then().assertThat().
            body("Employee.emp_lastname",equalTo("garcia"));

    //verifying the response headers
    response.then().assertThat().header("Content-Type","application/json");
    System.out.println("my test case is passed");
}
@Test
public void cupdateEmployee(){
    RequestSpecification preparedRequest=given().
            header("Content-Type","application/json").
            header("Authorization",token).
            body("{\n" +
                    "\"employee_id\": \""+employee_id+"\",\n" +
                    "  \"emp_firstname\": \"Ervin\",\n" +
                    "  \"emp_lastname\": \"Garcia\",\n" +
                    "  \"emp_middle_name\": \"DRAke\",\n" +
                    "  \"emp_gender\": \"M\",\n" +
                    "  \"emp_birthday\": \"2023-05-21\",\n" +
                    "  \"emp_status\": \"Goat\",\n" +
                    "  \"emp_job_title\": \"Goat\"\n" +
                    "}");

    //hitting the endpooint
    Response response=preparedRequest.when().put("/updateEmployee.php");
    response.then().assertThat().statusCode(200);
    response.then().assertThat().body("Message",equalTo("Employee record Updated"));

}
@Test
public void dgetUpdatedEmployee(){
    //preparing request
    RequestSpecification preparedRequest=given().
            header("Content-Type","application/json").
            header("Authorization",token).
            queryParam("employee_id",employee_id);

    Response response=preparedRequest.when().get("/getOneEmployee.php");
    response.prettyPrint();

    response.then().assertThat().statusCode(200);
    //if u want to verify the body of the response, you can do that using hamcrest matchers
}

}
