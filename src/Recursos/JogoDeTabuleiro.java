package Recursos;

public class JogoDeTabuleiro extends RecursoCompartilhado {
  private int jogadoresMin;
  private int jogadoresMax;
  private String genero;

  public JogoDeTabuleiro(String titulo, String autor, String descricao, int jogadoresMin, int jogadoresMax,
      String genero) {
    super(titulo, autor, descricao);
    this.jogadoresMin = jogadoresMin;
    this.jogadoresMax = jogadoresMax;
    this.genero = genero;
  }

  @Override
  public void exibirDetalhes() {
    System.out.println("Jogo de Tabuleiro: " + titulo + " | Autor: " + autor + " | Jogadores: " + jogadoresMin + "-"
        + jogadoresMax + " | Gênero: " + genero + " | Descrição: " + descricao);
  }
}
