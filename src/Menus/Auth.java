package Menus;

import App.App;
import Classes.Usuario;
import Utils.LimparTela;
import Exceptions.LoginException;
import java.util.Map;

public class Auth {
  private static Usuario validarLogin(Map<String, Usuario> usuarios, String email, String senha)
      throws LoginException {
    if (email == null || email.trim().isEmpty()) {
      throw new LoginException("Email não pode estar vazio!");
    }

    if (senha == null || senha.trim().isEmpty()) {
      throw new LoginException("Senha não pode estar vazia!");
    }

    Usuario usuario = usuarios.get(email);
    if (usuario == null || !usuario.getSenha().equals(senha)) {
      throw new LoginException("Email ou senha incorretos!");
    }

    return usuario;
  }

  public static Usuario Login(Map<String, Usuario> usuarios) {
    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("      BEM-VINDO A HOBBYCONNECT  ");
      System.out.println("=========================================");
      System.out.println("[1] Entrar (Login)");
      System.out.println("[2] Criar Conta (Registrar-se)");
      System.out.println("[0] Sair");
      System.out.print("\nEscolha uma opção: ");

      String escolha = App.scanner.nextLine();
      switch (escolha) {
        case "1":
          System.out.print("Digite seu email: ");
          String email = App.scanner.nextLine();
          System.out.print("Digite sua senha: ");
          String senha = App.scanner.nextLine();

          try {
            Usuario usuario = validarLogin(usuarios, email, senha);
            System.out.println("\nLogin realizado com sucesso!");
            System.out.println("Bem-vindo(a), " + usuario.getNome() + "!");
            System.out.println("\nPressione Enter para continuar...");
            App.scanner.nextLine();
            return usuario;
          } catch (LoginException e) {
            System.out.println("\nErro de login: " + e.getMessage());
            System.out.println("Pressione Enter para continuar...");
            App.scanner.nextLine();
          }
          break;

        case "2":
          menuCriarConta(usuarios);
          break;

        case "0":
          System.out.println("Saindo do sistema...");
          System.exit(0);
          break;

        default:
          System.out.println("Opção inválida! Tente novamente.");
          System.out.println("\nPressione Enter para continuar...");
          App.scanner.nextLine();
      }
    }
  }

  public static void menuCriarConta(Map<String, Usuario> usuarios) {
    LimparTela.limparTela();
    System.out.println("=========================================");
    System.out.println("  CRIAR CONTA - REDE SOCIAL DE HOBBIES  ");
    System.out.println("=========================================");

    System.out.print("Digite seu nome: ");
    String nome = App.scanner.nextLine();

    System.out.print("Digite seu email: ");
    String email = App.scanner.nextLine();

    if (usuarios.containsKey(email)) {
      System.out.println("Email já cadastrado. Tente novamente.");
      return;
    }

    System.out.print("Digite sua senha: ");
    String senha = App.scanner.nextLine();

    if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
      System.out.println("Todos os campos são obrigatórios. Tente novamente.");
      return;
    }

    Usuario novoUsuario = new Usuario(nome, email, senha);
    usuarios.put(email, novoUsuario);

    System.out.println("Conta criada com sucesso! Você já pode fazer login.");
    System.out.println("\nPressione Enter para continuar...");
    App.scanner.nextLine();
  }
}
