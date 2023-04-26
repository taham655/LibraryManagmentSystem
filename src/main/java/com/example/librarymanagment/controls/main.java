package com.example.librarymanagment.controls;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Button new1 = new Button("New");
        Tooltip tooltip = new Tooltip("Create a new file");
        //Tooltip.install(label, tooltip);
        tooltip.getStyleClass().add("square-tooltip");
        new1.setTooltip(tooltip);

        HBox hbox = new HBox(new1);
        Scene scene = new Scene(hbox, 200, 100);
        scene.getStylesheets().add(getClass().getResource("/CSS/test.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }
}
