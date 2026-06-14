package com.biblioteca.view;

import com.biblioteca.controller.Biblioteca;
import com.biblioteca.model.Emprestimo;

import javafx.geometry.Insets;

import javafx.scene.control.ListView;
import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class NotificacoesView extends BorderPane {

    private ListView<String> lista;

    public NotificacoesView() {

        criarInterface();
        carregarNotificacoes();
    }

    private void criarInterface() {

        setStyle(
                "-fx-background-color:#121212;"
        );

        Label titulo =
                new Label(
                        "🔔 Notificações"
                );

        titulo.setStyle(
                "-fx-font-size:24px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:white;"
        );

        lista =
                new ListView<>();

        lista.setStyle(
                "-fx-background-color:#1E1E1E;" +
                        "-fx-control-inner-background:#1E1E1E;" +
                        "-fx-text-fill:white;"
        );

        VBox topo =
                new VBox(titulo);

        topo.setPadding(
                new Insets(15)
        );

        setTop(topo);
        setCenter(lista);
    }

    private void carregarNotificacoes() {

        lista.getItems().clear();

        LocalDate hoje =
                LocalDate.now();

        for (Emprestimo e :
                Biblioteca.getEmprestimos()) {

            if (e.isDevolvido()) {
                continue;
            }

            long dias =
                    hoje.until(
                            e.getDataDevolucaoPrevista()
                    ).getDays();

            if (dias < 0) {

                lista.getItems().add(
                        "🚨 ATRASADO: "
                                + e.getLivro().getTitulo()
                                + " | "
                                + e.getUtilizador().getNome()
                );
            }

            else if (dias <= 3) {

                lista.getItems().add(
                        "⚠ Devolução próxima: "
                                + e.getLivro().getTitulo()
                                + " | "
                                + dias
                                + " dia(s)"
                );
            }
        }

        if (lista.getItems().isEmpty()) {

            lista.getItems().add(
                    "✅ Sem notificações."
            );
        }
    }
}