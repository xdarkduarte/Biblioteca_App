package com.biblioteca.estruturas;
import java.io.Serializable;

public class ListaLigada<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private No<T> inicio = null;
    private int tamanho = 0;

    public void adicionar(T info) {
        No<T> novo = new No<>(info);
        if (inicio == null) {
            inicio = novo;
        } else {
            No<T> atual = inicio;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novo;
        }
        tamanho++;
    }

    public boolean remover(T info) {
        if (inicio == null) return false;
        if (inicio.info.equals(info)) {
            inicio = inicio.proximo;
            tamanho--;
            return true;
        }
        No<T> atual = inicio;
        while (atual.proximo != null && !atual.proximo.info.equals(info)) {
            atual = atual.proximo;
        }
        if (atual.proximo != null) {
            atual.proximo = atual.proximo.proximo;
            tamanho--;
            return true;
        }
        return false;
    }

    public No<T> getInicio() { return inicio; }
    public int getTamanho() { return tamanho; }
    public boolean estaVazia() { return inicio == null; }
}
