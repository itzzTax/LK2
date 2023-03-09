package com.example.lk2;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LK2 extends Application {

    @Override
    public void start(Stage stage) {
        // Erstelle die X- und Y-Achsen mit einem Bereich von -10 bis 10
        final NumberAxis xAxis = new NumberAxis(-10, 10, 1);
        final NumberAxis yAxis = new NumberAxis(-10, 100, 10); // erweitere den Bereich der y-Achse, um die Parabel darzustellen

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
        lineChart.setPadding(new Insets(2, 2, 2, 120));

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

        StackPane layout = new StackPane();
        Button button = new Button("Hello");
        button.setLayoutX(200);
        button.setLayoutY(700);
        lineChart.setLayoutX(0);
        lineChart.setLayoutY(0);
        layout.getChildren().addAll(lineChart, button);

        // Füge einen Listener hinzu, um das Koordinatensystem bei einer Änderung der Größe des Layouts neu zu zeichnen
        layout.widthProperty().addListener((observable, oldValue, newValue) -> lineChart.requestLayout());
        layout.heightProperty().addListener((observable, oldValue, newValue) -> lineChart.requestLayout());

        // Erstelle die Szene und zeige sie an
        final Scene scene = new Scene(layout, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    //Jason Scheel
    public static String ableitungen(String funktion) {
        String ergebnis = "";

        // Ableitungen berechnen
        for (int i = 0; i <= 5; i++) {
            String aktuelleAbleitung = funktion;
            for (int j = 0; j < i; j++) {
                aktuelleAbleitung = ableiten(aktuelleAbleitung);
            }

            // Erstellung des Ergebnis-Strings
            ergebnis += "f" + (i == 0 ? "(x)" : ("'" + "(".repeat(i) + "x" + ")".repeat(i))) + " = " + aktuelleAbleitung + "\n";
        }

        return ergebnis;
    }

    private static String ableiten(String funktion) {
        String[] bestandteile = funktion.split("(?=[-+*/()^])|(?<=[-+*/()^])");
        String[] neueBestandteile = new String[bestandteile.length];

        for (int i = 0; i < bestandteile.length; i++) {
            if (bestandteile[i].matches("[0-9]+")) {
                // Zahl
                neueBestandteile[i] = "0";
            } else if (bestandteile[i].equals("x")) {
                // Variable
                neueBestandteile[i] = "1";
            } else if (bestandteile[i].equals("+") || bestandteile[i].equals("-")) {
                // Addition/Subtraktion
                neueBestandteile[i] = bestandteile[i];
            } else if (bestandteile[i].equals("*")) {
                // Multiplikation
                neueBestandteile[i] = "(" + bestandteile[i - 1] + ")*(" + bestandteile[i + 1] + ")";
            } else if (bestandteile[i].equals("/")) {
                // Division
                neueBestandteile[i] = "(" + bestandteile[i - 1] + ")/(" + bestandteile[i + 1] + ")";
            } else if (bestandteile[i].equals("^")) {
                // Potenz
                if (bestandteile[i - 1].equals("x")) {
                    neueBestandteile[i] = bestandteile[i + 1] + "*x^" + (Integer.parseInt(bestandteile[i + 1]) - 1);
                } else {
                    neueBestandteile[i] = "(" + bestandteile[i - 1] + ")^" + bestandteile[i + 1] + "*(" + ableiten(bestandteile[i - 1]) + ")";
                }
            } else if (bestandteile[i].equals("(") || bestandteile[i].equals(")")) {
                // Klammern
                neueBestandteile[i] = bestandteile[i];
            }
        }
        return funktion;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

