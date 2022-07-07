package api;

import java.util.HashMap;
import java.util.Map;

public class APIParams {
  Map<String, String> params = new HashMap<>();

  public Map<String, String> gorestHeaders(String name) {
    params.clear();
    params.put("name", name);
    return params;
  }
}
