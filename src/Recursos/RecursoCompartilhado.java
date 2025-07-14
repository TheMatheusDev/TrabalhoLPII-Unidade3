package Recursos;

public abstract class RecursoCompartilhado {
  protected String titulo;
  protected String autor;
  protected String descricao;

  public RecursoCompartilhado(String titulo, String autor, String descricao) {
    this.titulo = titulo;
    this.autor = autor;
    this.descricao = descricao;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getAutor() {
    return autor;
  }

  public String getDescricao() {
    return descricao;
  }

  public abstract void exibirDetalhes();
}
