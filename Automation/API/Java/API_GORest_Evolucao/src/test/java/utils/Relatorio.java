package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.messages.types.PickleStep;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Relatorio extends LogConfig implements ConcurrentEventListener {

  private static String nomeStep;
  private static String nomeCase;
  private static ExtentReports extent = null;
  private static ExtentSparkReporter spark = null;
  private static ExtentTest extentNomeCenario = null;
  private static ExtentTest bdd = null;
  private static String testStepResult = "";
  private static final String path = "src/test/resources/report.html";

  public static void escreverNoRelatorio(String texto, Boolean criarNovoNode) {
    if (extent == null) {
      extent = new ExtentReports();
      spark = new ExtentSparkReporter(path);
      extent.attachReporter(spark);
    }
    if (extentNomeCenario == null) {
      extentNomeCenario = extent.createTest(nomeCase);
    }
    if (criarNovoNode) {
      bdd = extentNomeCenario.createNode(nomeStep);
    }
    if (testStepResult.equals("PASSED")) {
      bdd.pass(texto);
    } else {
      bdd.fail(texto);
      extentNomeCenario.fail(texto);
    }
    extent.flush();
  }

  private String getCurrentTimestamp() {
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
    Calendar calendar = Calendar.getInstance();
    return formatter.format(calendar.getTime());
  }

  private final EventHandler<TestStepStarted> inicioStep = new EventHandler<TestStepStarted>() {
    @Override
    public void receive(TestStepStarted event) {
      if (event.getTestStep() instanceof PickleStepTestStep) {
        nomeStep = ((PickleStepTestStep) event.getTestStep()).getStep().getKeyword() +
            ((PickleStepTestStep) event.getTestStep()).getStep().getText();
        System.out.println(logAzul("BDD> " + nomeStep));
      }
    }
  };

  private final EventHandler<TestCaseStarted> inicioCase = new EventHandler<TestCaseStarted>() {
    @Override
    public void receive(TestCaseStarted event) {
      nomeCase = event.getTestCase().getName();
      System.out.println(logAzul(getCurrentTimestamp() + " ===== INICIANDO CASO DE TESTE: " + nomeCase));
    }
  };

  private final EventHandler<TestStepFinished> fimStep = new EventHandler<TestStepFinished>() {
    @Override
    public void receive(TestStepFinished event) {
      if (nomeStep != null) {
        boolean criarNovoNode = bdd == null || !bdd.getModel().getName().equals(nomeStep);
        testStepResult = event.getResult().getStatus().toString();
        escreverNoRelatorio(nomeStep, criarNovoNode);
      }

      nomeStep = null;
//      if(nomeStep == null)
//        return;
//
//      testStepResult = event.getResult().getStatus().toString();
//      if (extent == null) {
//        extent = new ExtentReports();
//        spark = new ExtentSparkReporter(path);
//        extent.attachReporter(spark);
//      }
//      if (extentNomeCenario == null) {
//        extentNomeCenario = extent.createTest(nomeCase);
//      }
//      bdd = extentNomeCenario.createNode(nomeStep);
//      if (testStepResult.equals("PASSED")) {
//        if(toPrint.isEmpty()) {
//          bdd.pass(nomeStep);
//        }
//        else {
//          bdd.pass(toPrint);
//          toPrint = "";
//        }
//      } else {
//        bdd.fail(event.getResult().getError());
//        extentNomeCenario.fail(event.getResult().getError());
//      }
//      extent.flush();
//      nomeStep = null;
    }
  };

  private final EventHandler<TestCaseFinished> fimCase = new EventHandler<TestCaseFinished>() {
    @Override
    public void receive(TestCaseFinished event) {
      extent.flush();
      extentNomeCenario = null;
    }
  };

  @Override
  public void setEventPublisher(EventPublisher publisher) {
    publisher.registerHandlerFor(TestStepStarted.class, inicioStep);
    publisher.registerHandlerFor(TestCaseStarted.class, inicioCase);
    publisher.registerHandlerFor(TestStepFinished.class, fimStep);
    publisher.registerHandlerFor(TestCaseFinished.class, fimCase);
  }
}
