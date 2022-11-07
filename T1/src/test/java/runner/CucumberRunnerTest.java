package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", 
				 features = {"src/test/java/Features/HomePage.feature"}, 
				 glue = {"stepDefinitions"},
                 plugin = {})
    
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
    
}