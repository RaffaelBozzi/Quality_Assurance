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

  private String nomeStep;
  private String nomeCase;
  private static ExtentReports extent = null;
  private static ExtentSparkReporter spark = null;
  private static ExtentTest extentNomeCenario = null;
  private static ExtentTest extentNomeCenario2 = null;
  private static ExtentTest bdd = null;
  private static String testStepResult = "";
  private int index = 0;
  final String path = "src/test/resources/report.html";

  private String getCurrentTimestamp() {
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
    Calendar calendar = Calendar.getInstance();
    return formatter.format(calendar.getTime());
  }

  private final EventHandler<TestStepStarted> inicioStep = new EventHandler<TestStepStarted>() {
    @Override
    public void receive(TestStepStarted event) {
      nomeStep = event.getTestStep().toString();
//      if(index != 0 && index != event.getTestCase().getTestSteps().size()-1) {
        nomeStep = ((PickleStepTestStep) event.getTestCase().getTestSteps().get(index)).getStep().getKeyword() +
            ((PickleStepTestStep) event.getTestCase().getTestSteps().get(index)).getStep().getText();
        System.out.println(logAzul("BDD> " + nomeStep));
//      }
      index++;
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
//      if(index == 0 && index == event.getTestCase().getTestSteps().size()-1)
//        return;

      testStepResult = event.getResult().getStatus().toString();
      if (extent == null) {
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(path);
        extent.attachReporter(spark);
      }
      if (extentNomeCenario == null) {
        extentNomeCenario = extent.createTest(nomeCase);
        extentNomeCenario2 = extent.createTest(nomeCase);
        extent.removeTest(nomeCase);
        bdd = extentNomeCenario2.createNode(nomeStep);
      }
      if (testStepResult.equals("PASSED")) {
        bdd.pass(nomeStep);
      } else {
        bdd.fail(event.getResult().getError());
        extentNomeCenario.fail(event.getResult().getError());
      }

    }
  };

  private final EventHandler<TestCaseFinished> fimCase = event -> {
    index = 0;
    extent.flush();
  };

  @Override
  public void setEventPublisher(EventPublisher publisher) {
    publisher.registerHandlerFor(TestStepStarted.class, inicioStep);
    publisher.registerHandlerFor(TestCaseStarted.class, inicioCase);
    publisher.registerHandlerFor(TestStepFinished.class, fimStep);
    publisher.registerHandlerFor(TestCaseFinished.class, fimCase);
  }
}
