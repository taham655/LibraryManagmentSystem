package com.example.librarymanagment.controls;

import com.example.librarymanagment.model.JDBC;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class forgotPass extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Forgot Password");
        BorderPane borderPane = new BorderPane();

        Label errorLabel = new Label();

        Label titleLabel = new Label("Forgot Password");

        Label usernameLabel = new Label("Username");
        TextField usernameTextField = new TextField();
        usernameTextField.setPrefHeight(30);
        usernameTextField.setMaxWidth(200);
        usernameTextField.setPromptText("Enter your username"); // add prompt text

        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(30);
        passwordField.setMaxWidth(200);
        passwordField.setPromptText("Enter your password"); // add prompt text

        Label confirmPasswordLabel = new Label("Confirm Password");
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPrefHeight(30);
        confirmPasswordField.setMaxWidth(200);
        confirmPasswordField.setPromptText("Confirm your password"); // add prompt text

        VBox vbox1 = new VBox(25);
        vbox1.getChildren().addAll(usernameLabel, passwordLabel, confirmPasswordLabel);
        vbox1.setAlignment(Pos.CENTER_RIGHT);

        VBox vbox2 = new VBox(8);
        vbox2.getChildren().addAll(usernameTextField, passwordField, confirmPasswordField);

        HBox hbox = new HBox(10);

        hbox.getChildren().addAll(vbox1, vbox2);
        hbox.setAlignment(Pos.CENTER);







        Button submitButton = new Button("Submit");

        submitButton.setOnAction(e ->{
            if(!JDBC.checkUser(usernameTextField.getText())){
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("User does not exist");
            } else if (passwordField.getText().equals(confirmPasswordField.getText())){
                JDBC.forgotPassword(usernameTextField.getText(), passwordField.getText());
                errorLabel.setTextFill(Color.GREEN);
                errorLabel.setText("Password updated successfully");

            } else {
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Passwords do not match");
            }
        });

        Button nvm = new Button("nvm sorry");
        //aldMemberButton.setDefaultButton(true);
        nvm.setStyle("-fx-background-color: TRANSPARENT; -fx-text-fill: #1531bb; -fx-font-style: italic; -fx-underline: true;");
        nvm.setOnAction(e -> {
            login login = new login();
            login.start(primaryStage);
        });
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(submitButton, nvm);



        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(titleLabel, hbox, errorLabel, buttonBox);
        vBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBox);


        Scene scene = new Scene(borderPane, 500, 500);
        scene.getStylesheets().add(getClass().getResource("/CSS/signup.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
