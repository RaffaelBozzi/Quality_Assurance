package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty:src/test/resources/report.html"},
    features = {"src/test/resources/features"},
    glue = {"steps", "runner"},
    tags = "@Gorest"
)

public class RunTest {
}
