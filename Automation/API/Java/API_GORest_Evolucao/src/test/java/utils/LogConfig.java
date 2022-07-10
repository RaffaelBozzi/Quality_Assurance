package utils;

public class LogConfig {
  private final String ANSI_RESET = "\u001b[0m";

  public String logVermelho(String texto) {
    String ANSI_RED = "\u001B[31m";
    return ANSI_RED + texto + ANSI_RESET;
  }

  public String logVerde(String texto) {
    String ANSI_GREEN = "\u001B[32m";
    return ANSI_GREEN + texto + ANSI_RESET;
  }

  public String logAzul(String texto) {
    String ANSI_BLUE = "\u001B[34m";
    return ANSI_BLUE + texto + ANSI_RESET;
  }
}
