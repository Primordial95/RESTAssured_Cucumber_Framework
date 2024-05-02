package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
/*
 * Author  @Vivek Kumar Yadav
 * 
 * Use your personal auth key in global.properties
 * If u guys want to run using parameter in cmd ::  mvn test -Dcucumber.filter.tags="@Regression"  : here use tags accordingly
 * and for jenkins using parameters then use this :: mvn test -Dcucumber.filter.tags="@$tag"       : here tag is parameterized
 * now to generate cucumber maven reports use verify i.e :: mvn test verify (add filters if u want)
 * 
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",glue = "stepdefinitions",plugin = "json:target/jsonReports/cucumber-report.json")
public class TestRunner {

}
