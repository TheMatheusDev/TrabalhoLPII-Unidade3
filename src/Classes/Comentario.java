package Classes;

public class Comentario extends Conteudo {
  private int comentarioID;
  private int postagemID;

  public Comentario(String autor, String texto, String data, int postagemID) {
    super(autor, texto, data);
    this.postagemID = postagemID;
  }

  public int getPostagemID() {
    return postagemID;
  }

  public void setComentarioID(int comentarioID) {
    this.comentarioID = comentarioID;
  }

  public int getComentarioID() {
    return comentarioID;
  }

  @Override
  public void exibir() {
    System.out.println("  Comentário (ID: " + comentarioID + ")");
    System.out.println("  Autor: " + autor);
    System.out.println("  Texto: " + texto);
    System.out.println("  Data: " + data);
    System.out.println("  " + "-".repeat(40));
  }
}
