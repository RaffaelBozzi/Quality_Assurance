package steps;

import api.APIHeaders;
import api.APIRequest;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import elementosAPI.ErrorMessage;
import io.cucumber.java.pt.*;
import io.restassured.common.mapper.TypeRef;
import org.json.JSONArray;
import org.json.JSONObject;
import elementosAPI.User;
import utils.PropertiesUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UsersSteps extends APIRequest {

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

  private JSONObject gerarBodyUsuario(ArrayList<String> dados) {
    User.UserBuilder usuario = User.builder();
    for (String dado : dados) {
      switch (dado.toLowerCase()) {
        case "nome":
          usuario = usuario.name(faker.name().fullName());
          break;

        case "email":
          usuario = usuario.email(faker.internet().emailAddress());
          break;

        case "genero":
          usuario = usuario.gender(User.sorteiaGenero());
          break;

        case "status":
          usuario = usuario.status(User.sorteiaStatus());
          break;
      }
    }

    sentData = usuario.build();
    return new JSONObject(new Gson().toJson(sentData));
  }

  @Dado("que possuo acesso ao gorest")
  public void quePossuoAcessoAoGorest() {
    token = prop.getProperties("token_gorest");
    uri = prop.getProperties("uri_gorest");
    headers = apiHeaders.gorestHeaders(token);
  }

  @Quando("envio uma requisicao de cadastro de usuario com dados validos")
  public void envioUmaRequisicaoDeCadastroDeUsuarioComDadosValidos() {
    ArrayList<String> dadosInformados = new ArrayList<>();
    dadosInformados.add("nome");
    dadosInformados.add("email");
    dadosInformados.add("genero");
    dadosInformados.add("status");

    body = gerarBodyUsuario(dadosInformados);

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

  @Quando("envio uma requisicao de cadastro de usuario sem informar {string}")
  public void envioUmaRequisicaoDeCadastroDeUsuarioSemInformar(String dado) {
    ArrayList<String> dadosInformados = new ArrayList<>();
    switch (dado.toLowerCase()) {
      case "nome":
        dadosInformados.add("email");
        dadosInformados.add("genero");
        dadosInformados.add("status");
        break;

      case "email":
        dadosInformados.add("nome");
        dadosInformados.add("genero");
        dadosInformados.add("status");
        break;

      case "genero":
        dadosInformados.add("nome");
        dadosInformados.add("email");
        dadosInformados.add("status");
        break;

      case "status":
        dadosInformados.add("nome");
        dadosInformados.add("email");
        dadosInformados.add("genero");
        break;
    }

    body = gerarBodyUsuario(dadosInformados);

    POST();
  }

  @Entao("é exibida mensagem de erro de {string}")
  public void éExibidaMensagemDeErroDe(String mensagem) {
    int statusEsperado = 422;
    ArrayList<ErrorMessage> mensagemEsperada = new ArrayList<>();
    ErrorMessage erro;
    boolean responseIsArray = true;

    switch (mensagem.toLowerCase()) {
      case "nome nao informado":
        erro = ErrorMessage.builder()
            .field("name")
            .message("can't be blank")
            .build();

        mensagemEsperada.add(erro);
        break;

      case "email nao informado":
        erro = ErrorMessage.builder()
            .field("email")
            .message("can't be blank")
            .build();

        mensagemEsperada.add(erro);
        break;

      case "genero nao informado":
        erro = ErrorMessage.builder()
            .field("gender")
            .message("can't be blank, can be male or female")
            .build();

        mensagemEsperada.add(erro);
        break;

      case "status nao informado":
        erro = ErrorMessage.builder()
            .field("status")
            .message("can't be blank")
            .build();

        mensagemEsperada.add(erro);
        break;

      case "email invalido":
        erro = ErrorMessage.builder()
            .field("email")
            .message("is invalid")
            .build();

        mensagemEsperada.add(erro);
        break;

      case "email cadastrado":
        erro = ErrorMessage.builder()
            .field("email")
            .message("has already been taken")
            .build();

        mensagemEsperada.add(erro);
        break;

      case "dados ausentes":
        erro = ErrorMessage.builder()
            .field("email")
            .message("can't be blank")
            .build();
        mensagemEsperada.add(erro);

        erro = ErrorMessage.builder()
            .field("name")
            .message("can't be blank")
            .build();
        mensagemEsperada.add(erro);

        erro = ErrorMessage.builder()
            .field("gender")
            .message("can't be blank, can be male or female")
            .build();
        mensagemEsperada.add(erro);

        erro = ErrorMessage.builder()
            .field("status")
            .message("can't be blank")
            .build();
        mensagemEsperada.add(erro);
        break;

      case "usuario não encontrado":
        statusEsperado = 404;
        erro = ErrorMessage.builder()
            .message("Resource not found")
            .build();

        mensagemEsperada.add(erro);

        responseIsArray = false;
        break;
    }

    validaStatusEsperado(statusEsperado);
    if (responseIsArray) {
      ArrayList<ErrorMessage> receivedData = new ArrayList<>(response.as(new TypeRef<List<ErrorMessage>>() {}));
      assertEquals(mensagemEsperada, receivedData);
    } else {
      ErrorMessage receivedData = response.as(new TypeRef<ErrorMessage>() {});
      assertEquals(mensagemEsperada.get(0), receivedData);
    }
  }

  @Quando("envio uma requisicao de cadastro de usuario informando {string} invalido")
  public void envioUmaRequisicaoDeCadastroDeUsuarioInformandoInvalido(String dado) {
    User.UserBuilder usuario = User.builder();

    switch (dado.toLowerCase()) {
      case "email":
        usuario = usuario.name(faker.name().fullName())
            .email(faker.internet().emailAddress().split("@")[0])
            .gender(User.sorteiaGenero())
            .status(User.sorteiaStatus());
        break;

      case "genero":
        usuario = usuario.name(faker.name().fullName())
            .email(faker.internet().emailAddress())
            .gender("GeneroInvalido")
            .status(User.sorteiaStatus());
        break;

      case "status":
        usuario = usuario.name(faker.name().fullName())
            .email(faker.internet().emailAddress())
            .gender(User.sorteiaGenero())
            .status("StatusInvalido");
        break;
    }

    sentData = usuario.build();
    body = new JSONObject(new Gson().toJson(sentData));

    POST();
  }

  @Quando("envio uma requisicao de cadastro de usuario com email cadastrado anteriormente")
  public void envioUmaRequisicaoDeCadastroDeUsuarioComEmailCadastradoAnteriormente() {
    existeUmUsuarioCadastradoNaApi();
    POST();
  }

  @Quando("envio uma requisicao de cadastro de usuario sem informar nenhum dado")
  public void envioUmaRequisicaoDeCadastroDeUsuarioSemInformarNenhumDado() {
    body = new JSONObject();
    POST();
  }

  @Quando("buscar um usuario nao cadastrado")
  public void buscarUmUsuarioNaoCadastrado() {
    uri = prop.getProperties("uri_gorest") + "/1z2y3x";
    body = new JSONObject();
    GET();
  }
}
