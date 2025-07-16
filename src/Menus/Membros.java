package Menus;

import App.App;
import Classes.Grupo;
import Classes.Membro;
import Classes.Usuario;
import Enums.Cargo;
import Utils.LimparTela;

public class Membros {

  public static void gerenciarMembros(Usuario usuarioLogado, Grupo grupo) {
    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("  GERENCIAR MEMBROS - " + grupo.getNome());
      System.out.println("=========================================");

      System.out.println("Lista de Membros:");
      for (var entrada : grupo.getMembros().entrySet()) {
        Membro membro = entrada.getValue();
        Usuario usuario = membro.getUsuario();
        Cargo cargo = membro.getCargo();
        System.out
            .println("[" + membro.getId() + "] " + usuario.getNome() + " (" + usuario.getEmail() + ") - " + cargo);
      }

      System.out.println("\n[1] Promover membro a admin");
      System.out.println("[2] Rebaixar admin a membro");
      System.out.println("[3] Remover membro");
      System.out.println("[0] Voltar");
      System.out.print("\nEscolha uma opção: ");

      String escolha = App.scanner.nextLine();
      switch (escolha) {
        case "1":
          promoverMembro(grupo);
          break;
        case "2":
          rebaixarAdmin(usuarioLogado, grupo);
          break;
        case "3":
          removerMembro(usuarioLogado, grupo);
          break;
        case "0":
          return;
        default:
          System.out.println("Opção inválida! Pressione Enter para continuar...");
          App.scanner.nextLine();
      }
    }
  }

  public static void promoverMembro(Grupo grupo) {
    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("  PROMOVER MEMBRO A ADMIN");
      System.out.println("=========================================");

      System.out.println("Membros que podem ser promovidos:");
      boolean temMembros = false;
      for (var entrada : grupo.getMembros().entrySet()) {
        if (entrada.getValue().getCargo() == Cargo.MEMBRO) {
          Membro membro = entrada.getValue();
          Usuario usuario = membro.getUsuario();
          System.out.println("[" + membro.getId() + "] " + usuario.getNome() + " (" + usuario.getEmail() + ")");
          temMembros = true;
        }
      }

      if (!temMembros) {
        System.out.println("Não há membros para promover.");
        System.out.println("\n[0] Voltar");
        System.out.print("\nEscolha uma opção: ");
        String opcao = App.scanner.nextLine();
        if (opcao.equals("0"))
          return;
        continue;
      }

      System.out.println("\n[0] Voltar");
      System.out.print("\nDigite o ID do membro para promover: ");
      String input = App.scanner.nextLine();

      if (input.equals("0")) {
        return;
      }

      try {
        int idMembro = Integer.parseInt(input);
        Membro membroEncontrado = null;

        for (var entrada : grupo.getMembros().entrySet()) {
          if (entrada.getValue().getId() == idMembro) {
            membroEncontrado = entrada.getValue();
            break;
          }
        }

        if (membroEncontrado == null) {
          System.out.println("Membro não encontrado!");
          System.out.println("Pressione Enter para continuar...");
          App.scanner.nextLine();
          continue;
        }

        if (membroEncontrado.getCargo() != Cargo.MEMBRO) {
          System.out.println("Este usuário já é administrador!");
          System.out.println("Pressione Enter para continuar...");
          App.scanner.nextLine();
          continue;
        }

        membroEncontrado.setCargo(Cargo.ADMIN);
        System.out.println("Membro promovido a administrador!");
      } catch (NumberFormatException e) {
        System.out.println("ID inválido!");
      }

      System.out.println("Pressione Enter para continuar...");
      App.scanner.nextLine();
    }
  }

  public static void rebaixarAdmin(Usuario usuarioLogado, Grupo grupo) {
    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("  REBAIXAR ADMIN A MEMBRO");
      System.out.println("=========================================");

      System.out.println("Administradores que podem ser rebaixados:");
      boolean temAdmins = false;
      for (var entrada : grupo.getMembros().entrySet()) {
        if (entrada.getValue().getCargo() == Cargo.ADMIN) {
          Membro membro = entrada.getValue();
          Usuario usuario = membro.getUsuario();

          if (usuario.getId() == usuarioLogado.getId()) {
            continue;
          }

          System.out.println("[" + membro.getId() + "] " + usuario.getNome() + " (" + usuario.getEmail() + ")");
          temAdmins = true;
        }
      }

      if (!temAdmins) {
        System.out.println("Não há administradores para rebaixar.");
        System.out.println("\n[0] Voltar");
        System.out.print("\nEscolha uma opção: ");
        String opcao = App.scanner.nextLine();
        if (opcao.equals("0"))
          return;
        continue;
      }

      System.out.println("\n[0] Voltar");
      System.out.print("\nDigite o ID do admin para rebaixar: ");
      String input = App.scanner.nextLine();

      if (input.equals("0"))
        return;

      try {
        int idMembro = Integer.parseInt(input);
        Membro membroEncontrado = null;

        for (var entrada : grupo.getMembros().entrySet()) {
          if (entrada.getValue().getId() == idMembro) {
            membroEncontrado = entrada.getValue();
            break;
          }
        }

        if (membroEncontrado == null) {
          System.out.println("Membro não encontrado!");
          System.out.println("Pressione Enter para continuar...");
          App.scanner.nextLine();
          continue;
        }

        if (membroEncontrado.getCargo() != Cargo.ADMIN) {
          System.out.println("Este usuário não é administrador!");
          System.out.println("Pressione Enter para continuar...");
          App.scanner.nextLine();
          continue;
        }
        membroEncontrado.setCargo(Cargo.MEMBRO);
        System.out.println("Administrador rebaixado a membro!");
      } catch (NumberFormatException e) {
        System.out.println("ID inválido!");
      }

      System.out.println("Pressione Enter para continuar...");
      App.scanner.nextLine();
    }
  }

  public static void removerMembro(Usuario usuarioLogado, Grupo grupo) {
    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("  REMOVER MEMBRO DO GRUPO");
      System.out.println("=========================================");

      System.out.println("Lista de Membros:");
      for (var entrada : grupo.getMembros().entrySet()) {
        Membro membro = entrada.getValue();
        Usuario usuario = membro.getUsuario();
        Cargo cargo = membro.getCargo();

        if (usuario.getId() == usuarioLogado.getId()) {
          continue;
        }
        System.out
            .println("[" + membro.getId() + "] " + usuario.getNome() + " (" + usuario.getEmail() + ") - " + cargo);
      }

      System.out.println("\n[0] Voltar");
      System.out.print("\nDigite o ID do membro para remover: ");
      String input = App.scanner.nextLine();

      if (input.equals("0"))
        return;

      try {
        int idMembro = Integer.parseInt(input);
        Membro membroEncontrado = null;

        for (var entrada : grupo.getMembros().entrySet()) {
          if (entrada.getValue().getId() == idMembro) {
            membroEncontrado = entrada.getValue();
            break;
          }
        }

        if (membroEncontrado == null) {
          System.out.println("Membro não encontrado!");
          System.out.println("Pressione Enter para continuar...");
          App.scanner.nextLine();
          continue;
        }

        if (membroEncontrado.getUsuario().getId() == usuarioLogado.getId()) {
          System.out.println("Você não pode remover a si mesmo do grupo!");
          System.out.println("Pressione Enter para continuar...");
          App.scanner.nextLine();
          continue;
        }

        Usuario usuario = membroEncontrado.getUsuario();
        boolean grupoFoiDeletado = grupo.removerMembro(usuario);
        if (grupoFoiDeletado) {
          System.out.println("Grupo foi deletado pois não há mais membros!");
          System.out.println("Pressione Enter para continuar...");
          App.scanner.nextLine();
          return;
        }
        System.out.println("Membro removido do grupo!");
      } catch (NumberFormatException e) {
        System.out.println("ID inválido!");
      }
      System.out.println("Pressione Enter para continuar...");
      App.scanner.nextLine();
    }
  }
}
