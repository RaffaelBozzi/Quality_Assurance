package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AddContactPage extends Page {
  public AddContactPage(WebDriver browser) {
    super(browser);
  }

  public AddContactPage chooseContactType(String type) {
    new Select(browser.findElement(By.id("addmoredata")).findElement(By.name("type"))).selectByValue(type);
    return this;
  }

  public AddContactPage enterContact(String contact) {
    browser.findElement(By.id("addmoredata")).findElement(By.name("contact")).sendKeys(contact);
    return this;
  }

  public MePage clickSave() {
    browser.findElement(By.id("addmoredata")).findElement(By.linkText("SAVE")).click();
    return new MePage(browser);
  }

  public MePage addContact(String type, String contact) {
    chooseContactType(type);
    enterContact(contact);
    return clickSave();
  }
}
