package org.test;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.utility.UtilityClass;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(features="src/test/resources", glue="org.step", dryRun=false,  monochrome=true, plugin= {"pretty", 

		                     "json:src\\test\\resources\\Reports\\jsonreport.json"})

public class TestRunner {

	@AfterClass
	public static void jvmrports() {
		
		UtilityClass.JVMReport("src\\test\\resources\\Reports\\jsonreport.json");
	}
	
}
