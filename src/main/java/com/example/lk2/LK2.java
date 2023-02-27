package com.example.lk2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LK2 extends Application {

    @Override
    public void start(Stage stage) {
        // Erstelle ein Textfeld und eine Beschriftung
        TextField eingabe_feld = new TextField();
        Label textfeld = new Label("Eingabe:");

        // Erstelle ein Gitterlayout und füge die Beschriftung und das Textfeld hinzu
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(textfeld, 0, 0);
        gridPane.add(eingabe_feld, 2, 0);

        // Erstelle eine Szene und füge das Gitterlayout hinzu
        Scene scene = new Scene(gridPane, 800, 600);

        // Setze die Szene und zeige das Fenster
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
