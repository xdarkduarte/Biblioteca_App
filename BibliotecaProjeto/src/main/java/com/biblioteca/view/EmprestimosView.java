package com.biblioteca.view;

import com.biblioteca.controller.Biblioteca;
import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Utilizador;

import javafx.geometry.Insets;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class EmprestimosView extends BorderPane {

    private TableView<Emprestimo> tabela;

    private ComboBox<Livro> cbLivro;
    private ComboBox<Utilizador> cbUtilizador;

    private DatePicker dpDevolucao;

    public EmprestimosView() {

        criarInterface();
    }

    private void criarInterface() {

        setStyle(
                "-fx-background-color:#121212;"
        );

        Label titulo =
                new Label(
                        "Gestão de Empréstimos"
                );

        titulo.setStyle(
                "-fx-font-size:24px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:white;"
        );

        tabela = new TableView<>();

        TableColumn<Emprestimo,String> colLivro =
                new TableColumn<>("Livro");

        colLivro.setCellValueFactory(
                cell ->
                        new javafx.beans.property.SimpleStringProperty(
                                cell.getValue()
                                        .getLivro()
                                        .getTitulo()
                        )
        );

        TableColumn<Emprestimo,String> colUtilizador =
                new TableColumn<>("Utilizador");

        colUtilizador.setCellValueFactory(
                cell ->
                        new javafx.beans.property.SimpleStringProperty(
                                cell.getValue()
                                        .getUtilizador()
                                        .getNome()
                        )
        );

        TableColumn<Emprestimo, LocalDate> colEmprestimo =
                new TableColumn<>("Data Empréstimo");

        colEmprestimo.setCellValueFactory(
                new PropertyValueFactory<>(
                        "dataEmprestimo"
                )
        );

        TableColumn<Emprestimo, LocalDate> colDevolucao =
                new TableColumn<>("Data Devolução");

        colDevolucao.setCellValueFactory(
                new PropertyValueFactory<>(
                        "dataDevolucaoPrevista"
                )
        );

        TableColumn<Emprestimo, Boolean> colEstado =
                new TableColumn<>("Devolvido");

        colEstado.setCellValueFactory(
                new PropertyValueFactory<>(
                        "devolvido"
                )
        );

        tabela.getColumns().addAll(
                colLivro,
                colUtilizador,
                colEmprestimo,
                colDevolucao,
                colEstado
        );

        tabela.setItems(
                Biblioteca.getEmprestimos()
        );

        cbLivro =
                new ComboBox<>();

        cbLivro.setPromptText(
                "Livro"
        );

        cbLivro.setItems(
                Biblioteca.getLivros()
        );

        cbUtilizador =
                new ComboBox<>();

        cbUtilizador.setPromptText(
                "Utilizador"
        );

        cbUtilizador.setItems(
                Biblioteca.getUtilizadores()
        );

        dpDevolucao =
                new DatePicker();

        dpDevolucao.setValue(
                LocalDate.now().plusDays(7)
        );

        Button btnEmprestar =
                criarBotao(
                        "Emprestar"
                );

        Button btnDevolver =
                criarBotao(
                        "Devolver"
                );

        btnEmprestar.setOnAction(
                e -> emprestar()
        );

        btnDevolver.setOnAction(
                e -> devolver()
        );

        HBox formulario =
                new HBox(
                        10,
                        cbLivro,
                        cbUtilizador,
                        dpDevolucao,
                        btnEmprestar,
                        btnDevolver
                );

        VBox topo =
                new VBox(
                        15,
                        titulo,
                        formulario
                );

        topo.setPadding(
                new Insets(15)
        );

        setTop(topo);
        setCenter(tabela);
    }

    private void emprestar() {

        Livro livro =
                cbLivro.getValue();

        Utilizador utilizador =
                cbUtilizador.getValue();

        if (livro == null ||
                utilizador == null) {

            return;
        }

        if (!livro.isDisponivel()) {

            Alert alert =
                    new Alert(
                            Alert.AlertType.WARNING
                    );

            alert.setContentText(
                    "Livro indisponível."
            );

            alert.showAndWait();

            return;
        }

        Biblioteca.criarEmprestimo(
                livro,
                utilizador,
                dpDevolucao.getValue()
        );

        tabela.refresh();
    }

    private void devolver() {

        Emprestimo emprestimo =
                tabela.getSelectionModel()
                        .getSelectedItem();

        if (emprestimo == null)
            return;

        Biblioteca.devolverLivro(
                emprestimo
        );

        tabela.refresh();
    }

    private Button criarBotao(
            String texto) {

        Button btn =
                new Button(texto);

        btn.setStyle(
                "-fx-background-color:#00CEC8;" +
                        "-fx-text-fill:black;" +
                        "-fx-font-weight:bold;" +
                        "-fx-background-radius:15;"
        );

        return btn;
    }
}