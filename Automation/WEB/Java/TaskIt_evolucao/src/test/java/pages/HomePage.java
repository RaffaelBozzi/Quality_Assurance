package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page {
  public HomePage(WebDriver browser) {
    super(browser);
  }

  public SignUpFormPage clickSignUp() {
    browser.findElement(By.id("signup")).click();
    return new SignUpFormPage(browser);
  }

  public SignInFormPage clickSignIn() {
    browser.findElement(By.linkText("Sign in")).click();

    return new SignInFormPage(browser);
  }

  public boolean isSignInAvaliable() {
    return browser.findElement(By.id("signinbox")).isEnabled();
  }

}
