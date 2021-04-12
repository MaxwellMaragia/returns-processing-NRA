package Runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = {"src/test/java/Features"},
	glue = "Steps",
	tags = "@sanity",
	plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
	dryRun = false,
	monochrome = true
)

public class Runnerclass {
	 
}  