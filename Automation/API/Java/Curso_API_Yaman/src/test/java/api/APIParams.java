package api;

import java.util.HashMap;
import java.util.Map;

public class APIParams {
  Map<String, String> params = new HashMap<>();

  public Map<String, String> correiosParams() {
    params.clear();
    params.put("StrRetorno", "xml");
    params.put("nCdServico", "04510");
    params.put("sCepOrigem", "74371520");
    params.put("sCepDestino", "13175613");
    params.put("nVlPeso", "2.00");
    params.put("nVlComprimento", "36.00");
    params.put("nVlAltura", "18.00");
    params.put("nVlLargura", "12.00");
    return params;
  }

  public Map<String, String> correiosTableParams(Map<String, String> map) {
    params.clear();
    params.put("StrRetorno", "xml");
    params.put("nCdServico", "04510");
    params.putAll(map);
//    map.entrySet().forEach(elem -> params.put(elem.getKey(), elem.getValue()));
    return params;
  }
}
