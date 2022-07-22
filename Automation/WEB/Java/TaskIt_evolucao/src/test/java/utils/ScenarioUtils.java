package utils;

import io.cucumber.java.Scenario;

import java.util.HashMap;
import java.util.Map;

public class ScenarioUtils {
  private static Map<Long, Scenario> repository = new HashMap<>();

  public static void add(Scenario scenario) {
    if (get() == null)
      repository.put(getId(), scenario);
  }

  public static void remove() {
    if (get() != null)
      repository.remove(getId());
  }

  private static Scenario get() {
    return repository.get(getId());
  }

  private static Long getId() {
    return Thread.currentThread().getId();
  }

  public static void addText(String text) {
    get().log(text);
  }
}
