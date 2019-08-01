package tests;

import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features={"src/test/resources/featureFiles"},glue={"stepDefinition"}, plugin = {"pretty", "html:target/cucumber"})

@Test
public class TestRunner extends AbstractTestNGCucumberTests{
	

}
