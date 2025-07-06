package com.techproed.stepdefs.api_stepdefs;

import com.techproed.pojos.RoomPojo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static com.techproed.stepdefs.Hook.spec;
import static com.techproed.stepdefs.db_stepdefs.DBRoomStepdefs.roomId;
import static com.techproed.stepdefs.ui_stepdefs.UIMedunnaStepdefs.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class APIRoomStepdefs {

    private Response response;

    private RoomPojo expectedData;

    @Given("A Get request is sent")
    public void aGetRequestIsSent() {
        //1 - Set the URL https://medunna.com/api/rooms/126833
        spec.pathParams("first", "api", "second", "rooms", "third", roomId);
        //2 - Set the expected data
        //ideally, we should get the roomnumber from db step defs. I just get it from the ui step defs to save time.
        expectedData = new RoomPojo(roomNumber, "TWIN", true, expectedPrice, expectedDescription);
        //3 - Send the request
        response = given(spec).when().get("{first}/{second}/{third}");
    }

    @Then("Response validated")
    public void responseValidated() {
        //4 - Make assertions
        RoomPojo actualData = response.as(RoomPojo.class);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getRoomNumber(), actualData.getRoomNumber());
        assertEquals(expectedData.isStatus(), actualData.isStatus());
        assertEquals(expectedData.getPrice(), actualData.getPrice());
        assertEquals(expectedData.getDescription(), actualData.getDescription());
    }
}