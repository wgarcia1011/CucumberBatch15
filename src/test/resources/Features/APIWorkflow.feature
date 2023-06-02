Feature: API workflow for HRMS
  Background:
    Given  a JWT is generated
  @api
  Scenario: create an employee using API call
    Given a request is prepare to create an employee
    When  a POST call is made to create an employee
    Then the status code for creating an employee is 201
    Then the employee contains key "Message" and value "Employee Created"
    Then  the employee id "Employee.employee_id" is stored as a global variable to be used for other calls

