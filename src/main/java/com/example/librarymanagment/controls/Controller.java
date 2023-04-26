package com.example.librarymanagment.controls;

import com.example.librarymanagment.model.Utils;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller extends Application{

        public void start(Stage primaryStage) throws IOException {
        BorderPane borderPane = new BorderPane();

        Button homeButton = new Button("Profile");
        Button aboutButton = new Button("Cook");
        Button contactButton = new Button("Filter");
        Button logOut = new Button("Log out");

        // Create an HBox to hold the navigation buttons on the left
        HBox leftNavBar = new HBox();
        leftNavBar.setPadding(new Insets(10));
        leftNavBar.setSpacing(10);
        leftNavBar.setAlignment(Pos.CENTER_LEFT);
        leftNavBar.setStyle("-fx-background-color: #f3f1ea;");
        leftNavBar.getChildren().addAll(homeButton, aboutButton, contactButton);

        // Create a StackPane to hold the search bar in the center
        StackPane centerNavBar = new StackPane();
        centerNavBar.setPadding(new Insets(10));
        centerNavBar.setStyle("-fx-background-color: #f3f1ea;");
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: #c4a054;");


        FlowPane root = Utils.searchBooks("");
        ScrollPane scrollPane = new ScrollPane();

        //Boolean isSearch = false;

        searchButton.setOnAction(e -> {
            String searchQuery = searchField.getText();
            scrollPane.setContent(Utils.searchBooks(searchQuery));


        });

        searchField.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        searchButton.fire();
                    }
                });

        centerNavBar.getChildren().add(searchField);

        //Create an HBox to hold the navigation buttons on the right
        HBox rightNavBar = new HBox();
        rightNavBar.setPadding(new Insets(10));
        rightNavBar.setSpacing(10);
        rightNavBar.setAlignment(Pos.CENTER_RIGHT);
        rightNavBar.setStyle("-fx-background-color: #f3f1ea;");
        logOut.setStyle("-fx-background-color: #3041c2;");
        rightNavBar.getChildren().addAll(searchButton,logOut);

        // Create an HBox to hold the navigation buttons and search bar
        HBox navigationBar = new HBox();
        navigationBar.getChildren().addAll(leftNavBar, centerNavBar, rightNavBar);
        HBox.setHgrow(centerNavBar, javafx.scene.layout.Priority.ALWAYS);
        navigationBar.setAlignment(Pos.CENTER);
        navigationBar.setStyle("-fx-background-color: #f3f1ea;");



        scrollPane.setContent(root);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        borderPane.setTop(navigationBar);
        borderPane.setCenter(scrollPane);
        borderPane.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));

        // Create scene and set it on the stage
        Scene scene = new Scene(borderPane, 1315, 890);
        scene.getStylesheets().add(getClass().getResource("/CSS/home.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

        logOut.setOnAction(e ->{
            login login = new login();
            login.start(new Stage());
            primaryStage.close();
        });


    }



    public static void main(String[] args) {
        launch();
    }


}
