package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddTaskPage extends Page {
  public AddTaskPage(WebDriver browser) {
    super(browser);
  }

  public AddTaskPage enterTaskTitle(String taskTitle) {
    browser.findElement(By.name("title")).sendKeys(taskTitle);
    return this;
  }

  public AddTaskPage enterTaskDateLimit(String limitDate) {
    browser.findElement(By.name("date")).click();
    browser.findElement(By.xpath("//*[@aria-label=\"" + limitDate + "\"]")).click();
    browser.findElement(By.className("picker__close")).click();
    return this;
  }

  public AddTaskPage enterTaskTimeLimit(String limitTime) {
    try {
      //Há uma falha no site que ao clicar no campo para seleção do horário ele não permanece aberto, ao tentar enviar algum texto o popup permanece aberto
      browser.findElement(By.name("time")).sendKeys(limitTime);
    } catch (Exception e) {
      browser.findElement(By.xpath("//div[@class=\"clockpicker-tick\" and text()=\"" + limitTime.split(":")[0] + "\"]")).click();
      browser.findElement(By.xpath("//div[@class=\"clockpicker-tick\" and text()=\"" + limitTime.split(":")[1] + "\"]")).click();
      browser.findElement(By.xpath("//button[text()=\"OK\"]")).click();
    }
    return this;
  }

  public AddTaskPage enterTaskDetails(String details) {
    browser.findElement(By.name("text")).sendKeys(details);
    return this;
  }

  public MyTasksPage clickSave() {
    browser.findElement(By.linkText("SAVE")).click();
    return new MyTasksPage(browser);
  }

  public MyTasksPage addTask(String title, String limitDate, String limitTime, String details) {
    return enterTaskTitle(title)
        .enterTaskDateLimit(limitDate)
        .enterTaskTimeLimit(limitTime)
        .enterTaskDetails(details)
        .clickSave();
  }
}
