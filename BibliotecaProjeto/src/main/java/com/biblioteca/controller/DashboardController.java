package com.biblioteca.controller;

import com.biblioteca.model.Emprestimo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class DashboardController extends BorderPane {

    public DashboardController() {

        setStyle("-fx-background-color:#0D0D0D;");

        VBox principal = new VBox(25);
        principal.setPadding(new Insets(30));

        // ==========================
        // TÍTULO
        // ==========================

        Label titulo = new Label("Dashboard");

        titulo.setStyle(
                "-fx-font-size:40px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:white;"
        );

        Label subtitulo =
                new Label("Visão geral do sistema");

        subtitulo.setStyle(
                "-fx-font-size:18px;" +
                        "-fx-text-fill:#BBBBBB;"
        );

        VBox topo =
                new VBox(5, titulo, subtitulo);

        topo.setAlignment(Pos.CENTER);

        // ==========================
        // CARDS
        // ==========================

        GridPane cards = new GridPane();

        cards.setHgap(20);
        cards.setVgap(20);

        cards.add(
                criarCard(
                        "📚 Livros",
                        String.valueOf(
                                Biblioteca.getLivros().size()
                        ),
                        "Total de livros"
                ),
                0,
                0
        );

        cards.add(
                criarCard(
                        "👥 Utilizadores",
                        String.valueOf(
                                Biblioteca.getUtilizadores().size()
                        ),
                        "Total de utilizadores"
                ),
                1,
                0
        );

        cards.add(
                criarCard(
                        "📋 Empréstimos",
                        String.valueOf(
                                Biblioteca.getEmprestimos().size()
                        ),
                        "Total de empréstimos"
                ),
                0,
                1
        );

        long atrasados =
                Biblioteca.getEmprestimos()
                        .stream()
                        .filter(e -> !e.isDevolvido())
                        .count();

        cards.add(
                criarCard(
                        "🔔 Pendentes",
                        String.valueOf(atrasados),
                        "Aguardam devolução"
                ),
                1,
                1
        );

        // ==========================
        // PARTE INFERIOR
        // ==========================

        HBox inferior =
                new HBox(20);

        inferior.getChildren().addAll(
                criarGrafico(),
                criarEmprestimosRecentes(),
                criarNotificacoes()
        );

        principal.getChildren().addAll(
                topo,
                cards,
                inferior
        );

        setCenter(principal);
    }

    private VBox criarCard(
            String titulo,
            String valor,
            String descricao) {

        Label lblTitulo =
                new Label(titulo);

        lblTitulo.setStyle(
                "-fx-font-size:24px;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-weight:bold;"
        );

        Label lblValor =
                new Label(valor);

        lblValor.setStyle(
                "-fx-font-size:42px;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-weight:bold;"
        );

        Label lblDescricao =
                new Label(descricao);

        lblDescricao.setStyle(
                "-fx-text-fill:#CCCCCC;"
        );

        VBox card =
                new VBox(
                        15,
                        lblTitulo,
                        lblValor,
                        lblDescricao
                );

        card.setPadding(
                new Insets(25)
        );

        card.setPrefSize(
                450,
                180
        );

        card.setStyle(
                "-fx-background-color:#1A1A1A;" +
                        "-fx-border-color:#00CEC8;" +
                        "-fx-border-radius:20;" +
                        "-fx-background-radius:20;"
        );

        return card;
    }

    private VBox criarGrafico() {

        long ativos =
                Biblioteca.getEmprestimos()
                        .stream()
                        .filter(e -> !e.isDevolvido())
                        .count();

        long devolvidos =
                Biblioteca.getEmprestimos()
                        .stream()
                        .filter(Emprestimo::isDevolvido)
                        .count();

        ObservableList<PieChart.Data> dados =
                FXCollections.observableArrayList(
                        new PieChart.Data(
                                "Ativos",
                                ativos
                        ),
                        new PieChart.Data(
                                "Devolvidos",
                                devolvidos
                        )
                );

        PieChart grafico =
                new PieChart(dados);

        grafico.setLegendVisible(true);

        VBox box =
                new VBox(
                        15,
                        new Label("📊 Estatísticas"),
                        grafico
                );

        box.setPadding(
                new Insets(20)
        );

        box.setPrefWidth(350);

        box.setStyle(
                "-fx-background-color:#1A1A1A;" +
                        "-fx-background-radius:20;"
        );

        return box;
    }

    private VBox criarEmprestimosRecentes() {

        VBox box =
                new VBox(10);

        Label titulo =
                new Label(
                        "📋 Empréstimos Recentes"
                );

        titulo.setStyle(
                "-fx-font-size:22px;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-weight:bold;"
        );

        box.getChildren().add(titulo);

        Biblioteca.getEmprestimos()
                .stream()
                .limit(5)
                .forEach(e -> {

                    Label lbl =
                            new Label(
                                    e.getLivro().getTitulo()
                                            + " → "
                                            + e.getUtilizador().getNome()
                            );

                    lbl.setStyle(
                            "-fx-text-fill:white;"
                    );

                    box.getChildren().add(lbl);
                });

        box.setPadding(
                new Insets(20)
        );

        box.setPrefWidth(400);

        box.setStyle(
                "-fx-background-color:#1A1A1A;" +
                        "-fx-background-radius:20;"
        );

        return box;
    }

    private VBox criarNotificacoes() {

        VBox box =
                new VBox(15);

        Label titulo =
                new Label("🔔 Notificações");

        titulo.setStyle(
                "-fx-font-size:22px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:white;"
        );

        Label alerta =
                new Label(
                        "Verifique empréstimos próximos da devolução."
                );

        alerta.setStyle(
                "-fx-text-fill:#FFD166;" +
                        "-fx-font-size:16px;"
        );

        box.getChildren().addAll(
                titulo,
                alerta
        );

        box.setPadding(
                new Insets(20)
        );

        box.setPrefWidth(400);

        box.setStyle(
                "-fx-background-color:#1A1A1A;" +
                        "-fx-background-radius:20;"
        );

        return box;
    }
}