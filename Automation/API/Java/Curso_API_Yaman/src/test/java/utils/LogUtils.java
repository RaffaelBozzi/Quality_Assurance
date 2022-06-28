package utils;

import io.cucumber.java.Scenario;

public class LogUtils {
    public void logInfo(String texto) {
        if(!texto.contains("{}")) {
            ScenarioUtils.addText(texto);
        }
    }

    public void logError(String texto) {
        if(!texto.contains("{}")) {
            ScenarioUtils.addText(texto);
        }
    }
}
