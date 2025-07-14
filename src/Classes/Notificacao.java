package Classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Enums.TipoNotificacao;

public class Notificacao {
  private static int contadorId = 0;
  private int id;
  private String titulo;
  private String mensagem;
  private LocalDateTime dataHora;
  private boolean lida;
  private TipoNotificacao tipoNotificacao;
  private Grupo grupo;

  public Notificacao(String titulo, String mensagem, TipoNotificacao tipoNotificacao, Grupo grupo) {
    this.id = ++contadorId;
    this.titulo = titulo;
    this.mensagem = mensagem;
    this.tipoNotificacao = tipoNotificacao;
    this.grupo = grupo;
    this.dataHora = LocalDateTime.now();
    this.lida = false;
  }

  public int getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getMensagem() {
    return mensagem;
  }

  public LocalDateTime getDataHora() {
    return dataHora;
  }

  public boolean isLida() {
    return lida;
  }

  public void marcarComoLida() {
    this.lida = true;
  }

  public TipoNotificacao getTipoNotificacao() {
    return tipoNotificacao;
  }

  public Grupo getGrupo() {
    return grupo;
  }

  public void exibir() {
    String statusLeitura = lida ? " " : " [NOVA] ";
    String icone = tipoNotificacao != null ? tipoNotificacao.getIcone() : "🔔";

    System.out.println(icone + statusLeitura + titulo);
    System.out.println("   " + mensagem);
    System.out.println("   " + dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    System.out.println("   " + "-".repeat(50));
  }
}
