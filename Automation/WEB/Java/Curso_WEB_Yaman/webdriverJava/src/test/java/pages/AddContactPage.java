package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddContactPage extends BasePage {
  public AddContactPage(WebDriver navegador) {
    super(navegador);
  }

  public AddContactPage escolherTipoDeContato(String tipo) {
    new Select(navegador.findElement(By.id("addmoredata")).findElement(By.name("type"))).selectByValue(tipo);
    return this;
  }

  public AddContactPage digitarContato(String contato) {
    navegador.findElement(By.id("addmoredata")).findElement(By.name("contact")).sendKeys(contato);
    return this;
  }

  public MePage clicarSalvar() {
    navegador.findElement(By.id("addmoredata")).findElement(By.linkText("SAVE")).click();
    return new MePage(navegador);
  }

  public MePage adicionarContato(String tipo, String contato) {
    escolherTipoDeContato(tipo);
    digitarContato(contato);
    return clicarSalvar();
  }

}
