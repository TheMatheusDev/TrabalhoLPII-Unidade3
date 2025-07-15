package Menu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import App.App;
import Classes.Comentario;
import Classes.Grupo;
import Classes.Postagem;
import Classes.Usuario;
import Utils.LimparTela;

public class Postagens {

  public static void verPostagensGrupo(Grupo grupo) {
    verPostagensGrupo(null, grupo);
  }

  public static void verPostagensGrupo(Usuario usuarioLogado, Grupo grupo) {
    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("  POSTAGENS DO GRUPO: " + grupo.getNome());
      System.out.println("=========================================");

      if (grupo.getPostagens().isEmpty()) {
        System.out.println("Nenhuma postagem encontrada neste grupo.");
        System.out.println("\n[0] Voltar");
        System.out.print("\nEscolha uma opção: ");
        String opcao = App.scanner.nextLine();

        if (opcao.equals("0"))
          return;

        System.out.println("Opção inválida! Pressione Enter para continuar...");
        App.scanner.nextLine();
        continue;
      }

      for (int i = 0; i < grupo.getPostagens().size(); i++) {
        Postagem postagem = grupo.getPostagens().get(i);
        postagem.exibir();
        System.out.println();
      }

      System.out.println("\n[1] Comentar em uma postagem");
      System.out.println("[0] Voltar");
      System.out.print("\nEscolha uma opção: ");
      String opcao = App.scanner.nextLine();

      switch (opcao) {
        case "1":
          if (usuarioLogado != null) {
            comentarPostagem(usuarioLogado, grupo);
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

  public static void comentarPostagem(Usuario usuarioLogado, Grupo grupo) {
    LimparTela.limparTela();
    System.out.println("=========================================");
    System.out.println("  COMENTAR EM POSTAGEM");
    System.out.println("=========================================");

    if (grupo.getPostagens().isEmpty()) {
      System.out.println("Nenhuma postagem encontrada neste grupo.");
      System.out.println("\nPressione Enter para continuar...");
      App.scanner.nextLine();
      return;
    }

    System.out.println("Escolha uma postagem para comentar:");
    for (int i = 0; i < grupo.getPostagens().size(); i++) {
      Postagem postagem = grupo.getPostagens().get(i);
      System.out.println("[" + (i + 1) + "] " + postagem.getTitulo() + " - " + postagem.getAutor());
    }

    System.out.print("\nDigite o número da postagem (0 para cancelar): ");
    String escolha = App.scanner.nextLine();
    int numeroPostagem;
    try {
      numeroPostagem = Integer.parseInt(escolha);
      if (numeroPostagem == 0)
        return;

      if (numeroPostagem < 1 || numeroPostagem > grupo.getPostagens().size()) {
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

    Postagem postagemEscolhida = grupo.getPostagens().get(numeroPostagem - 1);

    System.out.println("\n==============================");
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

  public static void criarPostagem(Usuario usuarioLogado, Grupo grupo) {
    LimparTela.limparTela();
    System.out.println("=========================================");
    System.out.println("  CRIAR NOVA POSTAGEM");
    System.out.println("=========================================");

    System.out.print("Título da postagem: ");
    String titulo = App.scanner.nextLine();

    System.out.print("Texto da postagem: ");
    String texto = App.scanner.nextLine();

    if (titulo.isEmpty() || texto.isEmpty()) {
      System.out.println("Título e texto são obrigatórios!");
      System.out.println("\nPressione Enter para continuar...");
      App.scanner.nextLine();
      return;
    }

    String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    Postagem novaPostagem = new Postagem(usuarioLogado.getNome(), texto, data, titulo, grupo);
    grupo.adicionarPostagem(novaPostagem);

    System.out.println("Postagem criada com sucesso!");
    System.out.println("\nPressione Enter para continuar...");
    App.scanner.nextLine();
  }
}
