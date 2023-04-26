package com.example.librarymanagment.model;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Main extends Application {

        @Override
        public void start(Stage s) throws Exception{
            s.setTitle("creating date picker");

            // create a tile pane
            TilePane r = new TilePane();

            // create a date picker
            DatePicker d = new DatePicker();
            Label l = new Label("Date");

            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    // get the date picker value
                    LocalDate i = d.getValue();

                    // get the selected date
                    l.setText("Date :" + i);
                }
            };
            d.setShowWeekNumbers(true);
            //d.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            d.setOnAction(event);




            // add button and label
            r.getChildren().addAll(d, l);

            // create a scene
            Scene sc = new Scene(r, 200, 200);

            // set the scene
            s.setScene(sc);

            s.show();

        }
    public static void main(String args[])
    {
        // launch the application
        launch(args);
    }

}