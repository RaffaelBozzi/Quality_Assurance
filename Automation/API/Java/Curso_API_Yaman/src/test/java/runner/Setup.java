package runner;

import api.APIUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.json.JSONObject;
import utils.ScenarioUtils;

import java.util.HashMap;

public class Setup extends APIUtils {
  @Before
  public void before(Scenario scenario) {
    ScenarioUtils.add(scenario);
//        headers = new HashMap<>();
//        params = new HashMap<>();
//        body = new JSONObject();
  }

  @After
  public void after() {
    ScenarioUtils.remove();
  }
}
