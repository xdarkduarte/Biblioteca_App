package com.biblioteca.view;

import com.biblioteca.controller.Biblioteca;
import com.biblioteca.controller.LoginController;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        // ==========================
        // CARREGAR DADOS GUARDADOS
        // ==========================

        Biblioteca.carregarUtilizadores();
        Biblioteca.carregarLivros();
        Biblioteca.carregarEmprestimos();

        // ==========================
        // ABRIR LOGIN
        // ==========================

        Biblioteca biblioteca = new Biblioteca();

        Stage loginStage = new Stage();

        LoginController loginController =
                new LoginController(
                        loginStage,
                        primaryStage,
                        biblioteca
                );

        loginStage.setScene(
                loginController.criarLayoutLogin()
        );

        loginStage.setTitle(
                "Sistema Biblioteca"
        );

        loginStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}