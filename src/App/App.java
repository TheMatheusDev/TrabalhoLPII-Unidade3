package App;

import java.util.HashMap;
import Classes.Grupo;
import Classes.Usuario;
import Menu.Auth;

public class App {
  public static HashMap<Integer, Grupo> grupos = new HashMap<>();
  public static HashMap<String, Usuario> usuarios = new HashMap<>();

  public static void main(String[] args) {
    Demo.popularDados();
    Auth.Login(App.usuarios);
  }
}
