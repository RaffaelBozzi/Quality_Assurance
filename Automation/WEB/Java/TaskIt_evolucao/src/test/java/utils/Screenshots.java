package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Screenshots {
  public static void takeScreenshot(WebDriver browser, String filePath, String stepName) {
    File screenshot = ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);
    try {
      FileUtils.copyFile(screenshot, new File(filePath + timestampForFileName() + stepName + ".png"));
    } catch (IOException e) {
      System.out.println("Erro ao criar arquivo de screenshot: " + e.getMessage());
    }
  }

  public static String timestampForFileName() {
    Timestamp ts = new Timestamp(System.currentTimeMillis());
    return new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(ts);
  }
}
