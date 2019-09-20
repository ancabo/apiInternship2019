package tests;

import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features={"src/test/resources/featureFiles/AddPet_Test.feature"},glue={"stepDefinition"}, plugin = {"pretty", "html:target/cucumber", "json:target/cucumber-report-html/cucumber.json"})

@Test
public class TestRunner extends AbstractTestNGCucumberTests{
	

}
