package Recursos;

public class Serie extends RecursoCompartilhado {
  private int temporadas;
  private String genero;

  public Serie(String titulo, String autor, String descricao, int temporadas, String genero) {
    super(titulo, autor, descricao);
    this.temporadas = temporadas;
    this.genero = genero;
  }

  @Override
  public void exibirDetalhes() {
    System.out.println("Série: " + titulo + " | Criador: " + autor + " | Temporadas: " + temporadas + " | Gênero: "
        + genero + " | Descrição: " + descricao);
  }
}
