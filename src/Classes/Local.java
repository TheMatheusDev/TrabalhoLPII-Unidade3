package Classes;

public class Local {
  private String nome;
  private String endereco;
  private String cidade;
  private int capacidade;

  public Local(String nome, String endereco, String cidade, int capacidade) {
    this.nome = nome;
    this.endereco = endereco;
    this.cidade = cidade;
    this.capacidade = capacidade;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public int getCapacidade() {
    return capacidade;
  }

  public void setCapacidade(int capacidade) {
    this.capacidade = capacidade;
  }

  @Override
  public String toString() {
    return nome + " - " + endereco + ", " + cidade + " (Capacidade: " + capacidade + ")";
  }
}
