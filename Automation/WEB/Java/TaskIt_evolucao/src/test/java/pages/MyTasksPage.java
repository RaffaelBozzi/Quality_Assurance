package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyTasksPage extends Page{
  public MyTasksPage(WebDriver browser) {
    super(browser);
  }


  public AddTaskPage goToAddTaskPage() {
    browser.findElement(By.xpath("//button[@data-target=\"addtask\"]")).click();
    return new AddTaskPage(browser);
  }

  public boolean isTaskRegistered(String task) {
    return browser.findElement(By.xpath("//span[text()=\"" + task + "\"]")).isEnabled();
  }

  public String getTaskValue(String task) {
    return browser.findElement(By.xpath("//a[@type=\"" + task + "\"]/preceding-sibling::span")).getText();
  }

  public MyTasksPage removeTask(String task) {
//    browser.findElement(By.xpath("//span[text()=\"" + task + "\"]/following-sibling::a")).click();
//    browser.switchTo().alert().accept();
    return this;
  }
}
