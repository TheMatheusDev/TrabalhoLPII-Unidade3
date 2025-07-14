package Utils;

public class LimparTela {
  public static void limparTela() {
    try {
      if (System.getProperty("os.name").toLowerCase().contains("win")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
        new ProcessBuilder("clear").inheritIO().start().waitFor();
      }
    } catch (Exception e) {
      System.out.println("Erro ao limpar a tela: " + e.getMessage());
    }
  }
}
