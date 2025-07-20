package Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import App.App;
import Classes.Grupo;
import Classes.Usuario;
import Classes.Postagem;
import Classes.Comentario;
import Enums.Cargo;
import Exceptions.UsuarioJaMembroException;

public class Demo {
  public static void popularDados() {
    Usuario user1 = new Usuario("tt", "tt", "tt");
    Usuario alice = new Usuario("Alice Silva", "alice@email.com", "123");
    Usuario bob = new Usuario("Bob Santos", "bob@email.com", "456");
    Usuario carol = new Usuario("Carol Lima", "carol@email.com", "789");
    Usuario david = new Usuario("David Oliveira", "david@email.com", "101");

    App.usuarios.put(user1.getEmail(), user1);
    App.usuarios.put(alice.getEmail(), alice);
    App.usuarios.put(bob.getEmail(), bob);
    App.usuarios.put(carol.getEmail(), carol);
    App.usuarios.put(david.getEmail(), david);

    Grupo grupoLivros = new Grupo("Clube do Livro", "Literatura",
        "Grupo para discussão de livros e literatura");
    Grupo grupoJogos = new Grupo("Gamers Unidos", "Jogos",
        "Comunidade de entusiastas de jogos de tabuleiro e videogames");
    Grupo grupoFilmes = new Grupo("Cinéfilos", "Cinema",
        "Discussões sobre filmes, séries e cinema em geral");

    App.grupos.put(grupoLivros.getId(), grupoLivros);
    App.grupos.put(grupoJogos.getId(), grupoJogos);
    App.grupos.put(grupoFilmes.getId(), grupoFilmes);

    try {
      grupoLivros.adicionarMembro(alice, Cargo.ADMIN);
      grupoLivros.adicionarMembro(bob, Cargo.MEMBRO);
      grupoLivros.adicionarMembro(carol, Cargo.MEMBRO);

      grupoJogos.adicionarMembro(bob, Cargo.ADMIN);
      grupoJogos.adicionarMembro(david, Cargo.MEMBRO);
      grupoJogos.adicionarMembro(alice, Cargo.MEMBRO);

      grupoFilmes.adicionarMembro(carol, Cargo.ADMIN);
      grupoFilmes.adicionarMembro(david, Cargo.MEMBRO);
      grupoFilmes.adicionarMembro(user1, Cargo.MEMBRO);

      String dataAtual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

      Postagem post1Livros = new Postagem(alice.getNome(),
          "Acabei de terminar '1984' do George Orwell. Que livro incrível! A forma como ele retrata a sociedade totalitária é assustadoramente atual. Alguém mais leu?",
          dataAtual, "Discussão: 1984 - George Orwell", grupoLivros);
      grupoLivros.adicionarPostagem(post1Livros);

      Postagem post2Livros = new Postagem(bob.getNome(),
          "Estou procurando recomendações de ficção científica. Já li Asimov e Philip K. Dick. O que vocês sugerem?",
          dataAtual, "Pedindo recomendações de Ficção Científica", grupoLivros);
      grupoLivros.adicionarPostagem(post2Livros);

      Postagem post3Livros = new Postagem(carol.getNome(),
          "Alguém já participou de um clube de leitura presencial? Estou pensando em organizar encontros mensais aqui em BH.",
          dataAtual, "Clube de Leitura Presencial - BH", grupoLivros);
      grupoLivros.adicionarPostagem(post3Livros);

      Postagem post1Jogos = new Postagem(bob.getNome(),
          "Comprei Wingspan ontem e já joguei 3 partidas! Que jogo fantástico. A arte é linda e a mecânica é super fluida.",
          dataAtual, "Review: Wingspan - Jogo de Tabuleiro", grupoJogos);
      grupoJogos.adicionarPostagem(post1Jogos);

      Postagem post2Jogos = new Postagem(david.getNome(),
          "Galera, alguém quer formar um grupo para jogar RPG online? Estou pensando em mestrar uma campanha de D&D 5e.",
          dataAtual, "Procurando grupo para RPG D&D 5e", grupoJogos);
      grupoJogos.adicionarPostagem(post2Jogos);

      Postagem post1Filmes = new Postagem(carol.getNome(),
          "Assistiram o novo filme do Villeneuve? Dune Parte 2 superou todas as minhas expectativas! A cinematografia é de outro mundo.",
          dataAtual, "Dune Parte 2 - Discussão sem spoilers", grupoFilmes);
      grupoFilmes.adicionarPostagem(post1Filmes);

      Postagem post2Filmes = new Postagem(david.getNome(),
          "Maratona Kubrick este fim de semana! Comecei com 2001 e vou até Laranja Mecânica. Alguém quer se juntar virtualmente?",
          dataAtual, "Maratona Stanley Kubrick", grupoFilmes);
      grupoFilmes.adicionarPostagem(post2Filmes);

      Comentario comentario1 = new Comentario(bob.getNome(),
          "Também li 1984 recentemente! Concordo totalmente, é impressionante como permanece atual.",
          dataAtual);
      post1Livros.adicionarComentario(comentario1);

      Comentario comentario1b = new Comentario(carol.getNome(),
          "A parte sobre a manipulação da linguagem me deixou arrepiada. 'Novilíngua' é genial e aterrorizante.",
          dataAtual);
      post1Livros.adicionarComentario(comentario1b);

      Comentario comentario1c = new Comentario(david.getNome(),
          "Orwell estava décadas à frente do seu tempo. Big Brother virou realidade em muitos aspectos.",
          dataAtual);
      post1Livros.adicionarComentario(comentario1c);

      Comentario comentario2 = new Comentario(carol.getNome(),
          "Recomendo muito 'Fundação' do Asimov se ainda não leu. E 'Neuromancer' do William Gibson é obrigatório!",
          dataAtual);
      post2Livros.adicionarComentario(comentario2);

      Comentario comentario3 = new Comentario(alice.getNome(),
          "Adorei a ideia do clube presencial! Se conseguir organizar, eu participo.",
          dataAtual);
      post3Livros.adicionarComentario(comentario3);

      Comentario comentario4 = new Comentario(alice.getNome(),
          "Wingspan está na minha wishlist há meses! Agora vou comprar depois dessa review.",
          dataAtual);
      post1Jogos.adicionarComentario(comentario4);

      Comentario comentario5 = new Comentario(user1.getNome(),
          "Dune Parte 2 é espetacular mesmo! Villeneuve conseguiu adaptar o livro perfeitamente.",
          dataAtual);
      post1Filmes.adicionarComentario(comentario5);

      Comentario comentario5b = new Comentario(david.getNome(),
          "A trilha sonora do Hans Zimmer também está incrível! Complementa perfeitamente as cenas épicas.",
          dataAtual);
      post1Filmes.adicionarComentario(comentario5b);

    } catch (UsuarioJaMembroException e) {
      System.err.println("Erro ao adicionar membro: " + e.getMessage());
    }
  }
}
