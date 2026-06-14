package com.biblioteca.estruturas;

import java.io.Serializable;

public class No<T> implements Serializable {

    T info;
    No<T> proximo;

    public No(T info) {
        this.info = info;
    }

    public T getDado() {
        return info;
    }

    public No<T> getProximo() {
        return proximo;
    }
}