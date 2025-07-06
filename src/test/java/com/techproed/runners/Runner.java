package com.techproed.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class) // Annotation that integrates Cucumber with JUnit and allows scenarios to run
@CucumberOptions( // This annotation specifies which scenarios to run and what reports to generate
        plugin = {
                "pretty", // Provides detailed information about scenarios in the console
                "html:target/default-cucumber-reports.html",
                "json:target/json-reports/cucumber.json",
                "junit:target/xml-report/cucumber.xml"
        },
        features = "src/test/resources/features",
        glue = {"com/techproed/stepdefs"},
        tags = "@e2e",
        dryRun = false
)
public class Runner {
}