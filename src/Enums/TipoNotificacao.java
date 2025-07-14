package Enums;

public enum TipoNotificacao {
  COMENTARIO("💬"),
  EVENTO("📅"),
  RECURSO("📚");

  private final String icone;

  TipoNotificacao(String icone) {
    this.icone = icone;
  }

  public String getIcone() {
    return icone;
  }
}
