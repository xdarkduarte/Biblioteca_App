package com.biblioteca.controller;

import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Utilizador;
import com.biblioteca.util.DataManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    // ==========================
    // FICHEIROS
    // ==========================

    private static final String UTILIZADORES_FILE =
            "utilizadores.dat";

    private static final String LIVROS_FILE =
            "livros.dat";

    private static final String EMPRESTIMOS_FILE =
            "emprestimos.dat";

    // ==========================
    // UTILIZADORES
    // ==========================

    private static ObservableList<Utilizador> utilizadores =
            FXCollections.observableArrayList();

    private static int proximoId = 1000006;

    // ==========================
    // LIVROS
    // ==========================

    private static ObservableList<Livro> livros =
            FXCollections.observableArrayList();

    // ==========================
    // EMPRÉSTIMOS
    // ==========================

    private static ObservableList<Emprestimo> emprestimos =
            FXCollections.observableArrayList();

    // ==========================
    // DADOS INICIAIS
    // ==========================

    static {

        utilizadores.add(
                new Utilizador(
                        1000001,
                        "João Silva",
                        "joao@biblioteca.cv",
                        "9911111"
                )
        );

        utilizadores.add(
                new Utilizador(
                        1000002,
                        "Maria Lopes",
                        "maria@biblioteca.cv",
                        "9922222"
                )
        );

        utilizadores.add(
                new Utilizador(
                        1000003,
                        "Carlos Santos",
                        "carlos@biblioteca.cv",
                        "9933333"
                )
        );

        utilizadores.add(
                new Utilizador(
                        1000004,
                        "Ana Monteiro",
                        "ana@biblioteca.cv",
                        "9944444"
                )
        );

        utilizadores.add(
                new Utilizador(
                        1000005,
                        "Pedro Lima",
                        "pedro@biblioteca.cv",
                        "9955555"
                )
        );

        livros.add(
                new Livro(
                        "Java Programming",
                        "Herbert Schildt",
                        "9780001",
                        2022,
                        "Programação",
                        5
                )
        );

        livros.add(
                new Livro(
                        "Estruturas de Dados",
                        "Mark Weiss",
                        "9780002",
                        2021,
                        "Programação",
                        3
                )
        );

        livros.add(
                new Livro(
                        "Algoritmos",
                        "Thomas Cormen",
                        "9780003",
                        2020,
                        "Computação",
                        4
                )
        );

        livros.add(
                new Livro(
                        "Banco de Dados",
                        "Silberschatz",
                        "9780004",
                        2019,
                        "Base de Dados",
                        2
                )
        );

        livros.add(
                new Livro(
                        "Engenharia de Software",
                        "Ian Sommerville",
                        "9780005",
                        2023,
                        "Software",
                        6
                )
        );
    }

    // ==========================
    // UTILIZADORES
    // ==========================

    public static ObservableList<Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public static void adicionarUtilizador(
            String nome,
            String email,
            String telefone) {

        utilizadores.add(
                new Utilizador(
                        proximoId++,
                        nome,
                        email,
                        telefone
                )
        );

        guardarUtilizadores();
    }

    public static void atualizarUtilizador(
            Utilizador u,
            String nome,
            String email,
            String telefone) {

        u.setNome(nome);
        u.setEmail(email);
        u.setTelefone(telefone);

        guardarUtilizadores();
    }

    public static void removerUtilizador(
            Utilizador u) {

        utilizadores.remove(u);

        guardarUtilizadores();
    }

    // ==========================
    // LIVROS
    // ==========================

    public static ObservableList<Livro> getLivros() {
        return livros;
    }

    public static void adicionarLivro(
            String titulo,
            String autor,
            String isbn,
            int ano,
            String categoria,
            int quantidade) {

        livros.add(
                new Livro(
                        titulo,
                        autor,
                        isbn,
                        ano,
                        categoria,
                        quantidade
                )
        );

        guardarLivros();
    }

    public static void atualizarLivro(
            Livro livro,
            String titulo,
            String autor,
            int ano,
            String categoria,
            int quantidade) {

        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAno(ano);
        livro.setCategoria(categoria);
        livro.setQuantidade(quantidade);

        guardarLivros();
    }

    public static void removerLivro(
            Livro livro) {

        livros.remove(livro);

        guardarLivros();
    }

    // ==========================
    // EMPRÉSTIMOS
    // ==========================

    public static ObservableList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public static void criarEmprestimo(
            Livro livro,
            Utilizador utilizador,
            LocalDate dataDevolucao) {

        if (livro == null || utilizador == null)
            return;

        if (!livro.isDisponivel())
            return;

        Emprestimo emprestimo =
                new Emprestimo(
                        livro,
                        utilizador,
                        LocalDate.now(),
                        dataDevolucao
                );

        livro.setEmprestado(true);

        emprestimos.add(emprestimo);

        guardarEmprestimos();
    }

    public static void devolverLivro(
            Emprestimo emprestimo) {

        if (emprestimo == null)
            return;

        if (emprestimo.isDevolvido())
            return;

        emprestimo.devolver();

        emprestimo
                .getLivro()
                .setEmprestado(false);

        guardarEmprestimos();
    }

    // ==========================
    // NOTIFICAÇÕES
    // ==========================

    public static List<Emprestimo>
    emprestimosProximosDevolver() {

        List<Emprestimo> lista =
                new ArrayList<>();

        for (Emprestimo e : emprestimos) {

            if (!e.isDevolvido()) {

                long dias =
                        ChronoUnit.DAYS.between(
                                LocalDate.now(),
                                e.getDataDevolucaoPrevista()
                        );

                if (dias <= 3) {

                    lista.add(e);
                }
            }
        }

        return lista;
    }

    // ==========================
    // PERSISTÊNCIA
    // ==========================

    public static void guardarUtilizadores() {

        DataManager.guardar(
                new ArrayList<>(utilizadores),
                UTILIZADORES_FILE
        );
    }

    public static void guardarLivros() {

        DataManager.guardar(
                new ArrayList<>(livros),
                LIVROS_FILE
        );
    }

    public static void guardarEmprestimos() {

        DataManager.guardar(
                new ArrayList<>(emprestimos),
                EMPRESTIMOS_FILE
        );
    }

    @SuppressWarnings("unchecked")
    public static void carregarUtilizadores() {

        Object obj =
                DataManager.carregar(
                        UTILIZADORES_FILE
                );

        if (obj != null) {

            utilizadores.clear();

            utilizadores.addAll(
                    (List<Utilizador>) obj
            );
        }
    }

    @SuppressWarnings("unchecked")
    public static void carregarLivros() {

        Object obj =
                DataManager.carregar(
                        LIVROS_FILE
                );

        if (obj != null) {

            livros.clear();

            livros.addAll(
                    (List<Livro>) obj
            );
        }
    }

    @SuppressWarnings("unchecked")
    public static void carregarEmprestimos() {

        Object obj =
                DataManager.carregar(
                        EMPRESTIMOS_FILE
                );

        if (obj != null) {

            emprestimos.clear();

            emprestimos.addAll(
                    (List<Emprestimo>) obj
            );
        }
    }
}