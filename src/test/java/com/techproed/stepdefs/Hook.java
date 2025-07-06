package com.techproed.stepdefs;

import com.techproed.utilities.Authentication;
import com.techproed.utilities.ConfigReader;
import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class Hook {

    public static RequestSpecification spec;

    @Before("@apie2e")
    public void setUp() throws Exception {
        spec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("baseUrl"))
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", Authentication.generateToken())
                .build();
    }
}