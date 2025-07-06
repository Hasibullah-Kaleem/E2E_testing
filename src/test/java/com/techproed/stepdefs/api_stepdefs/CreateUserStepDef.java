package com.techproed.stepdefs.api_stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.hu.Ha;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class CreateUserStepDef {

    private RequestSpecification spec;
    private Map<String,String> payload;
    private Response response;

    //This was our Step 1 in the previous project.
    @Given("the base URL {string} and path parameter {string} is used")
    public void theBaseURLAndPathParameterIsUsed(String baseUrl, String path) {
        spec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setBasePath(path)
                .setContentType(ContentType.JSON)
                .build();
    }
    //This was our Step 2 in the previous project.
    @And("a payload is created with name {string} and job {string}")
    public void aPayloadIsCreatedWithNameAndJob(String name, String job) {
        payload = new HashMap<>();
        payload.put("name", name);
        payload.put("job", job);

        //ForExampleThisIsAPojo pojo = new ForExampleThisIsAPojo(name, job);
    }
    //This was our Step 3 in the previous project.
    @When("a post request is sent and a response is received")
    public void aPostRequestIsSentAndAResponseIsReceived() {
        //spec object already has the path information in it, so we do not have to provide it again in the
        //post method.
        response = given(spec).body(payload).when().post();
    }
    //All the methods below were our step 4 previously.
    @Then("the status code should be {int}")
    public void theStatusCodeShouldBe(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.statusCode());
    }

    @And("the response content type should be {string}")
    public void theResponseContentTypeShouldBe(String expectedContentType) {
        assertEquals(expectedContentType, response.getContentType());
    }

    @And("the response name should be {string} and job should be {string}")
    public void theResponseNameShouldBeAndJobShouldBe(String expectedName, String expectedJob) {
        //String actualName = response.jsonPath()

        assertEquals(expectedName, response.jsonPath().getString("name"));
        assertEquals(expectedJob, response.jsonPath().getString("job"));
    }
}