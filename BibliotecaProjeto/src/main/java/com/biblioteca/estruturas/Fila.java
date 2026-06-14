package com.biblioteca.estruturas;
import java.io.Serializable;

public class Fila<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private No<T> frente = null;
    private No<T> tras = null;

    public void enfileirar(T info) {
        No<T> novo = new No<>(info);
        if (tras == null) {
            frente = tras = novo;
        } else {
            tras.proximo = novo;
            tras = novo;
        }
    }

    public T desenfileirar() {
        if (frente == null) return null;
        T info = frente.info;
        frente = frente.proximo;
        if (frente == null) tras = null;
        return info;
    }

    public T espiar() {
        return (frente != null) ? frente.info : null;
    }

    public boolean estaVazia() { return frente == null; }
}
