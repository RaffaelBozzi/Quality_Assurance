package steps;

import api.APIParams;
import api.APIRequest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import utils.PropertiesUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CorreiosSteps extends APIRequest {

  PropertiesUtils prop = new PropertiesUtils();
  APIParams apiParams = new APIParams();

  private void validaStatusEsperado(int responseStatusCode) {
    assertEquals(responseStatusCode, response.statusCode());
  }

  @Dado("que possuo acesso ao sistema")
  public void quePossuoAcessoAoSistema() {
    System.out.println("Sem autenticação");
  }

  @Quando("informo todos os dados necessarios para consulta do frete")
  public void informoTodosOsDadosNecessariosParaConsultaDoFrete() {
    uri = prop.getProperties("uri_correios");
    params = apiParams.correiosParams();
    GET();
  }

  @Entao("o valor do frete é exibido")
  public void oValorDoFreteÉExibido() {
    validaStatusEsperado(200);
    Assert.assertTrue(Float.parseFloat(response.xmlPath().getString("Servicos.cServico.Valor").replace(",",".")) > 0);
  }

  @Quando("informo todos os dados necessarios para consulta do frete datatable")
  public void informoTodosOsDadosNecessariosParaConsultaDoFreteDatatable(DataTable data) {
    uri = prop.getProperties("uri_correios");
    params = apiParams.correiosTableParams(data.asMap());
    GET();
  }

  @Quando("informo {string}, {string}, {string}, {string}, {string} e {string} para consulta do frete")
  public void informoEParaConsultaDoFrete(String origem, String destino, String peso, String comprimento, String altura, String largura) {
    uri = prop.getProperties("uri_correios");
    Map<String,String> dados = new HashMap<>();
    dados.put("sCepOrigem", origem);
    dados.put("sCepDestino", destino);
    dados.put("nVlPeso", peso);
    dados.put("nVlComprimento", comprimento);
    dados.put("nVlAltura", altura);
    dados.put("nVlLargura", largura);
    params = apiParams.correiosTableParams(dados);
    GET();
  }
}
