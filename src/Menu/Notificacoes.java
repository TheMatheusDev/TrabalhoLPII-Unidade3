package Menu;

import java.time.format.DateTimeFormatter;
import App.App;
import Classes.Grupo;
import Classes.Notificacao;
import Classes.Usuario;
import Utils.LimparTela;

public class Notificacoes {

  public static void menuNotificacoes(Usuario usuarioLogado) {
    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("  NOTIFICAÇÕES - " + usuarioLogado.getNome());
      System.out.println("=========================================");

      if (usuarioLogado.getNotificacoes().isEmpty()) {
        System.out.println("Você não tem notificações.");
        System.out.println("\n[0] Voltar ao menu principal");
        System.out.print("\nEscolha uma opção: ");
        String opcao = App.scanner.nextLine();

        if (opcao.equals("0")) {
          return;
        }
        continue;
      }

      int notificacoesNaoLidas = usuarioLogado.getNotificacaoNaoLidas();
      if (notificacoesNaoLidas > 0) {
        System.out.println("Você tem " + notificacoesNaoLidas + " notificações não lidas.\n");
      } else {
        System.out.println("Todas as notificações foram lidas.\n");
      }

      System.out.println("==== SUAS NOTIFICAÇÕES ====");
      for (int i = 0; i < usuarioLogado.getNotificacoes().size(); i++) {
        Notificacao notif = usuarioLogado.getNotificacoes().get(i);
        System.out.print("[" + (i + 1) + "] ");

        String statusLeitura = notif.isLida() ? " " : " [NOVA] ";
        String icone = notif.getTipoNotificacao() != null ? notif.getTipoNotificacao().getIcone() : "🔔";

        System.out.println(icone + statusLeitura + notif.getTitulo());
        System.out.println("    " + notif.getMensagem());
        System.out.println("    " + notif.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        System.out.println("    " + "-".repeat(50));
      }

      System.out.println("\n[1] Marcar todas como lidas");
      System.out.println("[2] Abrir grupo de uma notificação");
      System.out.println("[0] Voltar ao menu principal");
      System.out.print("\nEscolha uma opção: ");

      String escolha = App.scanner.nextLine();
      switch (escolha) {
        case "1":
          usuarioLogado.marcarTodasNotificacoesComoLidas();
          System.out.println("Todas as notificações foram marcadas como lidas!");
          System.out.println("Pressione Enter para continuar...");
          App.scanner.nextLine();
          break;

        case "2":
          abrirGrupoNotificacao(usuarioLogado);
          break;

        case "0":
          return;

        default:
          System.out.println("Opção inválida! Pressione Enter para continuar...");
          App.scanner.nextLine();
      }
    }
  }

  public static void abrirGrupoNotificacao(Usuario usuarioLogado) {
    if (usuarioLogado.getNotificacoes().isEmpty())
      return;

    System.out.print("Digite o número da notificação (1-" + usuarioLogado.getNotificacoes().size() + "): ");
    String input = App.scanner.nextLine();

    int indice;
    try {
      indice = Integer.parseInt(input) - 1;
    } catch (NumberFormatException e) {
      System.out.println("Entrada inválida!");
      System.out.println("Pressione Enter para continuar...");
      App.scanner.nextLine();
      return;
    }

    if (indice < 0 || indice >= usuarioLogado.getNotificacoes().size()) {
      System.out.println("Número inválido!");
      System.out.println("Pressione Enter para continuar...");
      App.scanner.nextLine();
      return;
    }

    Notificacao notif = usuarioLogado.getNotificacoes().get(indice);
    Grupo grupo = notif.getGrupo();
    if (!grupo.getMembros().containsKey(usuarioLogado.getId())) {
      System.out.println("Você não é mais membro deste grupo!");
      System.out.println("Pressione Enter para continuar...");
      App.scanner.nextLine();
      return;
    }
    notif.marcarComoLida();
    Grupos.menuGrupo(usuarioLogado, grupo);
  }
}
