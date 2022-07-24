package api;

import org.json.JSONObject;
import io.restassured.response.Response;
import utils.LogUtils;
import utils.Relatorio;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class APIUtils extends LogUtils {
  protected Response response;
  protected JSONObject body;
  protected String uri;
  protected Map<String, String> headers = new HashMap<>();
  protected Map<String, String> params = new HashMap<>();
  protected String token;

  public void log(String verbo) {
    super.logInfo(" ****** Dados enviados no request ****** ");
    super.logInfo(verbo + " " + uri);
    super.logInfo("Body: \n" + body.toString(2));
    super.logInfo("Headers: " + headers);
    super.logInfo("Params: " + params);

    super.logInfo(" ****** Dados recebidos ****** ");
    super.logInfo("Status code: " + response.statusCode());
    super.logInfo("Payload recebido: \n" + response.asPrettyString());
    super.logInfo("Tempo de resposta: " + response.timeIn(TimeUnit.MILLISECONDS));

    Relatorio.escreverNoRelatorio(" ****** Dados enviados no request ****** <br>" +
        verbo + " " + uri +
        "<br>Body: <br>" + body.toString(2).replace("\n", "<br>") +
        "<br>Headers: " + headers +
        "<br>Params: " + params +
        "<br><br><br> ****** Dados recebidos ****** <br>" +
        "Status code: " + response.statusCode() +
        "<br>Payload recebido: <br>" + response.asPrettyString().replace("\n", "<br>") +
        "<br>Tempo de resposta: " + response.timeIn(TimeUnit.MILLISECONDS), true);
  }
}