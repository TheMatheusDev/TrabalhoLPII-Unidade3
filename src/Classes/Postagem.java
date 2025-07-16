package Classes;

import java.util.ArrayList;
import java.util.List;

import Interfaces.Notificavel;
import Enums.TipoNotificacao;

public class Postagem extends Conteudo implements Notificavel {
  private String titulo;
  private int postagemID;
  private Grupo grupo;
  private List<Comentario> comentarios;
  private int contadorComentarios = 1;

  public Postagem(String autor, String texto, String data, String titulo, Grupo grupo) {
    this.autor = autor;
    this.texto = texto;
    this.data = data;
    this.titulo = titulo;
    this.grupo = grupo;
    this.comentarios = new ArrayList<>();
  }

  public void adicionarComentario(Comentario comentario) {
    comentario.setComentarioID(contadorComentarios++);
    comentarios.add(comentario);
    enviarNotificacao();
  }

  public List<Comentario> getComentarios() {
    return comentarios;
  }

  public Grupo getGrupo() {
    return grupo;
  }

  public int getPostagemID() {
    return postagemID;
  }

  public void setPostagemID(int postagemID) {
    this.postagemID = postagemID;
  }

  public String getTitulo() {
    return titulo;
  }

  @Override
  public void exibir() {
    System.out.println("--- " + grupo.getNome() + " - " + titulo + " ---");
    System.out.println("Autor: " + autor);
    System.out.println("Texto: " + texto);
    System.out.println("Data: " + data);
    System.out.println("Comentários:");
    for (Comentario c : comentarios) {
      c.exibir();
    }
  }

  @Override
  public void enviarNotificacao() {
    String autorComentario = "";
    if (!comentarios.isEmpty()) {
      autorComentario = comentarios.get(comentarios.size() - 1).getAutor();
    }

    String tituloNotificacao = "Novo comentário";
    String mensagemNotificacao = autorComentario + " comentou em '" + titulo + "' (" + grupo.getNome() + ")";

    for (Membro membro : grupo.getMembros().values()) {
      Usuario usuario = membro.getUsuario();
      if (!usuario.getNome().equals(autorComentario) && membro.recebeNotificacoesComentarios()) {
        Notificacao notificacao = new Notificacao(tituloNotificacao, mensagemNotificacao, TipoNotificacao.COMENTARIO,
            grupo);
        usuario.adicionarNotificacao(notificacao);
      }
    }
  }
}
