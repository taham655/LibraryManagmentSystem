package com.example.librarymanagment.controls;

import com.example.librarymanagment.model.JDBC;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class testing extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        root.setPadding(new Insets(10));

        // Create a Rectangle to represent the card
        Rectangle card = new Rectangle(200, 100);
        card.setFill(Color.WHITE);
        card.setArcWidth(10);//card.setStroke(Color.BLACK);
        card.setArcHeight(10);
        //card.setStrokeWidth(1.5);
        //card.setStyle("-fx-background-radius: 10px;");
        root.getChildren().add(card);

        // Create a Text node to display the balance
        Text balanceText = new Text("Balance: " + JDBC.getbalance(90)); // Replace with the actual balance value
        balanceText.setFont(Font.font("Arial", 16));
        root.getChildren().add(balanceText);

        // Set the color of the card based on whether there is an outstanding balance or not
        if (JDBC.getbalance(22) >0) { // Replace with the logic to check if there is an outstanding balance
            card.setFill(Color.RED);
        } else {
            card.setFill(Color.GREEN);
        }

        // Create a Scene and set it on the primary stage
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Balance Card");
        primaryStage.show();
    }


}

