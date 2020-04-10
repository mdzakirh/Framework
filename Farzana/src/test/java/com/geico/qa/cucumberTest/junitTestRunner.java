package com.geico.qa.cucumberTest;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true, 
		features = "src/test/java/com/geico/qa/cucumberFeature" 
		,glue={"com/geico/qa/cucumberStepDefination"}
		)
public class junitTestRunner {

}
