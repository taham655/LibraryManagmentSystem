package com.example.librarymanagment.controls;

import com.example.librarymanagment.model.JDBC;
import com.example.librarymanagment.model.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class login extends Application {
    private static String username;

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");
        User user = new User();

        // Create a BorderPane to hold the content
        BorderPane borderPane = new BorderPane();
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new javafx.geometry.Insets(10));
        vbox.setAlignment(Pos.CENTER_LEFT);

        // Add a title label
        Label titleLabel = new Label("Login");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");


        // Add a username label and text field
        Label usernameLabel = new Label("Username:");

        TextField usernameTextField = new TextField();
        usernameTextField.setText("taha");
        usernameTextField.setPrefHeight(30);


        // Add a password label and password field
        Label passwordLabel = new Label("Password:");

        PasswordField passwordField = new PasswordField();
        passwordField.setText("asd123");
        passwordField.setPrefHeight(30);


        ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList( "User" , "Admin"));
        comboBox.getSelectionModel().selectFirst();
        comboBox.setStyle("-fx-background-color: #ffffff; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #2d0e0e;");
        final String[] selectedValue = {comboBox.getValue()};

        comboBox.setOnAction(e -> {
            selectedValue[0] = comboBox.getValue();
            System.out.println(selectedValue[0]);
        });

        // Add a login button
        Button loginButton = new Button("Login");
        loginButton.setMinWidth(100);
        Button forgotPasswordButton = new Button("Forgot Password?");
        forgotPasswordButton.setStyle("-fx-background-color: TRANSPARENT; -fx-text-fill: #1531bb; -fx-pref-width: 150; -fx-font-style: italic; -fx-underline: true;");
        forgotPasswordButton.setOnAction(e -> {
            forgotPass forgotPassword = new forgotPass();
            try {
                forgotPassword.start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            primaryStage.close();
        });

        HBox loginBox = new HBox();
        loginBox.setSpacing(110);
        loginBox.getChildren().addAll(loginButton, forgotPasswordButton);

        loginButton.setOnAction(e -> {
            System.out.println(selectedValue[0]);
            if(selectedValue[0].equals("Admin")){
                int id = JDBC.adminLogin(usernameTextField.getText(), passwordField.getText());
                if (id != -1){
                    adminController admin = new adminController();
                    try {
                        admin.start(new Stage());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    primaryStage.close();
                } else {
                    System.out.println("Login failed");
                }
            }
            else if(selectedValue[0].equals("User")){
                int id = JDBC.login(usernameTextField.getText(), passwordField.getText());
                if (id != -1){
                    System.out.println("Login successful");
                    login.setUserName(usernameTextField.getText());
                    bookInfoController.getUserInfo(id, usernameTextField.getText());
                    UserProfile.userID = id;
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
            }

        });
        Button signUpButton = new Button("Sign Up");
        signUpButton.setOnAction(e -> {
            signup signup = new signup();
            signup.start(new Stage());
            primaryStage.close();
        });

        vbox.getChildren().addAll(comboBox,titleLabel, usernameLabel, usernameTextField, passwordLabel, passwordField, loginBox, signUpButton);
        HBox.setHgrow(vbox, Priority.ALWAYS);
        hbox.getChildren().addAll(vbox);
        hbox.setPrefWidth(350);

        borderPane.setRight(hbox);

        Image backgroundImage = new Image("/images/bg2.png");

        // Create the background image
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        // Set the background image to the root pane
        borderPane.setBackground(new Background(background));


        // Create a Scene and show the Stage
        Scene scene = new Scene(borderPane, 1315, 890);
        scene.getStylesheets().add(getClass().getResource("/CSS/login.css").toExternalForm());
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

