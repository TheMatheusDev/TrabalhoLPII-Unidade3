package Menu;

import java.util.Scanner;
import Classes.Grupo;
import Classes.Membro;
import Classes.Notificacao;
import Classes.Usuario;
import Enums.Cargo;
import Enums.TipoNotificacao;
import Recursos.*;
import Utils.LimparTela;

public class Recursos {
  private static Scanner scan = new Scanner(System.in);

  public static void gerenciarBiblioteca(Usuario usuarioLogado, Grupo grupo) {
    Cargo cargoUsuario = grupo.getMembros().get(usuarioLogado.getId()).getCargo();

    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("  BIBLIOTECA DO GRUPO: " + grupo.getNome());
      System.out.println("=========================================");

      if (grupo.getRecursos().isEmpty()) {
        System.out.println("Nenhum recurso encontrado na biblioteca.");
      } else {
        System.out.println("Recursos disponíveis:");
        for (int i = 0; i < grupo.getRecursos().size(); i++) {
          RecursoCompartilhado recurso = grupo.getRecursos().get(i);
          System.out.println("[" + (i + 1) + "] " + recurso.getTitulo() + " - " + recurso.getClass().getSimpleName());
        }
      }

      System.out.println("\n==========================================");
      System.out.println("\n[1] Ver detalhes de um recurso");

      if (cargoUsuario == Cargo.ADMIN) {
        System.out.println("[2] Adicionar novo recurso (Admin)");
        if (!grupo.getRecursos().isEmpty()) {
          System.out.println("[3] Excluir recurso (Admin)");
        }
      }

      System.out.println("[0] Voltar");
      System.out.print("\nEscolha uma opção: ");

      String escolha = scan.nextLine();
      switch (escolha) {
        case "1":
          verDetalhesRecurso(grupo);
          break;
        case "2":
          if (cargoUsuario == Cargo.ADMIN) {
            adicionarRecurso(usuarioLogado, grupo);
          } else {
            System.out.println("Apenas administradores podem adicionar recursos!");
            System.out.println("Pressione Enter para continuar...");
            scan.nextLine();
          }
          break;
        case "3":
          if (cargoUsuario == Cargo.ADMIN && !grupo.getRecursos().isEmpty()) {
            excluirRecurso(usuarioLogado, grupo);
          } else {
            System.out.println("Apenas administradores podem excluir recursos!");
            System.out.println("Pressione Enter para continuar...");
            scan.nextLine();
          }
          break;
        case "0":
          return;
        default:
          System.out.println("Opção inválida! Pressione Enter para continuar...");
          scan.nextLine();
      }
    }
  }

  public static void verDetalhesRecurso(Grupo grupo) {
    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("  DETALHES DOS RECURSOS");
      System.out.println("=========================================");

      if (grupo.getRecursos().isEmpty()) {
        System.out.println("Nenhum recurso na biblioteca!");
        System.out.println("\n[0] Voltar");
        System.out.print("\nEscolha uma opção: ");
        String opcao = scan.nextLine();
        if (opcao.equals("0")) {
          return;
        }
        continue;
      }

      System.out.println("Recursos disponíveis:");
      for (int i = 0; i < grupo.getRecursos().size(); i++) {
        RecursoCompartilhado recurso = grupo.getRecursos().get(i);
        System.out.println("[" + (i + 1) + "] " + recurso.getTitulo() + " - " + recurso.getClass().getSimpleName());
      }

      System.out.println("\n[0] Voltar");
      System.out.print("\nDigite o número do recurso: ");
      String input = scan.nextLine();

      if (input.equals("0")) {
        return;
      }

      try {
        int indice = Integer.parseInt(input) - 1;
        if (indice >= 0 && indice < grupo.getRecursos().size()) {
          LimparTela.limparTela();
          RecursoCompartilhado recurso = grupo.getRecursos().get(indice);
          recurso.exibirDetalhes();
          System.out.println("\nPressione Enter para continuar...");
          scan.nextLine();
        } else {
          System.out.println("Recurso não encontrado!");
          System.out.println("Pressione Enter para continuar...");
          scan.nextLine();
        }
      } catch (NumberFormatException e) {
        System.out.println("Número inválido!");
        System.out.println("Pressione Enter para continuar...");
        scan.nextLine();
      }
    }
  }

  public static void adicionarRecurso(Usuario usuarioLogado, Grupo grupo) {
    Cargo cargoUsuario = grupo.getMembros().get(usuarioLogado.getId()).getCargo();
    if (cargoUsuario != Cargo.ADMIN) {
      System.out.println("Apenas administradores podem adicionar recursos à biblioteca!");
      System.out.println("\nPressione Enter para continuar...");
      scan.nextLine();
      return;
    }

    LimparTela.limparTela();
    System.out.println("=========================================");
    System.out.println("  ADICIONAR RECURSO À BIBLIOTECA");
    System.out.println("=========================================");

    System.out.println("Tipo de recurso:");
    System.out.println("[1] Livro");
    System.out.println("[2] Trilha");
    System.out.println("[3] Filme");
    System.out.println("[4] Série");
    System.out.println("[5] Jogo de Tabuleiro");
    System.out.print("\nEscolha o tipo: ");
    String tipo = scan.nextLine();

    System.out.print("Título: ");
    String titulo = scan.nextLine();

    System.out.print("Autor/Criador: ");
    String autor = scan.nextLine();

    System.out.print("Descrição: ");
    String descricao = scan.nextLine();

    if (titulo.isEmpty() || autor.isEmpty() || descricao.isEmpty()) {
      System.out.println("Título, autor e descrição são obrigatórios!");
      System.out.println("\nPressione Enter para continuar...");
      scan.nextLine();
      return;
    }

    RecursoCompartilhado recurso = null;
    switch (tipo) {
      case "1": // Livro
        System.out.print("Gênero: ");
        String genero = scan.nextLine();
        recurso = new Livro(titulo, autor, descricao, genero);
        break;

      case "2": // Trilha
        System.out.print("Distância (km): ");
        try {
          double distancia = Double.parseDouble(scan.nextLine());
          System.out.print("Dificuldade: ");
          String dificuldade = scan.nextLine();
          recurso = new Trilha(titulo, autor, descricao, distancia, dificuldade);
        } catch (NumberFormatException e) {
          System.out.println("Distância inválida!");
          System.out.println("\nPressione Enter para continuar...");
          scan.nextLine();
          return;
        }
        break;

      case "3": // Filme
        System.out.print("Ano: ");
        try {
          int ano = Integer.parseInt(scan.nextLine());
          System.out.print("Gênero: ");
          String generoFilme = scan.nextLine();
          recurso = new Filme(titulo, autor, descricao, ano, generoFilme);
        } catch (NumberFormatException e) {
          System.out.println("Ano inválido!");
          System.out.println("\nPressione Enter para continuar...");
          scan.nextLine();
          return;
        }
        break;

      case "4": // Série
        System.out.print("Número de temporadas: ");
        try {
          int temporadas = Integer.parseInt(scan.nextLine());
          System.out.print("Gênero: ");
          String generoSerie = scan.nextLine();
          recurso = new Serie(titulo, autor, descricao, temporadas, generoSerie);
        } catch (NumberFormatException e) {
          System.out.println("Número de temporadas inválido!");
          System.out.println("\nPressione Enter para continuar...");
          scan.nextLine();
          return;
        }
        break;

      case "5": // Jogo de Tabuleiro
        System.out.print("Jogadores mínimo: ");
        try {
          int jogadoresMin = Integer.parseInt(scan.nextLine());
          System.out.print("Jogadores máximo: ");
          int jogadoresMax = Integer.parseInt(scan.nextLine());
          System.out.print("Gênero: ");
          String generoJogo = scan.nextLine();
          recurso = new JogoDeTabuleiro(titulo, autor, descricao, jogadoresMin, jogadoresMax, generoJogo);
        } catch (NumberFormatException e) {
          System.out.println("Número de jogadores inválido!");
          System.out.println("\nPressione Enter para continuar...");
          scan.nextLine();
          return;
        }
        break;

      default:
        System.out.println("Tipo de recurso inválido!");
        System.out.println("\nPressione Enter para continuar...");
        scan.nextLine();
        return;
    }

    if (recurso != null) {
      grupo.adicionarRecurso(recurso);
      System.out.println("Recurso adicionado à biblioteca com sucesso!");

      String tituloNotificacao = "Novo recurso na biblioteca";
      String tipoRecurso = recurso.getClass().getSimpleName();
      String mensagemNotificacao = usuarioLogado.getNome() + " adicionou '" + recurso.getTitulo() +
          "' (" + tipoRecurso + ") à biblioteca do grupo " + grupo.getNome();

      for (Membro membro : grupo.getMembros().values()) {
        Usuario usuario = membro.getUsuario();
        if (usuario.getId() != usuarioLogado.getId()) {
          Notificacao notificacao = new Notificacao(tituloNotificacao, mensagemNotificacao, TipoNotificacao.RECURSO,
              grupo);
          usuario.adicionarNotificacao(notificacao);
        }
      }
    }

    System.out.println("\nPressione Enter para continuar...");
    scan.nextLine();
  }

  public static void excluirRecurso(Usuario usuarioLogado, Grupo grupo) {
    Cargo cargoUsuario = grupo.getMembros().get(usuarioLogado.getId()).getCargo();
    if (cargoUsuario != Cargo.ADMIN) {
      System.out.println("Apenas administradores podem excluir recursos da biblioteca!");
      System.out.println("\nPressione Enter para continuar...");
      scan.nextLine();
      return;
    }

    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("  EXCLUIR RECURSO DA BIBLIOTECA");
      System.out.println("=========================================");

      if (grupo.getRecursos().isEmpty()) {
        System.out.println("Nenhum recurso na biblioteca para excluir!");
        System.out.println("\n[0] Voltar");
        System.out.print("\nEscolha uma opção: ");
        String opcao = scan.nextLine();
        if (opcao.equals("0")) {
          return;
        }
        continue;
      }

      System.out.println("Recursos disponíveis:");
      for (int i = 0; i < grupo.getRecursos().size(); i++) {
        RecursoCompartilhado recurso = grupo.getRecursos().get(i);
        System.out.println("[" + (i + 1) + "] " + recurso.getTitulo() + " - " + recurso.getClass().getSimpleName());
      }

      System.out.println("\n[0] Voltar");
      System.out.print("\nDigite o número do recurso a excluir: ");
      String input = scan.nextLine();

      if (input.equals("0")) {
        return;
      }

      try {
        int indice = Integer.parseInt(input) - 1;
        if (indice >= 0 && indice < grupo.getRecursos().size()) {
          RecursoCompartilhado recurso = grupo.getRecursos().get(indice);

          System.out.print("Tem certeza que deseja excluir '" + recurso.getTitulo() + "'? (s/N): ");
          String confirmacao = scan.nextLine();
          if (!confirmacao.equalsIgnoreCase("s") && !confirmacao.equalsIgnoreCase("sim")) {
            System.out.println("Operação cancelada!");
            System.out.println("\nPressione Enter para continuar...");
            scan.nextLine();
            continue;
          }

          grupo.removerRecurso(recurso);
          System.out.println("Recurso excluído da biblioteca com sucesso!");
          String tituloNotificacao = "Recurso removido da biblioteca";
          String tipoRecurso = recurso.getClass().getSimpleName();
          String mensagemNotificacao = usuarioLogado.getNome() + " removeu '" + recurso.getTitulo() +
              "' (" + tipoRecurso + ") da biblioteca do grupo " + grupo.getNome();

          for (Membro membro : grupo.getMembros().values()) {
            Usuario usuario = membro.getUsuario();
            if (usuario.getId() != usuarioLogado.getId()) {
              Notificacao notificacao = new Notificacao(tituloNotificacao, mensagemNotificacao,
                  TipoNotificacao.RECURSO,
                  grupo);
              usuario.adicionarNotificacao(notificacao);
            }
          }

          System.out.println("\nPressione Enter para continuar...");
          scan.nextLine();
          return;
        }

        System.out.println("Recurso não encontrado!");
        System.out.println("Pressione Enter para continuar...");
        scan.nextLine();
      } catch (NumberFormatException e) {
        System.out.println("Número inválido!");
        System.out.println("Pressione Enter para continuar...");
        scan.nextLine();
      }
    }
  }
}
