package com.techproed.stepdefs.db_stepdefs;

import com.techproed.stepdefs.ui_stepdefs.UIMedunnaStepdefs;
import com.techproed.utilities.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.*;

import static org.junit.Assert.assertEquals;

public class DBRoomStepdefs {
    private Connection connection;
    private ResultSet resultSet;
    private Statement st;

    public static Integer roomId;

    @Given("Admin Connects to the Database")
    public void adminConnectsToTheDatabase() throws SQLException {
        connection = DriverManager.getConnection(
                ConfigReader.getProperty("dbUrl"),
                ConfigReader.getProperty("dbUser"),
                ConfigReader.getProperty("dbPassword")
        );
    }

    @When("sends query for created room")
    public void sendsQueryForCreatedRoom() throws SQLException {
        st = connection.createStatement();
        resultSet = st.executeQuery("SELECT * FROM room WHERE room_number = " + UIMedunnaStepdefs.roomNumber);
    }

    @Then("validates created room from resultset")
    public void validatesCreatedRoomFromResultset() throws SQLException {
        resultSet.next();

        int expectedRoomNumber = UIMedunnaStepdefs.roomNumber;
        String expectedDescription = UIMedunnaStepdefs.expectedDescription;
        Double expectedPrice = UIMedunnaStepdefs.expectedPrice;
        //expectedPrice could be a number

        System.out.println(expectedPrice);

        int actualRoomNumber = resultSet.getInt("room_number");
        String actualDescription = resultSet.getString("description");
        Double actualPrice = resultSet.getDouble("price");
        //actualPrice could be a number

        assertEquals(expectedRoomNumber, actualRoomNumber);
        assertEquals(expectedDescription, actualDescription);
        assertEquals(expectedPrice, actualPrice);

        roomId = resultSet.getInt("id");
    }
}