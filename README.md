# 🎯 HobbyConnect - Rede Social para Grupos de Hobby

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge)
![OOP](https://img.shields.io/badge/OOP-Oriented-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Development-yellow?style=for-the-badge)

> Uma plataforma social focada na organização de comunidades e eventos para grupos de hobby, sem aspectos comerciais.

## 📋 Sobre o Projeto

O **HobbyConnect** é um sistema de rede social desenvolvido em Java que permite a criação e gerenciamento de grupos de hobby, organização de eventos e compartilhamento de recursos entre membros. O projeto demonstra conceitos avançados de Programação Orientada a Objetos.

### 🎯 Exemplos de Uso

- 📚 Clubes do Livro
- 🥾 Grupos de Trilhas
- 🎲 Comunidades de Jogos de Tabuleiro
- 🎬 Círculos de Cinema

## ⚡ Funcionalidades

- ✅ **Gerenciamento de Usuários**: Cadastro e autenticação
- ✅ **Criação de Grupos**: Organização por temas e interesses
- ✅ **Sistema de Membros**: Diferentes níveis de permissão (Admin, Moderador, Membro)
- ✅ **Organização de Eventos**: Criação e participação em eventos
- ✅ **Feed Social**: Postagens e comentários
- ✅ **Biblioteca Compartilhada**: Recursos como livros, trilhas, filmes
- ✅ **Sistema de Notificações**: Alertas em tempo real
- ✅ **Tratamento de Exceções**: Validações robustas

## 🏗️ Arquitetura do Sistema

### 📦 Estrutura de Pastas

```
src/
├── App/              # Ponto de entrada da aplicação
├── Classes/          # Classes principais do domínio
├── Enums/           # Enumerações (Cargo, TipoNotificacao)
├── Exceptions/      # Exceções customizadas
├── Interfaces/      # Interfaces
├── Menu/            # Sistema de menus e navegação
├── Recursos/        # Recursos compartilháveis
└── Utils/           # Utilitários gerais
```

### 🎯 Classes Principais

| Classe                 | Descrição                     | Atributos Principais                                                                                 |
| ---------------------- | ----------------------------- | ---------------------------------------------------------------------------------------------------- |
| `Usuario`              | Representação dos usuários    | id, nome, email, senha, notificacoes                                                                 |
| `Grupo`                | Comunidades de hobby          | id, nome, tema, descricao, membros, postagens                                                        |
| `Membro`               | Relação usuário-grupo         | id, usuario, cargo, dataDeIngresso, configuracao                                                     |
| `Evento`               | Eventos organizados           | id, titulo, data, local, capacidadeMaxima, grupo                                                     |
| `Conteudo`             | Classe abstrata (herança)     | autor, texto, data                                                                                   |
| `Postagem`             | Conteúdo do feed              | titulo, grupo, comentarios (+ herdados)                                                              |
| `Comentario`           | Interações nas postagens      | comentarioID, postagemID (+ herdados)                                                                |
| `Local`                | Locais para eventos           | nome, endereco, cidade, capacidade                                                                   |
| `Notificacao`          | Sistema de alertas            | id, titulo, mensagem, dataHora, tipoNotificacao, lida                                                |
| `ConfiguracaoMembro`   | Preferências de notificação   | id, membro, receberNotificacoesComentarios, receberNotificacoesEventos, receberNotificacoesPostagens |
| `RecursoCompartilhado` | Classe abstrata para recursos | titulo, autor, descricao                                                                             |
| `Livro`                | Recurso: livros               | genero (+ atributos herdados)                                                                        |
| `Trilha`               | Recurso: trilhas              | distancia, dificuldade (+ herdados)                                                                  |
| `Filme`                | Recurso: filmes               | ano, genero (+ atributos herdados)                                                                   |
| `Serie`                | Recurso: séries               | temporadas, genero (+ herdados)                                                                      |
| `JogoDeTabuleiro`      | Recurso: jogos de tabuleiro   | jogadoresMin, jogadoresMax, genero (+ herdados)                                                      |

## 🔗 Relacionamentos

### 📊 Relacionamentos Entre Entidades

#### 🔗 Relacionamentos 1:1 (Um para Um)

- **Membro** ↔ **ConfiguracaoMembro**: Cada membro tem uma configuração única de notificações

#### 🔗 Relacionamentos 1:N (Um para Muitos)

- **Usuario** → **Notificacao**: Um usuário pode ter muitas notificações
- **Grupo** → **Membro**: Um grupo pode ter muitos membros
- **Grupo** → **Postagem**: Um grupo pode ter muitas postagens
- **Grupo** → **Evento**: Um grupo pode organizar muitos eventos
- **Grupo** → **RecursoCompartilhado**: Um grupo pode ter muitos recursos
- **Postagem** → **Comentario**: Uma postagem pode ter muitos comentários
- **Local** → **Evento**: Um local pode hospedar muitos eventos

#### 🔗 Relacionamentos N:N (Muitos para Muitos)

- **Usuario** ↔ **Grupo**: Relacionamento através da entidade associativa **Membro**
- **Usuario** ↔ **Evento**: Um usuário pode participar de muitos eventos (lista de participantes)

### 🔗 Relacionamentos de Herança

| Classe Pai                          | Relação   | Classes Filhas                                                   |
| ----------------------------------- | --------- | ---------------------------------------------------------------- |
| **Conteudo** (Abstrata)             | `extends` | **Postagem**, **Comentario**                                     |
| **RecursoCompartilhado** (Abstrata) | `extends` | **Livro**, **Trilha**, **Filme**, **JogoDeTabuleiro**, **Serie** |

### 🔗 Resumo dos Tipos de Relacionamento

- **1:1** - Membro ↔ ConfiguracaoMembro
- **1:N** - Usuario → Notificacao, Grupo → (Membro, Postagem, Evento, RecursoCompartilhado), Postagem → Comentario, Local → Evento
- **N:N** - Usuario ↔ Grupo (via Membro), Usuario ↔ Evento (participantes)

## 🧬 Conceitos de POO Aplicados

### 🔄 Herança

```java
Conteudo (Abstrata)
├── Postagem
└── Comentario

RecursoCompartilhado (Abstrata)
├── Livro
├── Trilha
├── Filme
├── JogoDeTabuleiro
└── Serie
```

### 🎭 Polimorfismo

- Método `exibirDetalhes()` implementado diferentemente para cada tipo de recurso
- Lista polimórfica de `RecursoCompartilhado`

### 🤝 Interfaces

- `Notificavel`: Implementada por classes que podem enviar notificações

## 🛠️ CRUD Implementado

O sistema oferece diferentes níveis de operações CRUD para cada entidade:

- 👤 **Usuários** (CRUD): Cadastro, visualização, edição de dados pessoais, remoção de grupos
- 👥 **Grupos** (CRUD): Criação, visualização, edição (admin), remoção automática quando vazio
- 📅 **Eventos** (CRD): Criação (admin), visualização, participação, exclusão (admin)
- 📚 **Recursos** (CRD): Adição à biblioteca (admin), visualização de detalhes, remoção da biblioteca (admin)
- 📝 **Postagens** (CR): Criação, visualização no feed
- 💬 **Comentários** (CR): Adição em postagens, visualização
- 🔔 **Notificações** (CRU): Geração automática, visualização, marcação como lida

## ⚠️ Tratamento de Exceções

### Exceções Customizadas

- **`UsuarioJaMembroException`**: Tentativa de adicionar usuário já membro
- **`EventoLotadoException`**: Inscrição em evento com capacidade esgotada
- **`LoginException`**: Erros relacionados ao processo de autenticação
