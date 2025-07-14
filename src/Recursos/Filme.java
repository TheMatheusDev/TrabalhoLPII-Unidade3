package Recursos;

public class Filme extends RecursoCompartilhado {
  private int ano;
  private String genero;

  public Filme(String titulo, String autor, String descricao, int ano, String genero) {
    super(titulo, autor, descricao);
    this.ano = ano;
    this.genero = genero;
  }

  @Override
  public void exibirDetalhes() {
    System.out.println("Filme: " + titulo + " | Diretor: " + autor + " | Ano: " + ano + " | Gênero: " + genero
        + " | Descrição: " + descricao);
  }
}
