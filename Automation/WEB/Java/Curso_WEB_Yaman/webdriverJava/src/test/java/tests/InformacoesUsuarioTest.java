package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshots;

import java.time.Duration;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTest.csv")
public class InformacoesUsuarioTest {
  private WebDriver navegador;
  private final String path = "C:\\Users\\Paulo Carvalho\\IdeaProjects\\Quality_Assurance\\Automation\\WEB\\Curso_WEB_Yaman\\webdriverJava\\screenshots\\";

  @Rule
  public TestName testName = new TestName();

  @Before
  public void setup() {
    //Abrir o navegador
    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    navegador = new ChromeDriver();
    navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    //Ir até a URL e abrir o formulário de login
    navegador.get("http://www.juliodelima.com.br/taskit");

    //Faz login
    navegador.findElement(By.linkText("Sign in")).click();
    WebElement formularioSingInBox = navegador.findElement(By.id("signinbox"));
    formularioSingInBox.findElement(By.name("login")).sendKeys("RaffaelBozzi");
    formularioSingInBox.findElement(By.name("password")).sendKeys("estudoautomacaoweb");
    formularioSingInBox.findElement(By.linkText("SIGN IN")).click();

    navegador.findElement(By.className("me")).click();
    navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
  }

  @After
  public void tearDown() {
    navegador.quit();
  }

//  @Test
//  public void testLoginJulio() {
//    navegador.findElement(By.linkText("Sign in")).click();
//
//    //Faz login
//    WebElement formularioSingInBox = navegador.findElement(By.id("signinbox"));
//    formularioSingInBox.findElement(By.name("login")).sendKeys("julio0001");
//    formularioSingInBox.findElement(By.name("password")).sendKeys("123456");
//    formularioSingInBox.findElement(By.linkText("SIGN IN")).click();
//
//    WebElement me = navegador.findElement(By.className("me"));
//    Assert.assertEquals("Hi, Julio", me.getText());
//  }

  @Test
  public void adicionarContatoDoUsuario(
      @Param(name="tipo") String tipo,
      @Param(name="contato") String contato,
      @Param(name="mensagem") String mensagem
  ) {
    navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();
    WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

    new Select(popupAddMoreData.findElement(By.name("type"))).selectByValue(tipo);

    popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);
    popupAddMoreData.findElement(By.linkText("SAVE")).click();

    WebElement mensagemTelefoneAdicionado = navegador.findElement(By.id("toast-container"));
    Assert.assertEquals(mensagem, mensagemTelefoneAdicionado.getText());
    Screenshots.tirarScreenshot(navegador, path + Generator.dataHoraParaArquivo() + testName.getMethodName() + ".png");
  }

  @Test
  public void removerTelefoneDoUsuario() {
    navegador.findElement(By.xpath("//span[text()=\"+5500987654321\"]/following-sibling::a")).click();
    navegador.switchTo().alert().accept();

    WebElement mensagemTelefoneRemovido = navegador.findElement(By.id("toast-container"));
    Assert.assertEquals("Rest in peace, dear phone!", mensagemTelefoneRemovido.getText());

    Screenshots.tirarScreenshot(navegador, path + Generator.dataHoraParaArquivo() + testName.getMethodName() + ".png");

    WebDriverWait aguardar = new WebDriverWait(navegador, Duration.ofSeconds(10));
    aguardar.until(ExpectedConditions.stalenessOf(mensagemTelefoneRemovido));

    navegador.findElement(By.linkText("Logout")).click();
  }

}
