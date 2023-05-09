package com.example.librarymanagment.controls;

import com.example.librarymanagment.model.JDBC;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class forgotPass extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Forgot Password");
        BorderPane borderPane = new BorderPane();

        Label errorLabel = new Label();

        Label titleLabel = new Label("Forgot Password");
        titleLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: bold; -fx-font-family: Poppins Light; -fx-text-fill: #000000;");
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setPadding(new Insets(0, 0, 50, 0));

        Label usernameLabel = new Label("Username");
        usernameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-font-family: Poppins; -fx-text-fill: #000000;");
        usernameLabel.setAlignment(Pos.CENTER_LEFT);
        TextField usernameTextField = new TextField();
        usernameTextField.setStyle("-fx-background-color: #ffffff; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000; -fx-border-color: #000000; -fx-border-width: 1px; -fx-font-family: 'Roboto Light'; -fx-border-radius: 2px; -fx-border-insets: 1px; -fx-min-height: 50px;");
        usernameTextField.setPrefHeight(30);
        usernameTextField.setMaxWidth(350);
        usernameTextField.setPromptText("Enter your username"); // add prompt text

        Button submitButton = new Button("Submit");
        submitButton.setMinWidth(200);
        submitButton.setMaxHeight(40);

        submitButton.setOnAction(e ->{
            if(!JDBC.checkUser(usernameTextField.getText())){
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("User does not exist");

            } else {
//                String email = JDBC.getEmail(usernameTextField.getText());
                changePass.setStage(usernameTextField.getText());
                primaryStage.close();
            }
        });



        VBox vbox = new VBox(8);
        vbox.getChildren().addAll(titleLabel, usernameLabel, usernameTextField,errorLabel);

        vbox.setAlignment(Pos.CENTER_LEFT);

        Button nvm = new Button("Go Back");
        //aldMemberButton.setDefaultButton(true);
        nvm.setStyle("-fx-background-color: TRANSPARENT; -fx-text-fill: #000000; -fx-font-style: italic; -fx-underline: true; -fx-border-color: TRANSPARENT; -fx-font-size: 12px; -fx-font-weight: bold; -fx-font-family: Poppins;");
        nvm.setOnAction(e -> {
            login login = new login();
            login.start(primaryStage);
        });
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(submitButton);



        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(titleLabel, vbox, errorLabel, buttonBox, nvm);
        vBox.setAlignment(Pos.CENTER);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(vBox);
        hBox.setAlignment(Pos.CENTER);


        borderPane.setCenter(hBox);

        borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));


        Scene scene = new Scene(borderPane, 1315, 890);
        scene.getStylesheets().add(getClass().getResource("/CSS/signup.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
