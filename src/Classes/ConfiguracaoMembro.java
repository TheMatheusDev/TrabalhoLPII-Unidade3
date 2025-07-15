package Classes;

public class ConfiguracaoMembro {
  private static int contadorId = 0;
  private int id;
  private Membro membro;
  private boolean receberNotificacoesComentarios;
  private boolean receberNotificacoesEventos;
  private boolean receberNotificacoesPostagens;

  public ConfiguracaoMembro(Membro membro) {
    this.id = ++contadorId;
    this.membro = membro;

    this.receberNotificacoesComentarios = true;
    this.receberNotificacoesEventos = true;
    this.receberNotificacoesPostagens = true;
  }

  public int getId() {
    return id;
  }

  public Membro getMembro() {
    return membro;
  }

  public boolean isReceberNotificacoesComentarios() {
    return receberNotificacoesComentarios;
  }

  public void setReceberNotificacoesComentarios(boolean receberNotificacoesComentarios) {
    this.receberNotificacoesComentarios = receberNotificacoesComentarios;
  }

  public boolean isReceberNotificacoesEventos() {
    return receberNotificacoesEventos;
  }

  public void setReceberNotificacoesEventos(boolean receberNotificacoesEventos) {
    this.receberNotificacoesEventos = receberNotificacoesEventos;
  }

  public boolean isReceberNotificacoesPostagens() {
    return receberNotificacoesPostagens;
  }

  public void setReceberNotificacoesPostagens(boolean receberNotificacoesPostagens) {
    this.receberNotificacoesPostagens = receberNotificacoesPostagens;
  }

  public void desabilitarTodasNotificacoes() {
    this.receberNotificacoesComentarios = false;
    this.receberNotificacoesEventos = false;
    this.receberNotificacoesPostagens = false;
  }

  public void habilitarTodasNotificacoes() {
    this.receberNotificacoesComentarios = true;
    this.receberNotificacoesEventos = true;
    this.receberNotificacoesPostagens = true;
  }

  public void exibirConfiguracoes() {
    System.out.println("=== Configurações de Notificação ===");
    System.out.println("Comentários: " + (receberNotificacoesComentarios ? "Habilitado" : "Desabilitado"));
    System.out.println("Eventos: " + (receberNotificacoesEventos ? "Habilitado" : "Desabilitado"));
    System.out.println("Postagens: " + (receberNotificacoesPostagens ? "Habilitado" : "Desabilitado"));
  }
}
