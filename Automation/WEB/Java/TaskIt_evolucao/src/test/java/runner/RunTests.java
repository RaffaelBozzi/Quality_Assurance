package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"html:src/test/resources/relatorios/report.html"},
    features = {"src/test/resources/features"},
    glue = {"runner", "steps"},
    tags = "@AllTests"
//    tags = "@Login"
//    tags = "@Contacts"
//    tags = "@Tasks"
)

public class RunTests {
}
