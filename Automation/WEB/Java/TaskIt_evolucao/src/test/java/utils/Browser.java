package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Browser {
  protected static WebDriver browser;
  protected static LogUtils logger = new LogUtils();

  public void createChromeBrowser() {
    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    browser = new ChromeDriver();

    browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    browser.manage().window().maximize();
  }

  public void quitBrowser() {
    if (browser != null) {
      browser.quit();
      browser = null;
    }
  }

  public void navigateTo(String url) {
    browser.get(url);
  }

  public void log(String texto) {
    logger.logInfo(texto);
  }
}
