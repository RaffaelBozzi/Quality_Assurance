package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.Web;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioPageObjectsTest.csv")
public class InformacoesUsuarioPageObjectsTest {
  private WebDriver navegador;

  @Before
  public void setup() {
    navegador = Web.createChrome();
  }

  @After
  public void tearDown() {
    navegador.quit();
  }

  @Test
  public void adicionarContatoDoUsuario(
      @Param(name="login") String login,
      @Param(name="senha") String senha,
      @Param(name="tipo") String tipo,
      @Param(name="contato") String contato,
      @Param(name="mensagem") String mensagemEsperada
  ) {
    String mensagemRetornada = new LoginPage(navegador)
        .clicarSignIn()
        .fazerLogin(login, senha)
        .clicarMe()
        .clicarAbaMoreDataAboutYou()
        .clicarBotaoAddMoreDataAboutYou()
        .adicionarContato(tipo, contato)
        .capturarTextoToast();

    Assert.assertEquals(mensagemEsperada, mensagemRetornada);
  }
}
