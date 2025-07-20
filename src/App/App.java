package App;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import Classes.Grupo;
import Classes.Usuario;
import Menus.Auth;
import Menus.Principal;
import Utils.Demo;

public class App {
  public static Map<Integer, Grupo> grupos = new HashMap<>();
  public static Map<String, Usuario> usuarios = new HashMap<>();
  public static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    Demo.popularDados();

    while (true) {
      Usuario usuarioLogado = Auth.Login(App.usuarios);
      if (usuarioLogado != null) {
        Principal.menuPrincipal(usuarioLogado);
      }
    }
  }
}
