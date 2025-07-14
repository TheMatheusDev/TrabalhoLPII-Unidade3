package Classes;

public abstract class Conteudo {
  protected String autor;
  protected String texto;
  protected String data;

  public Conteudo() {
  }

  public Conteudo(String autor, String texto, String data) {
    this.autor = autor;
    this.texto = texto;
    this.data = data;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public String getTexto() {
    return texto;
  }

  public void setTexto(String texto) {
    this.texto = texto;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public abstract void exibir();
}
