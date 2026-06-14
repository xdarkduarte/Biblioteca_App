package com.biblioteca.model;

import java.io.Serializable;

public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;

    private String titulo;
    private String autor;
    private String isbn;
    private int ano;

    private String categoria;
    private int quantidade;

    private boolean emprestado;

    public Livro(
            String titulo,
            String autor,
            String isbn,
            int ano,
            String categoria,
            int quantidade) {

        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.ano = ano;
        this.categoria = categoria;
        this.quantidade = quantidade;

        this.emprestado = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    public boolean isDisponivel() {

        return !emprestado &&
                quantidade > 0;
    }

    @Override
    public String toString() {
        return titulo;
    }
}