package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHomePage extends Page {
  public UserHomePage(WebDriver browser) {
    super(browser);
  }

  public MePage clickProfile() {
    browser.findElement(By.className("me")).click();
    return new MePage(browser);
  }

  public String getUserName() {
    return browser.findElement(By.className("me")).getText();
  }

  public HomePage userLogout() {
    browser.findElement(By.linkText("Logout")).click();
    return new HomePage(browser);
  }

  public MyTasksPage clickTasks() {
    browser.findElement(By.linkText("My tasks")).click();
    return new MyTasksPage(browser);
  }
}
