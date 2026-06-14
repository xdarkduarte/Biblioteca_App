package com.biblioteca.view;

import com.biblioteca.controller.Biblioteca;
import com.biblioteca.model.Livro;

import javafx.collections.transformation.FilteredList;

import javafx.geometry.Insets;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LivrosView extends BorderPane {

    private TableView<Livro> tabela;

    private TextField txtTitulo;
    private TextField txtAutor;
    private TextField txtISBN;
    private TextField txtAno;
    private TextField txtCategoria;
    private TextField txtQuantidade;
    private TextField txtPesquisa;

    private FilteredList<Livro> listaFiltrada;

    public LivrosView() {

        criarInterface();
    }

    private void criarInterface() {

        setStyle(
                "-fx-background-color:#121212;"
        );

        Label titulo =
                new Label(
                        "Gestão de Livros"
                );

        titulo.setStyle(
                "-fx-font-size:24px;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-weight:bold;"
        );

        tabela = new TableView<>();

        TableColumn<Livro,String> colTitulo =
                new TableColumn<>("Título");

        colTitulo.setCellValueFactory(
                new PropertyValueFactory<>("titulo")
        );

        TableColumn<Livro,String> colAutor =
                new TableColumn<>("Autor");

        colAutor.setCellValueFactory(
                new PropertyValueFactory<>("autor")
        );

        TableColumn<Livro,String> colISBN =
                new TableColumn<>("ISBN");

        colISBN.setCellValueFactory(
                new PropertyValueFactory<>("isbn")
        );

        TableColumn<Livro,Integer> colAno =
                new TableColumn<>("Ano");

        colAno.setCellValueFactory(
                new PropertyValueFactory<>("ano")
        );

        TableColumn<Livro,String> colCategoria =
                new TableColumn<>("Categoria");

        colCategoria.setCellValueFactory(
                new PropertyValueFactory<>("categoria")
        );

        TableColumn<Livro,Integer> colQuantidade =
                new TableColumn<>("Quantidade");

        colQuantidade.setCellValueFactory(
                new PropertyValueFactory<>("quantidade")
        );

        TableColumn<Livro,Boolean> colDisponivel =
                new TableColumn<>("Disponível");

        colDisponivel.setCellValueFactory(
                new PropertyValueFactory<>("disponivel")
        );

        tabela.getColumns().addAll(
                colTitulo,
                colAutor,
                colISBN,
                colAno,
                colCategoria,
                colQuantidade,
                colDisponivel
        );

        listaFiltrada =
                new FilteredList<>(
                        Biblioteca.getLivros(),
                        p -> true
                );

        tabela.setItems(listaFiltrada);

        txtPesquisa = new TextField();
        txtPesquisa.setPromptText("Pesquisar");

        txtTitulo = new TextField();
        txtTitulo.setPromptText("Título");

        txtAutor = new TextField();
        txtAutor.setPromptText("Autor");

        txtISBN = new TextField();
        txtISBN.setPromptText("ISBN");

        txtAno = new TextField();
        txtAno.setPromptText("Ano");

        txtCategoria = new TextField();
        txtCategoria.setPromptText("Categoria");

        txtQuantidade = new TextField();
        txtQuantidade.setPromptText("Quantidade");

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

                            if(novo != null){

                                txtTitulo.setText(
                                        novo.getTitulo());

                                txtAutor.setText(
                                        novo.getAutor());

                                txtISBN.setText(
                                        novo.getIsbn());

                                txtAno.setText(
                                        String.valueOf(
                                                novo.getAno()));

                                txtCategoria.setText(
                                        novo.getCategoria());

                                txtQuantidade.setText(
                                        String.valueOf(
                                                novo.getQuantidade()));
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
                        txtTitulo,
                        txtAutor,
                        txtISBN,
                        txtAno,
                        txtCategoria,
                        txtQuantidade,
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

        Biblioteca.adicionarLivro(
                txtTitulo.getText(),
                txtAutor.getText(),
                txtISBN.getText(),
                Integer.parseInt(txtAno.getText()),
                txtCategoria.getText(),
                Integer.parseInt(
                        txtQuantidade.getText()
                )
        );

        limparCampos();
    }

    private void atualizar() {

        Livro livro =
                tabela.getSelectionModel()
                        .getSelectedItem();

        if(livro == null)
            return;

        Biblioteca.atualizarLivro(
                livro,
                txtTitulo.getText(),
                txtAutor.getText(),
                Integer.parseInt(
                        txtAno.getText()),
                txtCategoria.getText(),
                Integer.parseInt(
                        txtQuantidade.getText())
        );

        tabela.refresh();
    }

    private void eliminar() {

        Livro livro =
                tabela.getSelectionModel()
                        .getSelectedItem();

        if(livro == null)
            return;

        Biblioteca.removerLivro(livro);
    }

    private void pesquisar() {

        String texto =
                txtPesquisa.getText()
                        .toLowerCase();

        listaFiltrada.setPredicate(
                livro ->

                        livro.getTitulo()
                                .toLowerCase()
                                .contains(texto)
        );
    }

    private void limparCampos() {

        txtTitulo.clear();
        txtAutor.clear();
        txtISBN.clear();
        txtAno.clear();
        txtCategoria.clear();
        txtQuantidade.clear();
    }

    private Button criarBotao(
            String texto){

        Button btn =
                new Button(texto);

        btn.setStyle(
                "-fx-background-color:#00CEC8;" +
                        "-fx-text-fill:black;" +
                        "-fx-font-weight:bold;"
        );

        return btn;
    }
}