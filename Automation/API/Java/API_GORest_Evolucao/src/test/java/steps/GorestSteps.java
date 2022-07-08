package steps;

import api.APIHeaders;
import api.APIRequest;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.json.JSONObject;
import elementosAPI.User;
import utils.PropertiesUtils;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GorestSteps extends APIRequest {

  PropertiesUtils prop = new PropertiesUtils();
  APIHeaders apiHeaders = new APIHeaders();
  Faker faker = new Faker();
  Random rand = new Random();

  User sentData;
  int userId;

  private void validaStatusEsperado(int responseStatusCode) {
    assertEquals(responseStatusCode, response.statusCode());
  }

  private void validaIgualdadeRequestResponse() {
    User receivedData = response.jsonPath().getObject("", User.class);

    assertEquals(sentData, receivedData);
  }

  @Dado("que possuo acesso ao gorest")
  public void quePossuoAcessoAoGorest() {
    token = prop.getProperties("token_gorest");
  }

  @Quando("envio uma requisicao de cadastro de usuario com dados validos")
  public void envioUmaRequisicaoDeCadastroDeUsuarioComDadosValidos() {
    uri = prop.getProperties("uri_gorest");
    headers = apiHeaders.gorestHeaders(token);

    sentData = User.builder()
        .email(faker.internet().emailAddress())
        .name(faker.name().fullName())
        .gender(User.sorteiaGenero())
        .status(User.sorteiaStatus())
        .build();

    body = new JSONObject(new Gson().toJson(sentData));

    POST();

    sentData.setId(response.jsonPath().get("id"));
  }

  @Então("o usuario deve ser criado corretamente")
  public void oUsuarioDeveSerCriadoCorretamente() {
    validaStatusEsperado(201);
    validaIgualdadeRequestResponse();
  }

  @E("existe um usuario cadastrado na api")
  public void existeUmUsuarioCadastradoNaApi() {
    envioUmaRequisicaoDeCadastroDeUsuarioComDadosValidos();
    userId = response.jsonPath().get("id");
  }

  @Quando("buscar este usuario")
  public void buscarEsteUsuario() {
    uri = prop.getProperties("uri_gorest") + "/" + userId;
//    headers = apiHeaders.gorestHeaders(token);
    body = new JSONObject();
    GET();
  }

  @Então("o usuario deve ser retornado corretamente")
  public void oUsuarioDeveSerRetornadoCorretamente() {
    validaStatusEsperado(200);
    validaIgualdadeRequestResponse();
  }

  @Quando("altero os dados deste usuario")
  public void alteroOsDadosDesteUsuario() {
    uri = prop.getProperties("uri_gorest") + "/" + userId;
    sentData.setEmail(faker.internet().emailAddress());
    body = new JSONObject(new Gson().toJson(sentData));

    PUT();
  }

  @Então("os dados devem ser atualizados corretamente")
  public void osDadosDevemSerAtualizadosCorretamente() {
    validaStatusEsperado(200);
    assertEquals(Integer.valueOf(userId), response.jsonPath().get("id"));
    validaIgualdadeRequestResponse();
  }

  @Quando("altero um ou mais dados deste usuario")
  public void alteroUmOuMaisDadosDesteUsuario() {
    uri = prop.getProperties("uri_gorest") + "/" + userId;
    body = new JSONObject("{\"email\":\"" + faker.internet().emailAddress() + "\"}");
    sentData.setEmail(body.getString("email"));

    PATCH();
  }

  @Quando("deleto este usuario")
  public void deletoEsteUsuario() {
    uri = prop.getProperties("uri_gorest") + "/" + userId;
    body = new JSONObject();

    DELETE();

    validaStatusEsperado(204);
  }

  @Então("os dados do usuario sao excluidos")
  public void osDadosDoUsuarioSaoExcluidos() {
    buscarEsteUsuario();
    validaStatusEsperado(404);
    assertTrue(response.jsonPath().getJsonObject("message").toString().toLowerCase().contains("not found"));
  }
}
