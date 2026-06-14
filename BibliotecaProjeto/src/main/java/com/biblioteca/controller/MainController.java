package com.biblioteca.controller;

import com.biblioteca.view.EmprestimosView;
import com.biblioteca.view.LivrosView;
import com.biblioteca.view.NotificacoesView;
import com.biblioteca.view.UtilizadoresView;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {

    public void mostrar(Stage stage) {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color:#0D0D0D;"
        );

        // DASHBOARD INICIAL

        DashboardController dashboard =
                new DashboardController();

        root.setCenter(dashboard);

        // MENU LATERAL

        VBox menu = new VBox(15);

        menu.setPadding(
                new Insets(20)
        );

        menu.setPrefWidth(260);

        menu.setStyle(
                "-fx-background-color:#111111;"
        );

        // LOGO

        Label titulo =
                new Label("📚 Biblioteca");

        titulo.setStyle(
                "-fx-font-size:32px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:white;"
        );

        Label subtitulo =
                new Label("Sistema de Gestão");

        subtitulo.setStyle(
                "-fx-font-size:16px;" +
                        "-fx-text-fill:#CCCCCC;"
        );

        // BOTÕES

        Button btnDashboard =
                criarBotao("📊 Dashboard");

        Button btnUtilizadores =
                criarBotao("👥 Utilizadores");

        Button btnLivros =
                criarBotao("📚 Livros");

        Button btnEmprestimos =
                criarBotao("📋 Empréstimos");

        Button btnNotificacoes =
                criarBotao("🔔 Notificações");

        Button btnLogout =
                criarBotao("🚪 Logout");

        // EVENTOS

        btnDashboard.setOnAction(
                e -> root.setCenter(
                        new DashboardController()
                )
        );

        btnUtilizadores.setOnAction(
                e -> root.setCenter(
                        new UtilizadoresView()
                )
        );

        btnLivros.setOnAction(
                e -> root.setCenter(
                        new LivrosView()
                )
        );

        btnEmprestimos.setOnAction(
                e -> root.setCenter(
                        new EmprestimosView()
                )
        );

        btnNotificacoes.setOnAction(
                e -> root.setCenter(
                        new NotificacoesView()
                )
        );

        btnLogout.setOnAction(
                e -> stage.close()
        );

        // ESPAÇADOR

        Region spacer = new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        // RODAPÉ

        Label utilizador =
                new Label(
                        "👤 Administrador\nadmin"
                );

        utilizador.setStyle(
                "-fx-font-size:15px;" +
                        "-fx-text-fill:white;"
        );

        menu.getChildren().addAll(
                titulo,
                subtitulo,
                btnDashboard,
                btnUtilizadores,
                btnLivros,
                btnEmprestimos,
                btnNotificacoes,
                btnLogout,
                spacer,
                utilizador
        );

        root.setLeft(menu);

        // SCENE

        Scene scene =
                new Scene(
                        root,
                        1600,
                        900
                );

        try {

            scene.getStylesheets().add(
                    getClass()
                            .getResource("/style.css")
                            .toExternalForm()
            );

        } catch (Exception e) {

            System.out.println(
                    "CSS não encontrado."
            );
        }

        stage.setTitle(
                "Sistema de Gestão de Biblioteca"
        );

        stage.setMaximized(true);

        stage.setScene(scene);

        stage.show();
    }

    private Button criarBotao(
            String texto) {

        Button btn =
                new Button(texto);

        btn.setMaxWidth(
                Double.MAX_VALUE
        );

        btn.setPrefHeight(
                55
        );

        btn.setStyle(
                "-fx-background-color:#00CEC8;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-size:16px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-background-radius:15;" +
                        "-fx-cursor:hand;"
        );

        return btn;
    }
}