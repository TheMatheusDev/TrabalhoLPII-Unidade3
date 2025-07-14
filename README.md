# 🎯 HobbyConnect - Rede Social para Grupos de Hobby

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
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

| Classe       | Descrição                  | Atributos Principais   |
| ------------ | -------------------------- | ---------------------- |
| `Usuario`    | Representação dos usuários | nome, email, cidade    |
| `Grupo`      | Comunidades de hobby       | nome, descrição, tema  |
| `Membro`     | Relação usuário-grupo      | usuário, grupo, cargo  |
| `Evento`     | Eventos organizados        | título, data, local    |
| `Postagem`   | Conteúdo do feed           | título, texto, autor   |
| `Comentario` | Interações nas postagens   | texto, autor, postagem |

## 🔗 Relacionamentos

### 🔗 Tipos de Relacionamento

- **1:1** - Evento ↔ Local
- **1:N** - Grupo → Eventos, Postagem → Comentários
- **N:N** - Usuários ↔ Grupos (via Membro)

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
└── JogoDeTabuleiro
```

### 🎭 Polimorfismo

- Método `exibirDetalhes()` implementado diferentemente para cada tipo de recurso
- Lista polimórfica de `RecursoCompartilhado`

### 🤝 Interfaces

- `Notificavel`: Implementada por classes que podem enviar notificações

## 🛠️ CRUD Implementado

O sistema oferece operações completas de Create, Read, Update e Delete para:

- 👤 **Usuários**
- 👥 **Grupos**
- 📅 **Eventos**
- 📝 **Postagens**
- 📚 **Recursos** (Livros, Trilhas, etc.)

## ⚠️ Tratamento de Exceções

### Exceções Customizadas

- **`UsuarioJaMembroException`**: Tentativa de adicionar usuário já membro
- **`EventoLotadoException`**: Inscrição em evento com capacidade esgotada
