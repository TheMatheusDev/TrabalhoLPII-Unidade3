package Menu;

import java.util.Scanner;
import App.App;
import Classes.Grupo;
import Classes.Usuario;
import Enums.Cargo;
import Exceptions.UsuarioJaMembroException;
import Utils.LimparTela;

public class Grupos {
  private static Scanner scan = new Scanner(System.in);

  public static void menuMeusGrupos(Usuario usuarioLogado) {
    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("  MEUS GRUPOS - REDE SOCIAL DE HOBBIES  ");
      System.out.println("=========================================");
      System.out.println("Aqui você pode gerenciar seus grupos de hobbies.\n");

      if (usuarioLogado.getGrupos().isEmpty()) {
        System.out.println("Você não está em nenhum grupo.");
      } else {
        System.out.println("Lista de Grupos:");
        for (Grupo grupo : usuarioLogado.getGrupos().values()) {
          String cargo = grupo.getMembros().get(usuarioLogado.getId()).getCargo().toString();
          System.out.println("[" + grupo.getId() + "] - " + grupo.getNome() + " (Cargo: " + cargo + ")");
        }
      }

      System.out.println("\n[1] Acessar um grupo");
      System.out.println("[2] Criar novo grupo");
      System.out.println("[3] Sair de um grupo");
      System.out.println("[0] Voltar ao menu principal");
      System.out.print("\nEscolha uma opção: ");

      String escolha = scan.nextLine();

      switch (escolha) {
        case "1":
          acessarGrupo(usuarioLogado);
          break;
        case "2":
          criarGrupo(usuarioLogado);
          break;
        case "3":
          sairDoGrupo(usuarioLogado);
          break;
        case "0":
          return;
        default:
          System.out.println("Opção inválida! Pressione Enter para continuar...");
          scan.nextLine();
      }
    }
  }

  public static void menuEncontrarGrupos(Usuario usuarioLogado) {
    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("  ENCONTRAR GRUPOS - REDE SOCIAL DE HOBBIES  ");
      System.out.println("=========================================");
      System.out.println("Aqui você pode encontrar grupos de hobbies.\n");

      if (App.grupos.isEmpty()) {
        System.out.println("Nenhum grupo encontrado.");
        System.out.println("\n[0] Voltar ao menu principal");
        System.out.print("\nEscolha uma opção: ");
        String opcao = scan.nextLine();
        if (opcao.equals("0"))
          return;
        continue;
      }

      System.out.println("Lista de Grupos:");
      for (Grupo grupo : App.grupos.values()) {
        System.out.println("[" + grupo.getId() + "] - " + grupo.getNome() + " (Tema: " + grupo.getTema() + ")");
      }

      System.out.println("==========================================");
      System.out.println("[0] Voltar ao menu principal");
      System.out.print("\nDigite o ID do grupo que deseja visualizar: ");
      String grupoEscolhido = scan.nextLine();

      if (grupoEscolhido.equals("0"))
        return;

      int idGrupoEscolhido;
      try {
        idGrupoEscolhido = Integer.parseInt(grupoEscolhido);
        if (!App.grupos.containsKey(idGrupoEscolhido)) {
          System.out.println("\nGrupo não encontrado!");
          System.out.println("Pressione Enter para continuar...");
          scan.nextLine();
          continue;
        }
      } catch (NumberFormatException e) {
        System.out.println("\nID inválido!");
        System.out.println("Pressione Enter para continuar...");
        scan.nextLine();
        continue;
      }

      Grupo grupo = App.grupos.get(idGrupoEscolhido);
      visualizarDetalhesGrupo(usuarioLogado, grupo);
    }
  }

  public static void visualizarDetalhesGrupo(Usuario usuarioLogado, Grupo grupo) {
    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("  DETALHES DO GRUPO");
      System.out.println("=========================================");

      Grupo.visualizarGrupo(grupo);

      boolean jaEMembro = grupo.getMembros().containsKey(usuarioLogado.getId());

      if (jaEMembro) {
        menuGrupo(usuarioLogado, grupo);
        return;
      }

      System.out.println("\n[1] Entrar no grupo");
      System.out.println("[0] Voltar");

      System.out.print("\nEscolha uma opção: ");
      String opcao = scan.nextLine();

      switch (opcao) {
        case "1":
          try {
            grupo.adicionarMembro(usuarioLogado, Cargo.MEMBRO);
            System.out.println("\nVocê entrou no grupo: " + grupo.getNome());
            System.out.println("Pressione Enter para continuar...");
            scan.nextLine();
          } catch (UsuarioJaMembroException e) {
            System.out.println("\nErro: " + e.getMessage());
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

  public static void menuGrupo(Usuario usuarioLogado, Grupo grupo) {
    Cargo cargoUsuario = grupo.getMembros().get(usuarioLogado.getId()).getCargo();

    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("  GRUPO: " + grupo.getNome());
      System.out.println("  Seu cargo: " + cargoUsuario);
      System.out.println("=========================================");

      System.out.println("[1] Ver postagens do grupo");
      System.out.println("[2] Criar nova postagem");
      System.out.println("[3] Ver eventos do grupo");
      System.out.println("[4] Biblioteca de recursos");
      System.out.println("[5] Configurar notificações");

      if (cargoUsuario == Cargo.ADMIN) {
        System.out.println("[6] Criar evento (Admin)");
        System.out.println("[7] Gerenciar membros (Admin)");
        System.out.println("[8] Editar grupo (Admin)");
      }

      System.out.println("[0] Voltar");
      System.out.print("\nEscolha uma opção: ");

      String escolha = scan.nextLine();

      switch (escolha) {
        case "1":
          Postagens.verPostagensGrupo(usuarioLogado, grupo);
          break;
        case "2":
          Postagens.criarPostagem(usuarioLogado, grupo);
          break;
        case "3":
          Eventos.verEventosGrupo(usuarioLogado, grupo);
          break;
        case "4":
          Recursos.gerenciarBiblioteca(usuarioLogado, grupo);
          break;
        case "5":
          ConfiguracaoNotificacoes.configurarNotificacoesGrupo(usuarioLogado, grupo);
          break;
        case "6":
          if (cargoUsuario == Cargo.ADMIN) {
            Eventos.criarEvento(usuarioLogado, grupo);
          } else {
            System.out.println("Opção inválida! Pressione Enter para continuar...");
            scan.nextLine();
          }
          break;
        case "7":
          if (cargoUsuario == Cargo.ADMIN) {
            Membros.gerenciarMembros(usuarioLogado, grupo);
          } else {
            System.out.println("Opção inválida! Pressione Enter para continuar...");
            scan.nextLine();
          }
          break;
        case "8":
          if (cargoUsuario == Cargo.ADMIN) {
            editarGrupo(grupo);
          } else {
            System.out.println("Opção inválida! Pressione Enter para continuar...");
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

  public static void criarGrupo(Usuario usuarioLogado) {
    LimparTela.limparTela();
    System.out.println("=========================================");
    System.out.println("  CRIAR NOVO GRUPO  ");
    System.out.println("=========================================");

    System.out.print("Nome do grupo: ");
    String nome = scan.nextLine();

    System.out.print("Tema do grupo: ");
    String tema = scan.nextLine();

    System.out.print("Descrição do grupo: ");
    String descricao = scan.nextLine();

    if (nome.isEmpty() || tema.isEmpty() || descricao.isEmpty()) {
      System.out.println("Todos os campos são obrigatórios!");
      System.out.println("\nPressione Enter para continuar...");
      scan.nextLine();
      return;
    }

    Grupo novoGrupo = new Grupo(nome, tema, descricao);
    App.grupos.put(novoGrupo.getId(), novoGrupo);

    try {
      novoGrupo.adicionarMembro(usuarioLogado, Cargo.ADMIN);
      System.out.println("Grupo '" + nome + "' criado com sucesso!");
      System.out.println("Você é o administrador deste grupo.");
    } catch (UsuarioJaMembroException e) {
      System.out.println("Erro inesperado: " + e.getMessage());
    }

    System.out.println("\nPressione Enter para continuar...");
    scan.nextLine();
  }

  public static void sairDoGrupo(Usuario usuarioLogado) {
    LimparTela.limparTela();
    System.out.println("=========================================");
    System.out.println("  SAIR DE UM GRUPO  ");
    System.out.println("=========================================");

    if (usuarioLogado.getGrupos().isEmpty()) {
      System.out.println("Você não está em nenhum grupo.");
      System.out.println("\nPressione Enter para continuar...");
      scan.nextLine();
      return;
    }

    System.out.println("Lista de Grupos:");
    for (Grupo grupo : usuarioLogado.getGrupos().values()) {
      String cargo = grupo.getMembros().get(usuarioLogado.getId()).getCargo().toString();
      System.out.println("[" + grupo.getId() + "] - " + grupo.getNome() + " (Cargo: " + cargo + ")");
    }

    System.out.print("\nDigite o ID do grupo que deseja sair (0 para cancelar): ");
    String grupoEscolhido = scan.nextLine();

    int idGrupoEscolhido;
    try {
      idGrupoEscolhido = Integer.parseInt(grupoEscolhido);
      if (idGrupoEscolhido == 0)
        return;
    } catch (NumberFormatException e) {
      System.out.println("ID inválido!");
      System.out.println("\nPressione Enter para continuar...");
      scan.nextLine();
      return;
    }

    if (!usuarioLogado.getGrupos().containsKey(idGrupoEscolhido)) {
      System.out.println("Grupo não encontrado.");
      System.out.println("\nPressione Enter para continuar...");
      scan.nextLine();
      return;
    }

    Grupo grupo = usuarioLogado.getGrupos().get(idGrupoEscolhido);
    String nomeGrupo = grupo.getNome();
    boolean grupoFoiDeletado = grupo.removerMembro(usuarioLogado);

    if (grupoFoiDeletado) {
      System.out.println("Você era o último membro. O grupo '" + nomeGrupo + "' foi deletado.");
    } else {
      System.out.println("Você saiu do grupo: " + nomeGrupo);
    }

    System.out.println("\nPressione Enter para continuar...");
    scan.nextLine();
  }

  public static void acessarGrupo(Usuario usuarioLogado) {
    LimparTela.limparTela();
    System.out.println("=========================================");
    System.out.println("  ACESSAR GRUPO  ");
    System.out.println("=========================================");

    if (usuarioLogado.getGrupos().isEmpty()) {
      System.out.println("Você não está em nenhum grupo.");
      System.out.println("\nPressione Enter para continuar...");
      scan.nextLine();
      return;
    }

    System.out.println("Lista de Grupos:");
    for (Grupo grupo : usuarioLogado.getGrupos().values()) {
      String cargo = grupo.getMembros().get(usuarioLogado.getId()).getCargo().toString();
      System.out.println("[" + grupo.getId() + "] - " + grupo.getNome() + " (Cargo: " + cargo + ")");
    }

    System.out.print("\nDigite o ID do grupo que deseja acessar (0 para cancelar): ");
    String grupoEscolhido = scan.nextLine();

    int idGrupoEscolhido;
    try {
      idGrupoEscolhido = Integer.parseInt(grupoEscolhido);
      if (idGrupoEscolhido == 0)
        return;
    } catch (NumberFormatException e) {
      System.out.println("ID inválido!");
      System.out.println("\nPressione Enter para continuar...");
      scan.nextLine();
      return;
    }

    if (!usuarioLogado.getGrupos().containsKey(idGrupoEscolhido)) {
      System.out.println("Grupo não encontrado.");
      System.out.println("\nPressione Enter para continuar...");
      scan.nextLine();
      return;
    }

    Grupo grupo = usuarioLogado.getGrupos().get(idGrupoEscolhido);
    menuGrupo(usuarioLogado, grupo);
  }

  public static void editarGrupo(Grupo grupo) {
    LimparTela.limparTela();
    System.out.println("=========================================");
    System.out.println("  EDITAR GRUPO");
    System.out.println("=========================================");

    System.out.println("Dados atuais:");
    System.out.println("Nome: " + grupo.getNome());
    System.out.println("Tema: " + grupo.getTema());
    System.out.println("Descrição: " + grupo.getDescricao());

    System.out.print("\nNovo nome (Enter para manter atual): ");
    String novoNome = scan.nextLine();
    if (!novoNome.isEmpty()) {
      grupo.setNome(novoNome);
    }

    System.out.print("Novo tema (Enter para manter atual): ");
    String novoTema = scan.nextLine();
    if (!novoTema.isEmpty()) {
      grupo.setTema(novoTema);
    }

    System.out.print("Nova descrição (Enter para manter atual): ");
    String novaDescricao = scan.nextLine();
    if (!novaDescricao.isEmpty()) {
      grupo.setDescricao(novaDescricao);
    }

    System.out.println("Grupo atualizado com sucesso!");
    System.out.println("\nPressione Enter para continuar...");
    scan.nextLine();
  }
}
