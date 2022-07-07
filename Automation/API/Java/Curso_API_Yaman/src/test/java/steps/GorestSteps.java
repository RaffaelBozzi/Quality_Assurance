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
import users.UserLombok;
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
  //  String jsonPath = "src/test/resources/jsons/";

  // *Alternar de acordo com o tipo de solução escolhida
//  UserConstructor sentData;
  UserLombok sentData;
  int userId;

  private void validaStatusEsperado(int responseStatusCode) {
    assertEquals(responseStatusCode, response.statusCode());
  }

  private void validaIgualdadeRequestResponse() {
// *Verificação campo a campo -> usado anteriormente na solução da leitura do arquivo com o body
//    assertEquals(body.getString("email"), response.jsonPath().getString("email"));
//    assertEquals(body.getString("name"), response.jsonPath().getString("name"));
//    assertEquals(body.getString("gender"), response.jsonPath().getString("gender"));
//    assertEquals(body.getString("status"), response.jsonPath().getString("status"));

// *Verificação usando comparação de objetos -> UserConstructor
//    UserConstructor receivedData = new UserConstructor(
//        response.jsonPath().getString("name"),
//        response.jsonPath().getString("email"),
//        response.jsonPath().getString("gender"),
//        response.jsonPath().getString("status")
//    );

    // *Verificação usando comparação de objetos -> UserLombok
    UserLombok receivedData = response.jsonPath().getObject("", UserLombok.class);

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

// *Solução com leitura do arquivo com o body que se deseja enviar
//    body = JsonUtils.parseJSONFile(jsonPath + "create-user.json");
//    body.put("email", faker.internet().emailAddress());
//    body.put("name", faker.name().fullName());

// *Solução com uso de classe padrão
//    sentData = new UserConstructor(faker.name().fullName(),
//        faker.internet().emailAddress(),
//        (Arrays.asList("male", "female")).get(rand.nextInt(2)),
//        (Arrays.asList("active", "inactive")).get(rand.nextInt(2)));

// *Solução com o uso de classe com Lombok
    sentData = UserLombok.builder()
        .email(faker.internet().emailAddress())
        .name(faker.name().fullName())
        .gender((Arrays.asList("male", "female")).get(rand.nextInt(2)))
        .status((Arrays.asList("active", "inactive")).get(rand.nextInt(2)))
        .build();

    body = new JSONObject(new Gson().toJson(sentData));

    POST();
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
    assertEquals(userId, response.jsonPath().get("id"));
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
