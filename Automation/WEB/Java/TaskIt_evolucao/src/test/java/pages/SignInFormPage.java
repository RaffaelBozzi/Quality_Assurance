package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInFormPage extends Page {
  public SignInFormPage(WebDriver browser) {
    super(browser);
  }

  public SignInFormPage fillLogin(String login) {
    browser.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);
    return this;
  }

  public SignInFormPage fillPassword(String password) {
    browser.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(password);
    return this;
  }

  public UserHomePage clickSignIn() {
    browser.findElement(By.linkText("SIGN IN")).click();
    return new UserHomePage(browser);
  }

  public UserHomePage login(String login, String password) {
    fillLogin(login);
    fillPassword(password);
    return clickSignIn();
  }
}
