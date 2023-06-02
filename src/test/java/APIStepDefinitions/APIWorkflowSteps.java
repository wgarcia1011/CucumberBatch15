package APIStepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class APIWorkflowSteps {

    RequestSpecification request;
    Response response;
    public static String employee_id;
    @Given("a request is prepare to create an employee")
    public void a_request_is_prepare_to_create_an_employee() {
        request= given().header("Content-Type","application/json").header("Authorization",GenerateToken.token).body("{\n" +
                "    \"emp_firstname\": \"will\",\n" +
                "  \"emp_lastname\": \"garcia\",\n" +
                "  \"emp_middle_name\": \"drake\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2000-11-10\",\n" +
                "  \"emp_status\": \"Confirmed\",\n" +
                "  \"emp_job_title\": \"Engineer\" \n" +
                "}");
    }
    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
        // Write code here that turns the phrase above into concrete actions
        response=request.when().post("/createEmployee.php");
    }
    @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(Integer int1) {
        //argument or value is coming from feature file
        response.prettyPrint();
        response.then().assertThat().statusCode(int1);

    }
    @Then("the employee contains key {string} and value {string}")
    public void the_employee_contains_key_and_value(String message, String value) {
        response.then().assertThat().body(message,equalTo(value));

    }
    @Then("the employee id {string} is stored as a global variable to be used for other calls")
    public void the_employee_id_is_stored_as_a_global_variable_to_be_used_for_other_calls(String string) {
        //storing employee id in global variable
        //this path of employee is coming form feature file
        employee_id=response.jsonPath().getString(string);
        System.out.println(employee_id);
    }


}
