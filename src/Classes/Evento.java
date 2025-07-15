package Classes;

import Exceptions.EventoLotadoException;
import Interfaces.Notificavel;
import Enums.TipoNotificacao;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Evento implements Notificavel {
  private static int contadorId = 0;
  private int id;
  private String titulo;
  private LocalDateTime data;
  private Local local;
  private Grupo grupo;
  private ArrayList<Usuario> participantes;
  private int capacidadeMaxima;
  private String descricao;

  public Evento(String titulo, LocalDateTime data, Local local, Grupo grupo, int capacidadeMaxima, String descricao) {
    this.id = ++contadorId;
    this.titulo = titulo;
    this.data = data;
    this.local = local;
    this.grupo = grupo;
    this.capacidadeMaxima = capacidadeMaxima;
    this.descricao = descricao;
    this.participantes = new ArrayList<>();
  }

  public int getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public LocalDateTime getData() {
    return data;
  }

  public void setData(LocalDateTime data) {
    this.data = data;
  }

  public Local getLocal() {
    return local;
  }

  public void setLocal(Local local) {
    this.local = local;
  }

  public Grupo getGrupo() {
    return grupo;
  }

  public ArrayList<Usuario> getParticipantes() {
    return participantes;
  }

  public int getCapacidadeMaxima() {
    return capacidadeMaxima;
  }

  public void setCapacidadeMaxima(int capacidadeMaxima) {
    this.capacidadeMaxima = capacidadeMaxima;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public boolean isLotado() {
    return participantes.size() >= capacidadeMaxima;
  }

  public void adicionarParticipante(Usuario usuario) throws EventoLotadoException {
    if (isLotado()) {
      throw new EventoLotadoException(
          "Evento já atingiu a capacidade máxima de " + capacidadeMaxima + " participantes.");
    }

    if (!participantes.contains(usuario)) {
      participantes.add(usuario);
    }
  }

  public void removerParticipante(Usuario usuario) {
    participantes.remove(usuario);
  }

  public boolean isParticipante(Usuario usuario) {
    return participantes.contains(usuario);
  }

  @Override
  public void enviarNotificacao() {
    String tituloNotificacao = "Novo evento";
    String mensagemNotificacao = "'" + titulo + "' em " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
        " (" + grupo.getNome() + ")";

    for (Membro membro : grupo.getMembros().values()) {
      Usuario usuario = membro.getUsuario();
      if (membro.recebeNotificacoesEventos()) {
        Notificacao notificacao = new Notificacao(tituloNotificacao, mensagemNotificacao, TipoNotificacao.EVENTO,
            grupo);
        usuario.adicionarNotificacao(notificacao);
      }
    }
  }

  public void exibirDetalhes() {
    System.out.println("=== DETALHES DO EVENTO ===");
    System.out.println("ID: " + id);
    System.out.println("Título: " + titulo);
    System.out.println("Data: " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    System.out.println("Local: " + local.toString());
    System.out.println("Grupo: " + grupo.getNome());
    System.out.println("Descrição: " + descricao);
    System.out.println("Participantes: " + participantes.size() + "/" + capacidadeMaxima);

    if (!participantes.isEmpty()) {
      System.out.println("\nLista de Participantes:");
      for (Usuario participante : participantes) {
        System.out.println("- " + participante.getNome() + " (" + participante.getEmail() + ")");
      }
    }
  }
}
