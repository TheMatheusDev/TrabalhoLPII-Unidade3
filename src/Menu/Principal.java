package Menu;

import App.App;
import Classes.Usuario;
import Utils.LimparTela;

public class Principal {

  public static void menuPrincipal(Usuario usuarioLogado) {
    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("  MENU PRINCIPAL - Olá, " + usuarioLogado.getNome() + "!");
      System.out.println("=========================================");

      int notificacoesNaoLidas = usuarioLogado.getNotificacaoNaoLidas();
      String indicadorNotificacoes = "";
      if (notificacoesNaoLidas > 0) {
        indicadorNotificacoes = " (" + notificacoesNaoLidas + " novas)";
      }

      System.out.println("[1] Meu Feed de Notícias (Timeline Global)");
      System.out.println("[2] Meus Grupos");
      System.out.println("[3] Encontrar Grupos");
      System.out.println("[4] Notificações" + indicadorNotificacoes);
      System.out.println("[0] Sair da Conta (Logout)");
      System.out.print("\nEscolha uma opção: ");

      String escolha = App.scanner.nextLine();
      switch (escolha) {
        case "1":
          Feed.exibirFeedDeNoticias(usuarioLogado);
          break;
        case "2":
          Grupos.menuMeusGrupos(usuarioLogado);
          break;
        case "3":
          Grupos.menuEncontrarGrupos(usuarioLogado);
          break;
        case "4":
          Notificacoes.menuNotificacoes(usuarioLogado);
          break;
        case "0":
          System.out.println("Saindo da sua conta...");
          return;
        default:
          System.out.println("Opção inválida! Tente novamente.");
          break;
      }
    }
  }
}
