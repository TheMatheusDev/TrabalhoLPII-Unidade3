package Menu;

import App.App;
import Classes.Grupo;
import Classes.Membro;
import Classes.Usuario;
import Utils.LimparTela;

public class ConfiguracaoNotificacoes {

  public static void configurarNotificacoesGrupo(Usuario usuarioLogado, Grupo grupo) {
    Membro membro = grupo.getMembros().get(usuarioLogado.getId());

    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("  CONFIGURAÇÕES DE NOTIFICAÇÃO");
      System.out.println("  Grupo: " + grupo.getNome());
      System.out.println("=========================================");
      System.out.println("Configure suas preferências de notificação para este grupo:\n");
      System.out.println("Status atual das notificações:");
      membro.getConfiguracao().exibirConfiguracoes();

      System.out.println("\n[1] " + (membro.recebeNotificacoesComentarios() ? "Desabilitar" : "Habilitar")
          + " notificações de comentários");
      System.out.println(
          "[2] " + (membro.recebeNotificacoesEventos() ? "Desabilitar" : "Habilitar") + " notificações de eventos");
      System.out.println(
          "[3] " + (membro.recebeNotificacoesPostagens() ? "Desabilitar" : "Habilitar") + " notificações de postagens");
      System.out.println("[4] Desabilitar todas as notificações");
      System.out.println("[5] Habilitar todas as notificações");
      System.out.println("[0] Voltar ao menu do grupo");
      System.out.print("\nEscolha uma opção: ");

      String escolha = App.scanner.nextLine();
      switch (escolha) {
        case "1":
          membro.getConfiguracao().setReceberNotificacoesComentarios(!membro.recebeNotificacoesComentarios());
          String statusComentarios = membro.recebeNotificacoesComentarios() ? "habilitadas" : "desabilitadas";
          System.out.println("Notificações de comentários " + statusComentarios + "!");
          break;
        case "2":
          membro.getConfiguracao().setReceberNotificacoesEventos(!membro.recebeNotificacoesEventos());
          String statusEventos = membro.recebeNotificacoesEventos() ? "habilitadas" : "desabilitadas";
          System.out.println("Notificações de eventos " + statusEventos + "!");
          break;
        case "3":
          membro.getConfiguracao().setReceberNotificacoesPostagens(!membro.recebeNotificacoesPostagens());
          String statusPostagens = membro.recebeNotificacoesPostagens() ? "habilitadas" : "desabilitadas";
          System.out.println("Notificações de postagens " + statusPostagens + "!");
          break;
        case "4":
          membro.getConfiguracao().desabilitarTodasNotificacoes();
          System.out.println("Todas as notificações foram desabilitadas para este grupo!");
          break;
        case "5":
          membro.getConfiguracao().habilitarTodasNotificacoes();
          System.out.println("Todas as notificações foram habilitadas para este grupo!");
          break;
        case "0":
          return;
        default:
          System.out.println("Opção inválida!");
      }

      if (!escolha.equals("0")) {
        System.out.println("Pressione Enter para continuar...");
        App.scanner.nextLine();
      }
    }
  }
}
