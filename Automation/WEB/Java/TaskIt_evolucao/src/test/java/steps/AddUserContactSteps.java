package steps;

import com.github.javafaker.Faker;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import pages.AddContactPage;
import pages.MePage;
import pages.Page;
import pages.UserHomePage;
import utils.Browser;

import static utils.Screenshots.takeScreenshot;

public class AddUserContactSteps extends Browser {
  private final String filePath = "src/test/resources/relatorios/screenshots/";
  private String contact;
  private String contactType;

  @Dado("que acesso a página de contatos do usuário logado")
  public void queAcessoAPáginaDeContatosDoUsuárioLogado() {
    (new LoginSteps()).queOUsuárioEstáLogado();
    (new UserHomePage(browser)).clickProfile();
  }

  @Quando("informo a intenção de cadastrar um novo meio de contato")
  public void informoAIntençãoDeCadastrarUmNovoMeioDeContato() {
    (new MePage(browser)).clickMoreDataAboutYouTab().goToAddContactPage();
  }

  @E("preencho os dados do {string} do usuário")
  public void preenchoOsDadosDoDoUsuário(String informedContactType) {
    String type = "email";
    contactType = informedContactType;
    switch (informedContactType) {
      case "email":
        contact = (new Faker()).internet().emailAddress();
        break;
      case "telefone":
        contact = (new Faker()).phoneNumber().cellPhone();
        type = "phone";
        break;
    }

    (new AddContactPage(browser)).addContact(type, contact);
    takeScreenshot(browser, filePath, informedContactType + "Cadastrado");
  }

  @Entao("o novo contato deve estar listado")
  public void oNovoContatoDeveEstarListado() {
    String toastMessage = "Your contact has been added!";
    Assert.assertEquals(toastMessage, (new Page(browser)).getToastText());
    Assert.assertTrue(new MePage(browser).isContactRegistered(contact));
    log(" ****** " + contactType.toUpperCase() + " do usuário " + contact + " cadastrado ****** ");
  }

  @E("o possui ao menos um {string} cadastrado")
  public void oPossuiAoMenosUmCadastrado(String informedContactType) {
    MePage contactPage = (new MePage(browser)).clickMoreDataAboutYouTab();

    contactType = informedContactType;
    switch (informedContactType) {
      case "email":
        contact = contactPage.getContactValue("email");
        break;
      case "telefone":
        contact = contactPage.getContactValue("phone");
        break;
    }
    Assert.assertTrue(contactPage.isContactRegistered(contact));
    takeScreenshot(browser, filePath, informedContactType + "ÀDeletar");
  }

  @Quando("deleto o {string}")
  public void deletoO(String informedContactType) {
    (new MePage(browser)).removeContact(contact);

    contactType = informedContactType;
    String type;
    if (informedContactType.equals("telefone")) {
      type = "phone";
    } else {
      type = "email";
    }

    String toastMessage = "Rest in peace, dear " + type + "!";
    Assert.assertEquals(toastMessage, (new Page(browser)).getToastText());
    takeScreenshot(browser, filePath, informedContactType + "Deletado");
  }

  @Entao("o contato deve ser removido da lista")
  public void oContatoDeveSerRemovidoDaLista() {
    log(" ****** " + contactType.toUpperCase() + " do usuário " + contact + " deletado ****** ");
  }
}
