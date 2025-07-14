package Classes;

import java.time.LocalDate;
import Enums.Cargo;

public class Membro {
  private static int contadorId = 0;
  private int id;
  private Usuario usuario;
  private Cargo cargo;
  private LocalDate dataDeIngresso;

  public Membro(Usuario usuario, Cargo cargo) {
    this.id = ++contadorId;
    this.usuario = usuario;
    this.cargo = cargo;
    this.dataDeIngresso = LocalDate.now();
  }

  public int getId() {
    return id;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Cargo getCargo() {
    return cargo;
  }

  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  }

  public LocalDate getDataDeIngresso() {
    return dataDeIngresso;
  }

}
