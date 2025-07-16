package App;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import Classes.Grupo;
import Classes.Usuario;
import Menu.Auth;
import Utils.Demo;

public class App {
  public static Map<Integer, Grupo> grupos = new HashMap<>();
  public static Map<String, Usuario> usuarios = new HashMap<>();
  public static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    Demo.popularDados();
    Auth.Login(App.usuarios);
  }
}
