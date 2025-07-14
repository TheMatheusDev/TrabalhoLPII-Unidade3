package Recursos;

public class Trilha extends RecursoCompartilhado {
  private double distancia;
  private String dificuldade;

  public Trilha(String titulo, String autor, String descricao, double distancia, String dificuldade) {
    super(titulo, autor, descricao);
    this.distancia = distancia;
    this.dificuldade = dificuldade;
  }

  @Override
  public void exibirDetalhes() {
    System.out.println("Trilha: " + titulo + " | Autor: " + autor + " | Distância: " + distancia + "km | Dificuldade: "
        + dificuldade + " | Descrição: " + descricao);
  }
}
