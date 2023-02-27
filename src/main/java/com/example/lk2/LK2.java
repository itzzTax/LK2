package com.example.lk2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LK2 extends Application {

    @Override
    public void start(Stage stage) {
        // Erstelle ein Textfeld und eine Beschriftung
        TextField eingabe_feld = new TextField();
        Label textfeld = new Label("Eingabe des Graphen:");

        // Erstelle ein Gitterlayout und füge die Beschriftung und das Textfeld hinzu
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(textfeld, 0, 0);
        gridPane.add(eingabe_feld, 2, 0);

        // Canvas
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(backgroundColor);
        gc.fillRect(0, 0, width, height);
        gc.setStroke(borderColor);
        gc.setLineWidth(borderWidth);

        // Erstelle eine Szene und füge das Gitterlayout hinzu
        Scene scene = new Scene(gridPane, 1100, 700);

        // Setze die Szene und zeige das Fenster
        stage.setTitle("Graphischer Rechner");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
