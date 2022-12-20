package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "@CsquareCustomerCreateCQ", features = {"src\\test\\java\\feature"}, glue = "StepDefinitions")

public class Test_Runner extends AbstractTestNGCucumberTests{
	
	
}