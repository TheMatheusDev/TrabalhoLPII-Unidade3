package Menu;

import java.util.HashMap;
import java.util.Scanner;
import Classes.Usuario;
import Utils.LimparTela;
import Exceptions.LoginException;

public class Auth {
  private static Scanner scan = new Scanner(System.in);

  private static Usuario validarLogin(HashMap<String, Usuario> usuarios, String email, String senha)
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

  public static void Login(HashMap<String, Usuario> usuarios) {
    while (true) {
      LimparTela.limparTela();
      System.out.println("=========================================");
      System.out.println("      BEM-VINDO A HOBBYCONNECT  ");
      System.out.println("=========================================");
      System.out.println("[1] Entrar (Login)");
      System.out.println("[2] Criar Conta (Registrar-se)");
      System.out.println("[0] Sair");
      System.out.print("\nEscolha uma opção: ");
      String escolha = scan.nextLine();

      switch (escolha) {
        case "1":
          System.out.print("Digite seu email: ");
          String email = scan.nextLine();
          System.out.print("Digite sua senha: ");
          String senha = scan.nextLine();

          try {
            Usuario usuario = validarLogin(usuarios, email, senha);
            System.out.println("\nLogin realizado com sucesso!");
            System.out.println("Bem-vindo(a), " + usuario.getNome() + "!");
            System.out.println("\nPressione Enter para continuar...");
            scan.nextLine();
            Principal.menuPrincipal(usuario);
          } catch (LoginException e) {
            System.out.println("\nErro de login: " + e.getMessage());
            System.out.println("Pressione Enter para continuar...");
            scan.nextLine();
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
          scan.nextLine();
      }
    }
  }

  public static void menuCriarConta(HashMap<String, Usuario> usuarios) {
    LimparTela.limparTela();
    System.out.println("=========================================");
    System.out.println("  CRIAR CONTA - REDE SOCIAL DE HOBBIES  ");
    System.out.println("=========================================");

    System.out.print("Digite seu nome: ");
    String nome = scan.nextLine();

    System.out.print("Digite seu email: ");
    String email = scan.nextLine();

    if (usuarios.containsKey(email)) {
      System.out.println("Email já cadastrado. Tente novamente.");
      return;
    }

    System.out.print("Digite sua senha: ");
    String senha = scan.nextLine();

    System.out.print("Digite sua cidade: ");
    String cidade = scan.nextLine();

    if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || cidade.isEmpty()) {
      System.out.println("Todos os campos são obrigatórios. Tente novamente.");
      return;
    }

    Usuario novoUsuario = new Usuario(nome, email, senha, cidade);
    usuarios.put(email, novoUsuario);

    System.out.println("Conta criada com sucesso! Você já pode fazer login.");
    System.out.println("\nPressione Enter para continuar...");
    scan.nextLine();
  }
}
