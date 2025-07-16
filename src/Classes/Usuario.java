package Classes;

import App.App;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Usuario {
  private static int contadorId = 0;
  private int id;
  private String nome;
  private String email;
  private String senha;
  private List<Notificacao> notificacoes;

  public Usuario(String nome, String email, String senha) {
    this.id = ++contadorId;
    this.nome = nome;
    this.email = email;
    this.senha = senha;
    this.notificacoes = new ArrayList<>();
  }

  public int getId() {
    return id;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return this.senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Map<Integer, Grupo> getGrupos() {
    Map<Integer, Grupo> gruposDoUsuario = new HashMap<>();
    for (Grupo grupo : App.grupos.values()) {
      if (grupo.getMembros().containsKey(this.id)) {
        gruposDoUsuario.put(grupo.getId(), grupo);
      }
    }
    return gruposDoUsuario;
  }

  public List<Notificacao> getNotificacoes() {
    return notificacoes;
  }

  public void adicionarNotificacao(Notificacao notificacao) {
    notificacoes.add(0, notificacao);
  }

  public int getNotificacaoNaoLidas() {
    int count = 0;
    for (Notificacao notif : notificacoes) {
      if (!notif.isLida()) {
        count++;
      }
    }
    return count;
  }

  public void marcarTodasNotificacoesComoLidas() {
    for (Notificacao notif : notificacoes) {
      notif.marcarComoLida();
    }
  }

}
