package com.biblioteca.service;

import com.biblioteca.model.Utilizador;

import java.util.ArrayList;
import java.util.List;

public class UtilizadorService {

    private static final List<Utilizador> contas =
            new ArrayList<>();

    static {

        contas.add(
                new Utilizador(
                        "admin",
                        "admin",
                        "Administrador"
                )
        );
    }

    public static boolean login(
            String username,
            String password) {

        for (Utilizador u : contas) {

            if (u.getUsername().equals(username)
                    && u.getPassword().equals(password)) {

                return true;
            }
        }

        return false;
    }

    public static boolean criarConta(
            String nome,
            String username,
            String password) {

        for (Utilizador u : contas) {

            if (u.getUsername().equals(username)) {

                return false;
            }
        }

        contas.add(
                new Utilizador(
                        username,
                        password,
                        nome
                )
        );

        return true;
    }

    public static List<Utilizador> getContas() {
        return contas;
    }
}