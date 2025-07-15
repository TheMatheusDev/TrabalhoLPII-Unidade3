package Classes;

import java.time.LocalDate;
import Enums.Cargo;

public class Membro {
  private static int contadorId = 0;
  private int id;
  private Usuario usuario;
  private Cargo cargo;
  private LocalDate dataDeIngresso;
  private ConfiguracaoMembro configuracao;

  public Membro(Usuario usuario, Cargo cargo) {
    this.id = ++contadorId;
    this.usuario = usuario;
    this.cargo = cargo;
    this.dataDeIngresso = LocalDate.now();
    this.configuracao = new ConfiguracaoMembro(this);
  }

  public int getId() {
    return id;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Cargo getCargo() {
    return cargo;
  }

  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  }

  public LocalDate getDataDeIngresso() {
    return dataDeIngresso;
  }

  public ConfiguracaoMembro getConfiguracao() {
    return configuracao;
  }

  public void configurarNotificacoes(boolean comentarios, boolean eventos, boolean postagens) {
    this.configuracao.setReceberNotificacoesComentarios(comentarios);
    this.configuracao.setReceberNotificacoesEventos(eventos);
    this.configuracao.setReceberNotificacoesPostagens(postagens);
  }

  public boolean recebeNotificacoesComentarios() {
    return this.configuracao.isReceberNotificacoesComentarios();
  }

  public boolean recebeNotificacoesEventos() {
    return this.configuracao.isReceberNotificacoesEventos();
  }

  public boolean recebeNotificacoesPostagens() {
    return this.configuracao.isReceberNotificacoesPostagens();
  }

}
