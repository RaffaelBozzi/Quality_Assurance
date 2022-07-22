package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MePage extends Page {
  public MePage(WebDriver browser) {
    super(browser);
  }

  public MePage clickMoreDataAboutYouTab() {
    browser.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    return this;
  }

  public AddContactPage goToAddContactPage() {
    browser.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();
    return new AddContactPage(browser);
  }

  public boolean isContactRegistered(String contact) {
    return browser.findElement(By.xpath("//span[text()=\"" + contact + "\"]")).isEnabled();
  }

  public String getContactValue(String contactType) {
    return browser.findElement(By.xpath("//a[@type=\"" + contactType + "\"]/preceding-sibling::span")).getText();
  }

  public MePage removeContact(String contact) {
    browser.findElement(By.xpath("//span[text()=\"" + contact + "\"]/following-sibling::a")).click();
    browser.switchTo().alert().accept();
    return this;
  }
}
