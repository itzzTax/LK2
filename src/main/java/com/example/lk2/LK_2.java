package com.example.lk2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class LK_2 extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Polynomen Rechner");
        // Erstelle ein Textfeld und eine Beschriftung
        TextField feld_5 = new TextField();
        TextField feld_4 = new TextField();
        TextField feld_3 = new TextField();
        TextField feld_2 = new TextField();
        TextField feld_1 = new TextField();
        TextField feld_0 = new TextField();

        Label x_hoch_5 = new Label("x⁵");
        Label x_hoch_4 = new Label("x⁴");
        Label x_hoch_3 = new Label("x³");
        Label x_hoch_2 = new Label("x²");
        Label x_hoch_1 = new Label("x");
        Label x_hoch_0 = new Label(" ");

        Button berechnen_taste = new Button("Berechnen");
        Button delete_taste = new Button("Löschen");

        //Ekrem Sahin
        // Erstellung eines Arrays, mit allen Textfeldern
        TextField[] alle_eingabe_felder = {feld_5, feld_4, feld_3, feld_2, feld_1, feld_0};

        // Überprüfung der Textfelder, ob alle mit Inhalt eine gültige eingabe enthält
        berechnen_taste.setOnAction((ActionEvent e) -> {
            boolean alle_felder_gueltig = true;
            for (TextField feld : alle_eingabe_felder) {
                String inhalt = feld.getText().replace(",", ".");
                if (!inhalt.trim().isEmpty() && !inhalt.matches("-?\\d+(\\.\\d{1,3})?")) {
                    alle_felder_gueltig = false;
                    break;
                }
            }

            // Wenn alle Eingaben der Felder gültig sind wird es Berechnet
            if (alle_felder_gueltig) {
                // Führe die Berechnung aus
            } else {
                // Fehlermeldung bei ungültigen eingaben
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Fehler");
                alert.setHeaderText("Eingabe Ungültig");
                alert.setContentText("Bitte geben Sie in den Feldern ausschließlich eine positive oder negative Zahl oder Dezimalzahl (mit maximal 3 Nachkommastellen) ein.");
                alert.showAndWait();
            }
        });


        delete_taste.setOnAction(e -> {
            // Alle felder werden gelöscht
            feld_5.clear();
            feld_4.clear();
            feld_3.clear();
            feld_2.clear();
            feld_1.clear();
            feld_0.clear();
        });


        final NumberAxis xAxis = new NumberAxis(-10, 10, 1);
        final NumberAxis yAxis = new NumberAxis(-10, 100, 10);

        // Setze den Titel und die Labels der Achsen
        xAxis.setLabel("X-Achse");
        yAxis.setLabel("Y-Achse");

        // Vermeide, dass die Achsenetiketten abgeschnitten werden, indem sie zwischen den Achsen und dem Plot verschoben werden
        xAxis.setTickLabelGap(10);
        yAxis.setTickLabelGap(10);


        // Ändere die Platzierung der Achsenetiketten, damit sie sich auf der Achse selbst befinden
        xAxis.setSide(Side.BOTTOM);
        yAxis.setSide(Side.LEFT);

        // Erstelle das Koordinatensystem
        LineChart<Number,Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setCreateSymbols(false); // Entferne die Symbole der Datenpunkte
        lineChart.setHorizontalGridLinesVisible(true); // Aktiviert horizontale Gitterlinien
        lineChart.setPadding(new Insets(2, 2, 2, 2));

        // Berechne die x- und y-Werte für die Parabel
        XYChart.Series series = new XYChart.Series();
        for (double x = -12; x <= 12; x += 0.2) {
            double y = x * x;
            series.getData().add(new XYChart.Data(x, y));
        }

        // Füge die Parabeldaten zum Koordinatensystem hinzu
        lineChart.getData().add(series);

        // Zoom-Funktion hinzufügen
        final double SCALE_DELTA = 1.1;
        lineChart.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                event.consume();

                if (event.getDeltaY() == 0) {
                    return;
                }

                double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA : 1 / SCALE_DELTA;

                xAxis.setLowerBound(xAxis.getLowerBound() * scaleFactor);
                xAxis.setUpperBound(xAxis.getUpperBound() * scaleFactor);
                yAxis.setLowerBound(yAxis.getLowerBound() * scaleFactor);
                yAxis.setUpperBound(yAxis.getUpperBound() * scaleFactor);
            }
        });


        feld_5.setPrefWidth(30);
        feld_5.setPrefHeight(10);

        feld_4.setPrefWidth(30);
        feld_4.setPrefHeight(10);

        feld_3.setPrefWidth(30);
        feld_3.setPrefHeight(10);

        feld_2.setPrefWidth(30);
        feld_2.setPrefHeight(10);

        feld_1.setPrefWidth(30);
        feld_1.setPrefHeight(10);

        feld_0.setPrefWidth(30);
        feld_0.setPrefHeight(10);

        berechnen_taste.setPrefWidth(75);
        berechnen_taste.setPrefHeight(10);

        delete_taste.setPrefWidth(75);
        delete_taste.setPrefHeight(10);

        StackPane layout = new StackPane();
        layout.getChildren().add(lineChart);

        layout.widthProperty().addListener((observable, oldValue, newValue) -> lineChart.requestLayout());
        layout.heightProperty().addListener((observable, oldValue, newValue) -> lineChart.requestLayout());




        // Erstelle ein Gitterlayout und füge die Beschriftung und das Textfeld hinzu
        SplitPane pane = new SplitPane();
        SplitPane pane1 = new SplitPane();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5));
        gridPane.setHgap(10);
        gridPane.setVgap(10);



        // Position festlegen

        gridPane.add(feld_5, 0, 0);
        gridPane.add(x_hoch_5, 1, 0);

        gridPane.add(feld_4, 0, 1);
        gridPane.add(x_hoch_4, 1, 1);

        gridPane.add(feld_3, 0, 2);
        gridPane.add(x_hoch_3, 1, 2);

        gridPane.add(feld_2, 0, 3);
        gridPane.add(x_hoch_2, 1, 3);

        gridPane.add(feld_1, 0, 4);
        gridPane.add(x_hoch_1, 1, 4);

        gridPane.add(feld_0, 0, 5);
        gridPane.add(x_hoch_0, 1, 5);

        gridPane.add(berechnen_taste, 0, 6);
        gridPane.add(delete_taste, 0, 7);

        //gridPane.add(xAxis, 2, 0);
        //gridPane.add(yAxis, 2, 0);


        pane1.getItems().add(layout);

        //hauptgrid.add(gridPane, gridPane1);


        pane.getItems().addAll(gridPane, pane1);

        // Erstelle eine Szene und füge das Gitterlayout hinzu
        Scene scene = new Scene(pane, 800, 600);

        // Setze die Szene und zeige das Fenster
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
