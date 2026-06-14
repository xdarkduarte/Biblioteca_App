# Sistema de Gestão de Biblioteca

## Descrição

O Sistema de Gestão de Biblioteca é uma aplicação desktop desenvolvida em **Java** utilizando **JavaFX** para a interface gráfica. O objetivo do sistema é facilitar a gestão de livros, utilizadores e empréstimos de forma simples, intuitiva e eficiente.

O projeto foi desenvolvido aplicando os conceitos da disciplina de **Conceitos de Algoritmos e Estruturas de Dados**, utilizando Programação Orientada a Objetos, estruturas de dados, persistência em ficheiros e interfaces gráficas modernas.

Além disso, o sistema possui autenticação de utilizadores, dashboard interativo, notificações automáticas e armazenamento permanente dos dados.

---

# Funcionalidades

### Autenticação

* Login de utilizadores
* Criação de novas contas
* Validação de credenciais
* Conta de administrador padrão

```text
Utilizador: admin
Password: admin
```

---

### Gestão de Utilizadores

* Adicionar utilizadores
* Atualizar utilizadores
* Eliminar utilizadores
* Visualizar utilizadores
* IDs automáticos de 7 dígitos

Cada utilizador possui:

* ID
* Nome
* Email
* Telefone

Exemplo:

```text
1000001 - João Silva
1000002 - Maria Lopes
1000003 - Carlos Santos
```

---

### Gestão de Livros

* Adicionar livros
* Atualizar livros
* Eliminar livros
* Pesquisar livros
* Consultar disponibilidade

Cada livro possui:

* Título
* Autor
* ISBN
* Ano
* Categoria
* Quantidade
* Estado (Disponível / Emprestado)

Exemplo:

```text
Título: Java Programming
Autor: Herbert Schildt
ISBN: 9780001
Categoria: Programação
Quantidade: 5
```

---

### Gestão de Empréstimos

* Registar empréstimos
* Selecionar livro
* Selecionar utilizador
* Definir data de devolução
* Devolver livros
* Controlo automático da disponibilidade dos livros

Informações do empréstimo:

* Livro
* Utilizador
* Data do empréstimo
* Data prevista de devolução
* Estado (Devolvido / Pendente)

---

### Sistema de Notificações

O sistema verifica automaticamente:

* Livros próximos da data de devolução
* Empréstimos pendentes
* Empréstimos em atraso

As notificações aparecem quando faltam até 3 dias para a devolução.

Exemplo:

```text
Livro: Java Programming

Utilizador: João Silva

Devolução prevista:
15/06/2026

Estado:
Próximo da devolução
```

---

### Dashboard

Apresenta informações gerais:

* Total de livros
* Total de utilizadores
* Total de empréstimos
* Empréstimos pendentes
* Livros disponíveis
* Livros emprestados

O dashboard utiliza cartões estatísticos modernos para facilitar a visualização das informações.

---

### Persistência de Dados

Os dados são guardados automaticamente através de serialização Java.

Ficheiros gerados:

```text
utilizadores.dat
livros.dat
emprestimos.dat
```

Os dados permanecem disponíveis mesmo após fechar a aplicação.

Ao iniciar:

```java
Biblioteca.carregarUtilizadores();
Biblioteca.carregarLivros();
Biblioteca.carregarEmprestimos();
```

Ao alterar dados:

```java
Biblioteca.guardarUtilizadores();
Biblioteca.guardarLivros();
Biblioteca.guardarEmprestimos();
```

---

# Tecnologias Utilizadas

* Java 21
* JavaFX 21
* Maven
* CSS
* Serialização Java
* IntelliJ IDEA
* Programação Orientada a Objetos (POO)

---

# Estrutura do Projeto

```text
src
└── main
    ├── java
    │
    └── com.biblioteca
        │
        ├── controller
        │   ├── Biblioteca.java
        │   ├── LoginController.java
        │   ├── MainController.java
        │   └── DashboardController.java
        │
        ├── model
        │   ├── Utilizador.java
        │   ├── Livro.java
        │   ├── Emprestimo.java
        │   └── Operacao.java
        │
        ├── service
        │   └── UtilizadorService.java
        │
        ├── util
        │   └── DataManager.java
        │
        ├── estruturas
        │   ├── ListaLigada.java
        │   ├── No.java
        │   ├── Fila.java
        │   └── Pilha.java
        │
        └── view
            ├── UtilizadoresView.java
            ├── LivrosView.java
            ├── EmprestimosView.java
            ├── NotificacoesView.java
            └── MainApp.java

    └── resources
        └── style.css
```

---

# Executar o Projeto

## 1. Clonar o repositório

```bash
git clone https://github.com/seu-utilizador/biblioteca.git
```

---

## 2. Abrir no IntelliJ IDEA

Abrir a pasta do projeto.

---

## 3. Atualizar dependências Maven

```bash
Reload Maven Project
```

ou

```bash
mvn clean install
```

---

## 4. Executar

Executar:

```text
MainApp.java
```

ou

```bash
mvn javafx:run
```

---

# Interface

O sistema utiliza:

* Tema escuro
* Botões em cor turquesa (#00CEC8)
* Dashboard moderno
* Cartões estatísticos
* Menu lateral
* Tabelas organizadas
* Interface intuitiva
* Design responsivo

### Paleta de Cores

```text
Fundo Principal: #121212

Menu Lateral: #181818

Botões: #00CEC8

Texto: #FFFFFF

Cards: #1E1E1E
```

---

# Conceitos de algoritmos e estruturas de dados Aplicados

### Encapsulamento

Utilização de atributos privados e métodos getters/setters.

### Abstração

Modelação das entidades:

* Utilizador
* Livro
* Empréstimo

### Herança

Estrutura preparada para futuras extensões de tipos de utilizadores.

### Polimorfismo

Possibilidade de implementação de diferentes comportamentos para diferentes perfis de utilizador.

---

# Estruturas de Dados Utilizadas

### Lista Ligada

Utilizada para armazenamento dinâmico de dados.

### Pilha (Stack)

Implementação do conceito LIFO (Last In, First Out).

### Fila (Queue)

Implementação do conceito FIFO (First In, First Out).

---

# Melhorias Futuras

* Exportação para PDF
* Exportação para Excel
* Gráficos estatísticos
* Base de dados MySQL
* Perfis de utilizador
* Histórico de operações
* Backup automático
* Upload de capa dos livros
* Leitura de código de barras ISBN
* Sistema de multas por atraso
* Pesquisa avançada
* Relatórios automáticos
* Dashboard com gráficos

---


