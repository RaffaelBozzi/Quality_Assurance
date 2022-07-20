package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Web {
  public static WebDriver createChrome() {
    //Abrir o navegador
    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    WebDriver navegador = new ChromeDriver();
    navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    //Ir até a URL e abrir o formulário de login
    navegador.get("http://www.juliodelima.com.br/taskit");

    return navegador;
  }
}
