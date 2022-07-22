package runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.Browser;
import utils.ScenarioUtils;

public class Setup extends Browser {

  @Before
  public void before(Scenario scenario) {
    ScenarioUtils.add(scenario);
    createChromeBrowser();
  }

  @After
  public void after() {
    quitBrowser();
    ScenarioUtils.remove();
  }
}
