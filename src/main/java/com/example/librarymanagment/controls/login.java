package com.example.librarymanagment.controls;

import com.example.librarymanagment.model.JDBC;
import com.example.librarymanagment.model.User;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class login extends Application {
    private static String username;

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");
        User user = new User();

        // Create a BorderPane to hold the content
        BorderPane borderPane = new BorderPane();

        // Create a GridPane to hold the login form
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new javafx.geometry.Insets(10));
        vbox.setAlignment(javafx.geometry.Pos.CENTER);

        // Add a title label
        Label titleLabel = new Label("Login");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");


        // Add a username label and text field
        Label usernameLabel = new Label("Username:");

        TextField usernameTextField = new TextField();
        usernameTextField.setText("taha");


        // Add a password label and password field
        Label passwordLabel = new Label("Password:");

        PasswordField passwordField = new PasswordField();
        passwordField.setText("asd123");


        // Add a login button
        Button loginButton = new Button("Login");
        loginButton.setDefaultButton(true);
        loginButton.setOnAction(e -> {
            int id = JDBC.login(usernameTextField.getText(), passwordField.getText());
            if (id != -1){
                System.out.println("Login successful");
                login.setUserName(usernameTextField.getText());
                bookInfoController.getUserInfo(id, usernameTextField.getText());
                Controller home = new Controller();
                try {
                    home.start(new Stage());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                primaryStage.close();
            } else {
                System.out.println("Login failed");
            }
        });
        Button signUpButton = new Button("Sign Up");
        signUpButton.setOnAction(e -> {
            signup signup = new signup();
            signup.start(new Stage());
            primaryStage.close();
        });

        vbox.getChildren().addAll(titleLabel, usernameLabel, usernameTextField, passwordLabel, passwordField, loginButton, signUpButton);

        // Add the GridPane to the BorderPane
        borderPane.setCenter(vbox);

        // Set the background color of the BorderPane
        borderPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));

        // Create a Scene and show the Stage
        Scene scene = new Scene(borderPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void setUserName(String username) {
        login.username = username;
    }

    public static String getUserName() {
        return username;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

