package com.biblioteca.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Emprestimo
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private Livro livro;
    private Utilizador utilizador;

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;

    private boolean devolvido;

    public Emprestimo(
            Livro livro,
            Utilizador utilizador,
            LocalDate dataEmprestimo,
            LocalDate dataDevolucaoPrevista) {

        this.livro = livro;
        this.utilizador = utilizador;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista =
                dataDevolucaoPrevista;

        this.devolvido = false;
    }

    public Livro getLivro() {
        return livro;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void devolver() {
        devolvido = true;
    }
}