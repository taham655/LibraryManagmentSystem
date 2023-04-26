package com.example.librarymanagment.controls;

import com.example.librarymanagment.model.JDBC;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class signup extends Application {
        public void start(Stage primaryStage) {
                primaryStage.setTitle("Sign Up");

                // Create UI elements
                Label titleLabel = new Label("Sign Up");
                titleLabel.getStyleClass().add("title"); // add the "title" style class

                TextField usernameTextField = new TextField();
                usernameTextField.getStyleClass().add("form"); // add the "form" style class
                usernameTextField.setPromptText("Enter your username"); // add prompt text

                PasswordField passwordField = new PasswordField();
                passwordField.getStyleClass().add("form"); // add the "form" style class

                passwordField.setPromptText("Enter your password"); // add prompt text
                PasswordField confirmPasswordField = new PasswordField();
                confirmPasswordField.getStyleClass().add("form"); // add the "form" style class
                confirmPasswordField.setPromptText("Confirm your password"); // add prompt text
                Button signUpButton = new Button("Sign Up");
                signUpButton.getStyleClass().add("form"); // add the "form" style class
                signUpButton.setDefaultButton(true);

                // Set action for the sign-up button
                signUpButton.setOnAction(e -> {
                        JDBC.signUp(usernameTextField.getText(), passwordField.getText(), confirmPasswordField.getText());
                        login login = new login();
                        login.start(primaryStage);
                });

                // Add UI elements to the layout
                VBox mainVBox = new VBox();
                mainVBox.setAlignment(Pos.CENTER);
                mainVBox.setSpacing(20);
                mainVBox.getChildren().addAll(
                        titleLabel,
                        usernameTextField,
                        passwordField,
                        confirmPasswordField,
                        signUpButton
                );

                BorderPane borderPane = new BorderPane(mainVBox);
                borderPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));

                // Load the CSS file and apply it to the scene
                Scene scene = new Scene(borderPane, 600, 400);
                scene.getStylesheets().add(getClass().getResource("/CSS/signup.css").toExternalForm());
                // Show the scene
                primaryStage.setScene(scene);
                primaryStage.show();
        }



        public static void main(String[] args) {
            launch(args);
        }
    }


