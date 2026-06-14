package com.biblioteca.view;

import com.biblioteca.controller.Biblioteca;
import com.biblioteca.model.Utilizador;

import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UtilizadoresView extends BorderPane {

    private TableView<Utilizador> tabela;

    private TextField txtNome;
    private TextField txtEmail;
    private TextField txtTelefone;
    private TextField txtPesquisa;

    private FilteredList<Utilizador> listaFiltrada;

    public UtilizadoresView() {

        criarInterface();
    }

    private void criarInterface() {

        setStyle("-fx-background-color:#121212;");

        Label titulo =
                new Label(
                        "Gestão de Utilizadores"
                );

        titulo.setStyle(
                "-fx-font-size:24px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:white;"
        );

        tabela = new TableView<>();

        tabela.setStyle(
                "-fx-background-color:#1E1E1E;"
        );

        TableColumn<Utilizador, Integer> colId =
                new TableColumn<>("ID");

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        TableColumn<Utilizador, String> colNome =
                new TableColumn<>("Nome");

        colNome.setCellValueFactory(
                new PropertyValueFactory<>("nome")
        );

        TableColumn<Utilizador, String> colEmail =
                new TableColumn<>("Email");

        colEmail.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );

        TableColumn<Utilizador, String> colTelefone =
                new TableColumn<>("Telefone");

        colTelefone.setCellValueFactory(
                new PropertyValueFactory<>("telefone")
        );

        tabela.getColumns().addAll(
                colId,
                colNome,
                colEmail,
                colTelefone
        );

        listaFiltrada =
                new FilteredList<>(
                        Biblioteca.getUtilizadores(),
                        p -> true
                );

        tabela.setItems(listaFiltrada);

        txtPesquisa = new TextField();
        txtPesquisa.setPromptText("Pesquisar utilizador");

        txtNome = new TextField();
        txtNome.setPromptText("Nome");

        txtEmail = new TextField();
        txtEmail.setPromptText("Email");

        txtTelefone = new TextField();
        txtTelefone.setPromptText("Telefone");

        aplicarEstiloCampo(txtPesquisa);
        aplicarEstiloCampo(txtNome);
        aplicarEstiloCampo(txtEmail);
        aplicarEstiloCampo(txtTelefone);

        Button btnAdicionar =
                criarBotao("Adicionar");

        Button btnAtualizar =
                criarBotao("Atualizar");

        Button btnEliminar =
                criarBotao("Eliminar");

        Button btnPesquisar =
                criarBotao("Pesquisar");

        btnAdicionar.setOnAction(
                e -> adicionar()
        );

        btnAtualizar.setOnAction(
                e -> atualizar()
        );

        btnEliminar.setOnAction(
                e -> eliminar()
        );

        btnPesquisar.setOnAction(
                e -> pesquisar()
        );

        tabela.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (obs, antigo, novo) -> {

                            if (novo != null) {

                                txtNome.setText(
                                        novo.getNome());

                                txtEmail.setText(
                                        novo.getEmail());

                                txtTelefone.setText(
                                        novo.getTelefone());
                            }
                        });

        HBox pesquisaBox =
                new HBox(
                        10,
                        txtPesquisa,
                        btnPesquisar
                );

        HBox formulario =
                new HBox(
                        10,
                        txtNome,
                        txtEmail,
                        txtTelefone,
                        btnAdicionar,
                        btnAtualizar,
                        btnEliminar
                );

        VBox topo =
                new VBox(
                        15,
                        titulo,
                        pesquisaBox,
                        formulario
                );

        topo.setPadding(
                new Insets(15)
        );

        setTop(topo);
        setCenter(tabela);
    }

    private void adicionar() {

        if (txtNome.getText().trim().isEmpty()) {
            return;
        }

        Biblioteca.adicionarUtilizador(
                txtNome.getText(),
                txtEmail.getText(),
                txtTelefone.getText()
        );

        limparCampos();
    }

    private void atualizar() {

        Utilizador u =
                tabela.getSelectionModel()
                        .getSelectedItem();

        if (u == null)
            return;

        Biblioteca.atualizarUtilizador(
                u,
                txtNome.getText(),
                txtEmail.getText(),
                txtTelefone.getText()
        );

        tabela.refresh();
    }

    private void eliminar() {

        Utilizador u =
                tabela.getSelectionModel()
                        .getSelectedItem();

        if (u == null)
            return;

        Alert confirmacao =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirmacao.setTitle(
                "Confirmação"
        );

        confirmacao.setHeaderText(
                "Eliminar Utilizador"
        );

        confirmacao.setContentText(
                "Deseja realmente eliminar?"
        );

        confirmacao.showAndWait()
                .ifPresent(resposta -> {

                    if (resposta ==
                            ButtonType.OK) {

                        Biblioteca.removerUtilizador(u);
                    }
                });

        limparCampos();
    }

    private void pesquisar() {

        String texto =
                txtPesquisa
                        .getText()
                        .toLowerCase();

        listaFiltrada.setPredicate(
                utilizador -> {

                    if (texto.isEmpty())
                        return true;

                    return utilizador
                            .getNome()
                            .toLowerCase()
                            .contains(texto);
                });
    }

    private void limparCampos() {

        txtNome.clear();
        txtEmail.clear();
        txtTelefone.clear();
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

    private void aplicarEstiloCampo(
            TextField campo) {

        campo.setStyle(
                "-fx-background-color:#2A2A2A;" +
                        "-fx-text-fill:white;" +
                        "-fx-prompt-text-fill:gray;"
        );
    }
}