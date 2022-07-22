package steps;

import com.github.javafaker.Faker;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import pages.HomePage;
import pages.SignUpFormPage;
import pages.UserHomePage;
import utils.Browser;

import static utils.Screenshots.takeScreenshot;

public class LoginSteps extends Browser {
  private final String filePath = "src/test/resources/relatorios/screenshots/";
  private String name;
  private String userName;
  private String password;

  @Dado("que acesso a plataforma do TaskIt")
  public void queAcessoAPlataformaDoTaskIt() {
    navigateTo("http://www.juliodelima.com.br/taskit");
  }

  @Quando("informo a intenção de me cadastrar na plataforma")
  public void informoAIntençãoDeMeCadastrarNaPlataforma() {
    (new HomePage(browser)).clickSignUp();
  }

  @E("preencho os dados de cadastro com informações válidas")
  public void preenchoOsDadosDeCadastroComInformaçõesVálidas() {
    Faker faker = new Faker();
    name = faker.name().name();
    userName = faker.name().username();
    (new SignUpFormPage(browser)).createNewUser(name, userName, "123456");

    log(" ****** Dados do usuário criado ****** ");
    log("Nome do usuário: " + name);
    log("Login do usuário: " + userName);
    log("Senha do usuário: 123456");

    takeScreenshot(browser, filePath, "UsuarioCriado");
  }

  @Entao("o novo usuário deve ser criado")
  public void oNovoUsuárioDeveSerCriado() {
    Assert.assertEquals("Hi, " + name, (new UserHomePage(browser)).getUserName());
  }

  @Quando("efetuo o login com dados do usuário {string} cadastrado previamente")
  public void efetuoOLoginComDadosDoUsuárioCadastradoPreviamente(String usuario) {
    switch (usuario) {
      case "raffael":
        name = "Raffael";
        userName = "RaffaelBozzi";
        password = "estudoautomacaoweb";
        break;
      case "julio":
        name = "Julio";
        userName = "julio0001";
        password = "123456";
        break;
    }

    (new HomePage(browser)).clickSignIn().login(userName, password);
  }

  @Entao("é exibida a página inicial do usuário")
  public void éExibidaAPáginaInicialDoUsuário() {
    oNovoUsuárioDeveSerCriado();
    log(" ****** Dados do usuário usados para login ****** ");
    log("Nome do usuário: " + name);
    log("Login do usuário: " + userName);
    log("Senha do usuário: " + password);

    takeScreenshot(browser, filePath, "UsuarioLogado");
  }

  @Dado("que o usuário está logado")
  public void queOUsuárioEstáLogado() {
    queAcessoAPlataformaDoTaskIt();
    efetuoOLoginComDadosDoUsuárioCadastradoPreviamente("raffael");
    éExibidaAPáginaInicialDoUsuário();

//    takeScreenshot(browser, filePath, "UsuarioLogado");
  }

  @Quando("efetuo o logout")
  public void efetuoOLogout() {
    (new UserHomePage(browser)).userLogout();
  }

  @Entao("é exibida a página inicial da plataforma")
  public void éExibidaAPáginaInicialDaPlataforma() {
    Assert.assertTrue((new HomePage(browser)).isSignInAvaliable());
    log(" ****** Usuário deslogado da plataforma ****** ");
//    takeScreenshot(browser, filePath, "UsuarioDeslogado");
  }
}
