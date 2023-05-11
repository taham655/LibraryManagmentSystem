package com.example.librarymanagment.controls;

import com.example.librarymanagment.model.EMAIL;
import com.example.librarymanagment.model.JDBC;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class changePass extends Application {
    static boolean isUser;

    public static void setStage(String username){

        Stage stage = new Stage();

        Random random = new Random();

        int randomNumber = random.nextInt(9000) + 1000;

        BorderPane borderPane = new BorderPane();
        System.out.println(isUser);
        String emails;

        if (isUser){
             emails = JDBC.getUserEmail(username);
            System.out.println(emails);
             password.adminUser(true);
        } else {
             emails = JDBC.getAdminEmail(username);
            System.out.println(emails);
             password.adminUser(false);
        }

        //System.out.println(EMAIL.email);
        EMAIL.email(emails, "Password Recovery", "Your code is: " +  randomNumber + "\n\nIf you did not request this change, please ignore this email.");


        Label titleLabel = new Label("Account Recovery");
        titleLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: bold; -fx-font-family: Poppins Light; -fx-text-fill: #000000;");
        titleLabel.setAlignment(Pos.CENTER);

        Label explain = new Label("An email has been sent to " + emails + " with a link to change your password");
        explain.setStyle("-fx-font-size: 14px; -fx-font-family: 'Roboto Light'; -fx-text-fill: #000000;");
        explain.setPadding(new Insets(10));

        VBox codeBox = new VBox();
        codeBox.setSpacing(10);
        codeBox.setAlignment(Pos.CENTER);

        Label codeLabel = new Label("Enter the code sent to your email");
        codeLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-font-family: Poppins; -fx-text-fill: #000000;");

        TextField codeTextField = new TextField();
        codeTextField.setStyle("-fx-background-color: #ffffff; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000; -fx-border-color: #000000; -fx-border-width: 1px; -fx-font-family: 'Roboto Light'; -fx-border-radius: 2px; -fx-border-insets: 1px; -fx-min-height: 50px;");
        codeTextField.setMaxWidth(500);
        codeTextField.setPromptText("Enter code");

        codeBox.getChildren().addAll(codeLabel, codeTextField);

        Button submitButton = new Button("Submit");
        submitButton.setMinWidth(200);
        submitButton.setMaxHeight(40);
        submitButton.setStyle("-fx-background-color: #000000; -fx-text-fill: #ffffff; -fx-font-size: 16px; -fx-font-weight: bold; -fx-font-family: 'Roboto Light';");
        Label errorLabel = new Label("");
        submitButton.setOnAction(e ->{
            if (codeTextField.getText().equals(Integer.toString(randomNumber))){
                password.user(username);
                password password = new password();
                try {
                    password.start(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            } else {
                errorLabel.setStyle("-fx-text-fill: #ff0000;");
                errorLabel.setText("Incorrect code");
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

        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new javafx.geometry.Insets(50));
        vBox.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(titleLabel,explain, codeBox, submitButton , gobackButton, errorLabel);

        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane, 1315, 890);
        scene.getStylesheets().add(changePass.class.getResource("/CSS/signup.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void adminUser(boolean isUsers){
        isUser = isUsers;
    }
    @Override
    public void start(Stage stage) throws Exception {

    }
}
