package com.biblioteca.model;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Operacao implements Serializable {
    private static final long serialVersionUID = 1L;
    private String descricao;
    private LocalDateTime timestamp;

    public Operacao(String descricao) {
        this.descricao = descricao;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "[" + timestamp.toLocalTime().toString().substring(0, 5) + "] " + descricao;
    }
}
