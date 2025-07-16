package Menus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import App.App;
import Classes.Comentario;
import Classes.Grupo;
import Classes.Postagem;
import Classes.Usuario;
import Utils.LimparTela;

public class Feed {
  public static void exibirFeedDeNoticias(Usuario usuarioLogado) {
    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("  FEED DE NOTÍCIAS - " + usuarioLogado.getNome());
      System.out.println("=========================================");

      if (usuarioLogado.getGrupos().isEmpty()) {
        System.out.println("Você não está em nenhum grupo.");
        System.out.println("Entre em grupos para ver postagens no seu feed!");
        System.out.println("\n[0] Voltar ao menu principal");
        System.out.print("\nEscolha uma opção: ");
        String opcao = App.scanner.nextLine();

        if (opcao.equals("0")) {
          return;
        }

        System.out.println("Opção inválida! Pressione Enter para continuar...");
        App.scanner.nextLine();
        continue;
      }

      List<Postagem> todasPostagens = new ArrayList<>();
      for (Grupo grupo : usuarioLogado.getGrupos().values()) {
        todasPostagens.addAll(grupo.getPostagens());
      }

      if (todasPostagens.isEmpty()) {
        System.out.println("Nenhuma postagem encontrada nos seus grupos.");
        System.out.println("Seja o primeiro a postar algo!");
        System.out.println("\n[0] Voltar ao menu principal");
        System.out.print("\nEscolha uma opção: ");
        String opcao = App.scanner.nextLine();

        if (opcao.equals("0")) {
          return;
        }

        System.out.println("Opção inválida! Pressione Enter para continuar...");
        App.scanner.nextLine();
        continue;
      }

      // ordenar posts por cronologia
      todasPostagens.sort((p1, p2) -> {
        return p2.getData().compareTo(p1.getData());
      });

      System.out.println("📰 SUAS POSTAGENS RECENTES (" + todasPostagens.size() + "):");
      System.out.println("=========================================");
      for (int i = 0; i < todasPostagens.size(); i++) {
        Postagem postagem = todasPostagens.get(i);
        postagem.exibir();
        System.out.println("   📍 Grupo: " + postagem.getGrupo().getNome());
        System.out.println("   " + "─".repeat(50));
      }

      System.out.println("\n[1] Comentar em uma postagem");
      System.out.println("[2] Filtrar por grupo específico");
      System.out.println("[0] Voltar ao menu principal");
      System.out.print("\nEscolha uma opção: ");

      String opcao = App.scanner.nextLine();
      switch (opcao) {
        case "1":
          comentarPostagemFeed(usuarioLogado, todasPostagens);
          break;
        case "2":
          filtrarPostagensPorGrupo(usuarioLogado);
          break;
        case "0":
          return;
        default:
          System.out.println("Opção inválida! Pressione Enter para continuar...");
          App.scanner.nextLine();
      }
    }
  }

  public static void comentarPostagemFeed(Usuario usuarioLogado, List<Postagem> todasPostagens) {
    LimparTela.limparTela();
    System.out.println("=========================================");
    System.out.println("  COMENTAR EM POSTAGEM - FEED");
    System.out.println("=========================================");

    System.out.println("Escolha uma postagem para comentar:");
    for (int i = 0; i < todasPostagens.size(); i++) {
      Postagem postagem = todasPostagens.get(i);
      System.out.println("[" + (i + 1) + "] " + postagem.getTitulo() + " - " + postagem.getAutor() +
          " (Grupo: " + postagem.getGrupo().getNome() + ")");
    }

    System.out.print("\nDigite o número da postagem (0 para cancelar): ");
    String escolha = App.scanner.nextLine();
    int numeroPostagem;
    try {
      numeroPostagem = Integer.parseInt(escolha);
      if (numeroPostagem == 0)
        return;

      if (numeroPostagem < 1 || numeroPostagem > todasPostagens.size()) {
        System.out.println("Número de postagem inválido!");
        System.out.println("\nPressione Enter para continuar...");
        App.scanner.nextLine();
        return;
      }
    } catch (NumberFormatException e) {
      System.out.println("Número inválido!");
      System.out.println("\nPressione Enter para continuar...");
      App.scanner.nextLine();
      return;
    }

    Postagem postagemEscolhida = todasPostagens.get(numeroPostagem - 1);
    System.out.println("==============================");
    postagemEscolhida.exibir();
    System.out.println("==============================\n");
    System.out.print("Digite seu comentário: ");
    String textoComentario = App.scanner.nextLine();

    if (textoComentario.trim().isEmpty()) {
      System.out.println("Comentário não pode estar vazio!");
      System.out.println("\nPressione Enter para continuar...");
      App.scanner.nextLine();
      return;
    }

    String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    Comentario novoComentario = new Comentario(usuarioLogado.getNome(), textoComentario, data,
        postagemEscolhida.getPostagemID());
    postagemEscolhida.adicionarComentario(novoComentario);

    System.out.println("\nComentário adicionado com sucesso!");
    System.out.println("\nPressione Enter para continuar...");
    App.scanner.nextLine();
  }

  public static void filtrarPostagensPorGrupo(Usuario usuarioLogado) {
    LimparTela.limparTela();
    System.out.println("=========================================");
    System.out.println("  FILTRAR POSTAGENS POR GRUPO");
    System.out.println("=========================================");

    System.out.println("Escolha um grupo para ver apenas suas postagens:");
    List<Grupo> grupos = new ArrayList<>(usuarioLogado.getGrupos().values());

    for (int i = 0; i < grupos.size(); i++) {
      Grupo grupo = grupos.get(i);
      System.out.println("[" + (i + 1) + "] " + grupo.getNome() + " (" + grupo.getPostagens().size() + " postagens)");
    }

    System.out.print("\nDigite o número do grupo (0 para cancelar): ");
    String escolha = App.scanner.nextLine();
    int numeroGrupo;
    try {
      numeroGrupo = Integer.parseInt(escolha);
      if (numeroGrupo == 0)
        return;

      if (numeroGrupo < 1 || numeroGrupo > grupos.size()) {
        System.out.println("Número de grupo inválido!");
        System.out.println("\nPressione Enter para continuar...");
        App.scanner.nextLine();
        return;
      }
    } catch (NumberFormatException e) {
      System.out.println("Número inválido!");
      System.out.println("\nPressione Enter para continuar...");
      App.scanner.nextLine();
      return;
    }

    Grupo grupoEscolhido = grupos.get(numeroGrupo - 1);
    Postagens.verPostagensGrupo(usuarioLogado, grupoEscolhido);
  }
}
