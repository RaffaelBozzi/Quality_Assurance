package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginFormPage extends BasePage {

  public LoginFormPage(WebDriver navegador) {
    super(navegador);
  }

  public LoginFormPage digitarLogin(String login) {
    navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);
    return this;
  }

  public LoginFormPage digitarPassword(String password) {
    navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(password);
    return this;
  }

  public UsuarioHomePage clicarSignIn() {
    navegador.findElement(By.linkText("SIGN IN")).click();
    return new UsuarioHomePage(navegador);
  }

  public UsuarioHomePage fazerLogin(String login, String password) {
    digitarLogin(login);
    digitarPassword(password);
    return clicarSignIn();
  }
}
