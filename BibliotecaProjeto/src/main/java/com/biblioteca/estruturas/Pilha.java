package com.biblioteca.estruturas;

import com.biblioteca.model.Operacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pilha implements Serializable {

    private No<Operacao> topo;

    public void push(Operacao op) {

        No<Operacao> novo =
                new No<>(op);

        novo.proximo = topo;

        topo = novo;
    }

    public Operacao pop() {

        if (topo == null)
            return null;

        Operacao op =
                topo.info;

        topo = topo.proximo;

        return op;
    }

    public boolean isEmpty() {

        return topo == null;
    }

    public List<Operacao> paraLista() {

        List<Operacao> lista =
                new ArrayList<>();

        No<Operacao> temp = topo;

        while (temp != null) {

            lista.add(temp.info);

            temp = temp.proximo;
        }

        return lista;
    }
}