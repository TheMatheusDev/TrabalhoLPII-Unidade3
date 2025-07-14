package Recursos;

public class Livro extends RecursoCompartilhado {
  private String genero;

  public Livro(String titulo, String autor, String descricao, String genero) {
    super(titulo, autor, descricao);
    this.genero = genero;
  }

  @Override
  public void exibirDetalhes() {
    System.out
        .println("Livro: " + titulo + " | Autor: " + autor + " | Gênero: " + genero + " | Descrição: " + descricao);
  }
}
