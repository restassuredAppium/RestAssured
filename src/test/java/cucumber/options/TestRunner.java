package cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", plugin = "json:target/JsonReports/cucumber-report.json", glue = {
		"stepdefinitions" })
public class TestRunner {
//, tags = {"@DeletePlace"}
}
