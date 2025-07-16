package Menus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import App.App;
import Classes.Evento;
import Classes.Grupo;
import Classes.Local;
import Classes.Usuario;
import Enums.Cargo;
import Exceptions.EventoLotadoException;
import Utils.LimparTela;

public class Eventos {

  public static void verEventosGrupo(Usuario usuarioLogado, Grupo grupo) {
    Cargo cargoUsuario = grupo.getMembros().get(usuarioLogado.getId()).getCargo();

    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("  EVENTOS DO GRUPO: " + grupo.getNome());
      System.out.println("=========================================");

      if (grupo.getEventos().isEmpty()) {
        System.out.println("Nenhum evento encontrado neste grupo.");
        System.out.println("\nPressione Enter para voltar...");
        App.scanner.nextLine();
        return;
      }

      System.out.println("Lista de Eventos:");
      for (Evento evento : grupo.getEventos()) {
        String status = evento.isParticipante(usuarioLogado) ? " (INSCRITO)" : "";
        System.out.println("[" + evento.getId() + "] " + evento.getTitulo() +
            " - " + evento.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) +
            status);
      }

      System.out.println("\n==========================================");
      System.out.println("\n[1] Ver detalhes de um evento");
      System.out.println("[2] Inscrever-se/Cancelar inscrição");

      if (cargoUsuario == Cargo.ADMIN) {
        System.out.println("[3] Deletar evento (Admin)");
      }

      System.out.println("[0] Voltar");
      System.out.print("\nEscolha uma opção: ");

      String escolha = App.scanner.nextLine();
      switch (escolha) {
        case "1":
          verDetalhesEvento(grupo);
          break;
        case "2":
          gerenciarInscricaoEvento(usuarioLogado, grupo);
          break;
        case "3":
          if (cargoUsuario == Cargo.ADMIN) {
            deletarEvento(grupo);
          } else {
            System.out.println("Opção inválida! Pressione Enter para continuar...");
            App.scanner.nextLine();
          }
          break;
        case "0":
          return;
        default:
          System.out.println("Opção inválida! Pressione Enter para continuar...");
          App.scanner.nextLine();
      }
    }
  }

  public static void deletarEvento(Grupo grupo) {
    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("  DELETAR EVENTO");
      System.out.println("=========================================");

      if (grupo.getEventos().isEmpty()) {
        System.out.println("Nenhum evento encontrado neste grupo.");
        System.out.println("\nPressione Enter para voltar...");
        App.scanner.nextLine();
        return;
      }

      System.out.println("Lista de Eventos:");
      for (Evento evento : grupo.getEventos()) {
        System.out.println("[" + evento.getId() + "] " + evento.getTitulo() +
            " - " + evento.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) +
            " (" + evento.getParticipantes().size() + "/" + evento.getCapacidadeMaxima() + " participantes)");
      }

      System.out.println("\n[0] Voltar");
      System.out.print("\nDigite o ID do evento que deseja deletar: ");
      String input = App.scanner.nextLine();

      if (input.equals("0")) {
        return;
      }

      try {
        int idEvento = Integer.parseInt(input);
        Evento eventoEncontrado = null;

        for (Evento evento : grupo.getEventos()) {
          if (evento.getId() == idEvento) {
            eventoEncontrado = evento;
            break;
          }
        }

        if (eventoEncontrado == null) {
          System.out.println("Evento não encontrado!");
          System.out.println("Pressione Enter para continuar...");
          App.scanner.nextLine();
          continue;
        }

        System.out.println("\nVocê tem certeza que deseja deletar o evento '" + eventoEncontrado.getTitulo() + "'?");
        System.out.println("Esta ação não pode ser desfeita.");
        System.out.print("Digite 'CONFIRMAR' para deletar ou qualquer outra coisa para cancelar: ");
        String confirmacao = App.scanner.nextLine();

        if (confirmacao.equals("CONFIRMAR")) {
          grupo.removerEvento(eventoEncontrado);
          System.out.println("Evento deletado com sucesso!");
        } else {
          System.out.println("Operação cancelada.");
        }
      } catch (NumberFormatException e) {
        System.out.println("ID inválido!");
      }

      System.out.println("Pressione Enter para continuar...");
      App.scanner.nextLine();
    }
  }

  public static void criarEvento(Usuario usuarioLogado, Grupo grupo) {
    LimparTela.limparTela();
    System.out.println("=========================================");
    System.out.println("  CRIAR NOVO EVENTO");
    System.out.println("=========================================");

    System.out.print("Título do evento: ");
    String titulo = App.scanner.nextLine();

    System.out.print("Descrição do evento: ");
    String descricao = App.scanner.nextLine();

    System.out.print("Data e hora (dd/MM/yyyy HH:mm): ");
    String dataStr = App.scanner.nextLine();

    LocalDateTime data;
    try {
      data = LocalDateTime.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    } catch (DateTimeParseException e) {
      System.out.println("Formato de data inválido!");
      System.out.println("\nPressione Enter para continuar...");
      App.scanner.nextLine();
      return;
    }

    System.out.print("Nome do local: ");
    String nomeLocal = App.scanner.nextLine();

    System.out.print("Endereço: ");
    String endereco = App.scanner.nextLine();

    System.out.print("Cidade: ");
    String cidade = App.scanner.nextLine();

    System.out.print("Capacidade máxima: ");
    int capacidade;
    try {
      capacidade = Integer.parseInt(App.scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Capacidade inválida!");
      System.out.println("\nPressione Enter para continuar...");
      App.scanner.nextLine();
      return;
    }

    if (titulo.isEmpty() || descricao.isEmpty() || nomeLocal.isEmpty() || endereco.isEmpty() || cidade.isEmpty()) {
      System.out.println("Todos os campos são obrigatórios!");
      System.out.println("\nPressione Enter para continuar...");
      App.scanner.nextLine();
      return;
    }

    Local local = new Local(nomeLocal, endereco, cidade, capacidade);
    Evento novoEvento = new Evento(titulo, data, local, grupo, capacidade, descricao);
    grupo.adicionarEvento(novoEvento);

    System.out.println("Evento criado com sucesso!");
    System.out.println("\nPressione Enter para continuar...");
    App.scanner.nextLine();
  }

  public static void verDetalhesEvento(Grupo grupo) {
    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("  DETALHES DO EVENTO");
      System.out.println("=========================================");

      System.out.println("Lista de Eventos:");
      for (Evento evento : grupo.getEventos()) {
        System.out.println("[" + evento.getId() + "] " + evento.getTitulo() +
            " - " + evento.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
      }

      System.out.println("\n[0] Voltar");
      System.out.print("\nDigite o ID do evento para ver detalhes: ");
      String input = App.scanner.nextLine();

      if (input.equals("0")) {
        return;
      }

      try {
        int id = Integer.parseInt(input);
        Evento evento = grupo.getEventos().stream()
            .filter(e -> e.getId() == id)
            .findFirst()
            .orElse(null);

        if (evento == null) {
          System.out.println("Evento não encontrado!");
          System.out.println("Pressione Enter para continuar...");
          App.scanner.nextLine();
          continue;
        }

        LimparTela.limparTela();
        evento.exibirDetalhes();
        System.out.println("\nPressione Enter para continuar...");
        App.scanner.nextLine();
      } catch (NumberFormatException e) {
        System.out.println("ID inválido!");
        System.out.println("Pressione Enter para continuar...");
        App.scanner.nextLine();
      }
    }
  }

  public static void gerenciarInscricaoEvento(Usuario usuarioLogado, Grupo grupo) {
    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("  GERENCIAR INSCRIÇÃO EM EVENTO");
      System.out.println("=========================================");

      System.out.println("Lista de Eventos:");
      for (Evento evento : grupo.getEventos()) {
        String status = evento.isParticipante(usuarioLogado) ? " (INSCRITO)" : " (NÃO INSCRITO)";
        System.out.println("[" + evento.getId() + "] " + evento.getTitulo() +
            " - " + evento.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) +
            status);
      }

      System.out.println("\n[0] Voltar");
      System.out.print("\nDigite o ID do evento: ");
      String input = App.scanner.nextLine();

      if (input.equals("0")) {
        return;
      }

      try {
        int id = Integer.parseInt(input);
        Evento evento = null;
        for (Evento e : grupo.getEventos()) {
          if (e.getId() == id) {
            evento = e;
            break;
          }
        }

        if (evento == null) {
          System.out.println("Evento não encontrado!");
          System.out.println("Pressione Enter para continuar...");
          App.scanner.nextLine();
          continue;
        }

        if (evento.isParticipante(usuarioLogado)) {
          evento.removerParticipante(usuarioLogado);
          System.out.println("Inscrição cancelada com sucesso!");
          System.out.println("Pressione Enter para continuar...");
          App.scanner.nextLine();
          return;
        }

        try {
          evento.adicionarParticipante(usuarioLogado);
          System.out.println("Inscrição realizada com sucesso!");
        } catch (EventoLotadoException e) {
          System.out.println("Erro: " + e.getMessage());
        }
        System.out.println("Pressione Enter para continuar...");
        App.scanner.nextLine();
      } catch (NumberFormatException e) {
        System.out.println("ID inválido!");
        System.out.println("Pressione Enter para continuar...");
        App.scanner.nextLine();
      }
    }
  }
}
