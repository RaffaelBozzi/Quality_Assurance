package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import pages.*;
import utils.Browser;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static utils.Screenshots.takeScreenshot;

public class AddUserTaskSteps extends Browser {
  private final String filePath = "src/test/resources/relatorios/screenshots/";
  private String taskTitle;

  @Dado("que acesso a página de atividades do usuário logado")
  public void queAcessoAPáginaDeAtividadesDoUsuárioLogado() {
    (new LoginSteps()).queOUsuárioEstáLogado();
    (new UserHomePage(browser)).clickTasks();
  }

  @Quando("informo a intenção de cadastrar uma nova atividade")
  public void informoAIntençãoDeCadastrarUmaNovaAtividade() {
    (new MyTasksPage(browser)).goToAddTaskPage();
  }

  @E("preencho os dados da atividade a ser realizada")
  public void preenchoOsDadosDaAtividadeASerRealizada() {
    AddTaskPage taskPage = new AddTaskPage(browser);

    //Detemina o limite para o dia seguinte
    Timestamp ts = new Timestamp(System.currentTimeMillis());
    Calendar cal = Calendar.getInstance();
    cal.setTime(ts);
    cal.add(Calendar.DAY_OF_WEEK, 1);
    ts.setTime(cal.getTime().getTime());
    String limitDate = new SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH).format(ts);

    taskTitle = "Titulo Teste";
    taskPage.addTask(taskTitle, limitDate, "23:55", "Teste detalhes da atividade");
  }

  @Entao("a nova atividade deve estar listada")
  public void aNovaAtividadeDeveEstarListada() {
    String toastMessage = "The task has been added, pretty simple!";
    Assert.assertEquals(toastMessage, (new Page(browser)).getToastText());
    log(" ****** Atividade \"" + taskTitle + "\" do usuário cadastrada ****** ");
    takeScreenshot(browser, filePath, "AtividadeAdicionada");
  }

//  @E("o possui ao menos uma atividade cadastrada")
//  public void oPossuiAoMenosUmaAtividadeCadastrada() {
//  }
//
//  @Quando("deleto a atividade")
//  public void deletoAAtividade() {
//  }
//
//  @Entao("a atividade deve ser removida da lista")
//  public void aAtividadeDeveSerRemovidaDaLista() {
//  }
}
