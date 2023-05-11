package com.example.librarymanagment.controls;

import com.example.librarymanagment.model.JDBC;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class password extends Application {
    static String user = "";
    static boolean isUser;
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = new BorderPane();
        System.out.println("Came here");

        VBox vBox = new VBox();


        Label titleLabel = new Label("Change Password");
        titleLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: bold; -fx-font-family: Poppins Light; -fx-text-fill: #000000;");
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setPadding(new Insets(0, 0, 50, 0));


        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password"); // add prompt text
        passwordField.setStyle("-fx-background-color: #ffffff; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000; -fx-border-color: #000000; -fx-border-width: 1px; -fx-font-family: 'Roboto Light'; -fx-border-radius: 2px; -fx-border-insets: 1px; -fx-min-height: 50px;");
        passwordField.setMaxWidth(400);
        Label passwordLabel = new Label("Password:");


        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm password"); // add prompt text
        confirmPasswordField.setStyle("-fx-background-color: #ffffff; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000; -fx-border-color: #000000; -fx-border-width: 1px; -fx-font-family: 'Roboto Light'; -fx-border-radius: 2px; -fx-border-insets: 1px; -fx-min-height: 50px;");
        confirmPasswordField.setMaxWidth(400);
        Label confirmPasswordLabel = new Label("Confirm Password:");

        passwordLabel.setAlignment(Pos.CENTER_LEFT);
        confirmPasswordLabel.setAlignment(Pos.CENTER_LEFT);


        Label errorLabel = new Label();

        Button submitButton = new Button("Submit");
        submitButton.setMinWidth(200);
        submitButton.setMaxHeight(40);

        submitButton.setOnAction(e ->{
            if(passwordField.getText().equals(confirmPasswordField.getText())){
                if (isUser){
                    JDBC.updateUserPassword(user, passwordField.getText());
                } else {
                    JDBC.updateAdminPassword(user, passwordField.getText());
                }

                login login = new login();
                try {
                    login.start(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else {
                errorLabel.setText("Passwords do not match");
                errorLabel.setStyle("-fx-text-fill: #ff0000;");
            }
        });

        Button gobackButton = new Button("Cancel");
        gobackButton.setStyle("-fx-background-color: TRANSPARENT; -fx-text-fill: #000000; -fx-pref-width: 150; -fx-font-style: italic; -fx-underline: true; -fx-border-color: TRANSPARENT;");
        gobackButton.setMaxHeight(20);
        gobackButton.setOnAction(e ->{
            login login = new login();
            try {
                login.start(stage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });



        vBox.setSpacing(5);
        //vBox.setMaxWidth(300);
        vBox.setAlignment(Pos.CENTER_LEFT);

        vBox.getChildren().addAll(titleLabel,passwordLabel, passwordField, confirmPasswordLabel, confirmPasswordField);
        VBox vBox1 = new VBox();
        vBox1.setSpacing(10);
        vBox1.setAlignment(Pos.CENTER);
        vBox1.getChildren().addAll(vBox,  errorLabel , submitButton , gobackButton );


        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(0, 0, 0, 0));
        hBox.getChildren().addAll(vBox1);

        borderPane.setCenter(hBox);

        Scene scene = new Scene(borderPane, 1315, 890);
        scene.getStylesheets().add(getClass().getResource("/CSS/signup.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
    public static void adminUser(boolean isUsers){
        isUser = isUsers;
    }


    public static void user(String userid){
        user = userid;
    }
}
