package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Page {
  protected WebDriver browser;

  public Page(WebDriver browser) {
    this.browser = browser;
  }

  public String getToastText() {
    return browser.findElement(By.id("toast-container")).getText();
  }
}
