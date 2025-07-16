package Classes;

import App.App;
import Enums.Cargo;
import Exceptions.UsuarioJaMembroException;
import Recursos.RecursoCompartilhado;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Grupo {
  private static int contadorId = 0;
  private int id;
  private String nome;
  private String tema;
  private String descricao;
  private Map<Integer, Membro> membros;
  private List<Postagem> postagens;
  private List<Evento> eventos;
  private List<RecursoCompartilhado> recursos;
  private int contadorPostagens = 1;

  public Grupo(String nome, String tema, String descricao) {
    this.id = ++contadorId;
    this.nome = nome;
    this.tema = tema;
    this.descricao = descricao;
    this.membros = new HashMap<Integer, Membro>();
    this.postagens = new ArrayList<>();
    this.eventos = new ArrayList<>();
    this.recursos = new ArrayList<>();
  }

  public int getId() {
    return id;
  }

  public int getQtdMembros() {
    return membros.size();
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getTema() {
    return tema;
  }

  public void setTema(String tema) {
    this.tema = tema;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Map<Integer, Membro> getMembros() {
    return membros;
  }

  public List<Postagem> getPostagens() {
    return postagens;
  }

  public void adicionarPostagem(Postagem postagem) {
    postagem.setPostagemID(contadorPostagens++);
    postagens.add(postagem);
  }

  public void adicionarMembro(Usuario usuario, Cargo cargo) throws UsuarioJaMembroException {
    if (membros.containsKey(usuario.getId())) {
      throw new UsuarioJaMembroException("Usuário já é membro deste grupo: " + nome);
    }
    Membro novoMembro = new Membro(usuario, cargo);
    membros.put(usuario.getId(), novoMembro);
  }

  public boolean removerMembro(Usuario usuario) {
    membros.remove(usuario.getId());
    if (membros.isEmpty()) {
      System.out.println("O grupo " + nome + " não possui mais membros e será deletado.");
      App.grupos.remove(this.id);
      return true;
    }
    return false;
  }

  public List<Evento> getEventos() {
    return eventos;
  }

  public void adicionarEvento(Evento evento) {
    eventos.add(evento);
    evento.enviarNotificacao();
  }

  public void removerEvento(Evento evento) {
    eventos.remove(evento);
  }

  public List<RecursoCompartilhado> getRecursos() {
    return recursos;
  }

  public void adicionarRecurso(RecursoCompartilhado recurso) {
    recursos.add(recurso);
  }

  public void removerRecurso(RecursoCompartilhado recurso) {
    recursos.remove(recurso);
  }

  public static void visualizarGrupo(Grupo grupo) {
    if (grupo == null) {
      System.out.println("Grupo não encontrado.");
      return;
    }

    System.out.println("\nGrupo: " + grupo.getNome());
    System.out.println("Tema: " + grupo.getTema());
    System.out.println("Descrição: " + grupo.getDescricao());
    System.out.println("Quantidade de Membros: " + grupo.getQtdMembros());
    return;
  }
}
