package com.biblioteca.controller;

import com.biblioteca.service.UtilizadorService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;

import javafx.scene.control.*;

import javafx.scene.layout.VBox;

import javafx.stage.Stage;

public class LoginController {

    private Stage loginStage;
    private Stage mainStage;
    private Biblioteca biblioteca;

    private TextField txtUsername;
    private PasswordField txtPassword;

    public LoginController(
            Stage loginStage,
            Stage mainStage,
            Biblioteca biblioteca) {

        this.loginStage = loginStage;
        this.mainStage = mainStage;
        this.biblioteca = biblioteca;
    }

    public Scene criarLayoutLogin() {

        Label logo =
                new Label("📚");

        logo.setStyle(
                "-fx-font-size:60px;"
        );

        Label titulo =
                new Label(
                        "Biblioteca Digital"
                );

        titulo.setStyle(
                "-fx-font-size:26px;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-weight:bold;"
        );

        txtUsername =
                new TextField();

        txtUsername.setPromptText(
                "Utilizador"
        );

        txtPassword =
                new PasswordField();

        txtPassword.setPromptText(
                "Palavra-passe"
        );

        Button btnEntrar =
                new Button("Entrar");

        Button btnCriarConta =
                new Button("Criar Conta");

        btnEntrar.setMaxWidth(
                Double.MAX_VALUE);

        btnCriarConta.setMaxWidth(
                Double.MAX_VALUE);

        btnEntrar.setOnAction(
                e -> fazerLogin());

        btnCriarConta.setOnAction(
                e -> criarConta());

        VBox root =
                new VBox(15);

        root.setAlignment(
                Pos.CENTER);

        root.setPadding(
                new Insets(40));

        root.setStyle(
                "-fx-background-color:#121212;"
        );

        root.getChildren().addAll(
                logo,
                titulo,
                txtUsername,
                txtPassword,
                btnEntrar,
                btnCriarConta
        );

        return new Scene(
                root,
                500,
                600
        );
    }

    private void fazerLogin() {

        boolean autenticado =
                UtilizadorService.login(
                        txtUsername.getText(),
                        txtPassword.getText()
                );

        System.out.println("Autenticado = " + autenticado);

        if (autenticado) {

            System.out.println("Abrindo janela principal...");

            MainController controller =
                    new MainController();

            controller.mostrar(mainStage);

            loginStage.close();

        } else {

            Alert alert =
                    new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText(null);
            alert.setContentText("Login inválido");

            alert.showAndWait();
        }
    }

    private void criarConta() {

        TextInputDialog nomeDialog =
                new TextInputDialog();

        nomeDialog.setHeaderText(
                "Nome"
        );

        String nome =
                nomeDialog
                        .showAndWait()
                        .orElse("");

        TextInputDialog userDialog =
                new TextInputDialog();

        userDialog.setHeaderText(
                "Username"
        );

        String username =
                userDialog
                        .showAndWait()
                        .orElse("");

        TextInputDialog passDialog =
                new TextInputDialog();

        passDialog.setHeaderText(
                "Password"
        );

        String password =
                passDialog
                        .showAndWait()
                        .orElse("");

        boolean criado =
                UtilizadorService.criarConta(
                        nome,
                        username,
                        password
                );

        Alert alert =
                new Alert(
                        criado
                                ? Alert.AlertType.INFORMATION
                                : Alert.AlertType.ERROR
                );

        alert.setHeaderText(null);

        alert.setContentText(
                criado
                        ? "Conta criada!"
                        : "Username já existe!"
        );

        alert.showAndWait();
    }
}