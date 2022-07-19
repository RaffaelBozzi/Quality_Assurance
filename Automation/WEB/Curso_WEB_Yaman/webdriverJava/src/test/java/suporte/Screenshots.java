package suporte;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Screenshots {
  public static void tirarScreenshot(WebDriver navegador, String nomeArquivo) {
    File screenshot = ((TakesScreenshot) navegador).getScreenshotAs(OutputType.FILE);
    try {
      FileUtils.copyFile(screenshot, new File(nomeArquivo));
    } catch (IOException e) {
      System.out.println("Erro ao criar arquivo de screenshot: " + e.getMessage());
    }
  }
}
