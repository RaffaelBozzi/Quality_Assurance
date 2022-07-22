package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpFormPage extends Page {
  public SignUpFormPage(WebDriver browser) {
    super(browser);
  }

  public SignUpFormPage fillName(String name) {
    browser.findElement(By.id("name-sign-up")).sendKeys(name);
    return this;
  }

  public SignUpFormPage fillLoginUserName(String login) {
    browser.findElement(By.id("login-sign-up")).sendKeys(login);
    return this;
  }

  public SignUpFormPage fillLoginPassword(String password) {
    browser.findElement(By.id("password-sign-up")).sendKeys(password);
    return this;
  }

  public UserHomePage saveSignUp() {
    browser.findElement(By.id("btn-submit-sign-up")).click();
    return new UserHomePage(browser);
  }

  public UserHomePage createNewUser(String name, String login, String password) {
    return fillName(name).fillLoginUserName(login).fillLoginPassword(password).saveSignUp();
  }
}
