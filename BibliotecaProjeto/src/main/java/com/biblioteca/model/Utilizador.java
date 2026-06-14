package com.biblioteca.model;

import java.io.Serializable;

public class Utilizador implements Serializable {

    private int id;
    private String nome;
    private String email;
    private String telefone;

    private String username;
    private String password;

    public Utilizador(int id,
                      String nome,
                      String email,
                      String telefone) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Utilizador(String username,
                      String password,
                      String nome) {

        this.username = username;
        this.password = password;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return nome;
    }}