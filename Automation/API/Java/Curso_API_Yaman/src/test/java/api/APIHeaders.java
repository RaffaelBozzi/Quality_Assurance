package api;

import java.util.HashMap;
import java.util.Map;

public class APIHeaders {
  Map<String, String> headers = new HashMap<>();

  public Map<String, String> gorestHeaders(String token) {
    headers.put("Authorization", token);
    headers.put("Accept", "application/json");
    headers.put("Content-Type", "application/json");
    return headers;
  }
}
