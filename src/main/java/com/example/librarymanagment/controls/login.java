package com.example.librarymanagment.controls;

import com.example.librarymanagment.model.JDBC;
import com.example.librarymanagment.model.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new javafx.geometry.Insets(10));
        vbox.setAlignment(Pos.CENTER_LEFT);

        // Add a title label
        Label titleLabel = new Label("Login");
        titleLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: bold; -fx-font-family: Poppins Light; -fx-text-fill: #000000;");

        Image logo = new Image("/images/blacklogo.png");
        ImageView logoImageView = new ImageView(logo);
        logoImageView.setFitHeight(100);
        logoImageView.setPreserveRatio(true);
//        logoImageView.setFitWidth(50);


        // Add a username label and text field
        Label usernameLabel = new Label("USERNAME");
        usernameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-font-family: Poppins; -fx-text-fill: #000000;");

        TextField usernameTextField = new TextField();
        usernameTextField.setStyle("-fx-background-color: #ffffff; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000; -fx-border-color: #000000; -fx-border-width: 1px; -fx-font-family: 'Roboto Light'; -fx-border-radius: 2px; -fx-border-insets: 1px; -fx-min-height: 50px;");
        usernameTextField.setText("taha");
        usernameTextField.setMinWidth(350);
        //usernameTextField.setPrefHeight(30);


        // Add a password label and password field
        Label passwordLabel = new Label("PASSWORD");
        passwordLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-font-family: 'Roboto Light'; -fx-text-fill: #000000;");

        PasswordField passwordField = new PasswordField();
        passwordField.setStyle("-fx-background-color: #ffffff; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000; -fx-border-color: #000000; -fx-border-width: 1px; -fx-font-family: 'Roboto Light'; -fx-border-radius: 1px; -fx-border-insets: 1px; -fx-min-height: 50px;");
        passwordField.setText("asd123");
        passwordField.setPrefHeight(30);


        ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList( "User" , "Admin"));
        comboBox.getSelectionModel().selectFirst();
        comboBox.setStyle("-fx-background-color: #ffffff; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #2d0e0e; -fx-border-color: #000000; -fx-border-width: 1px;");
        final String[] selectedValue = {comboBox.getValue()};

        comboBox.setOnAction(e -> {
            selectedValue[0] = comboBox.getValue();
            System.out.println(selectedValue[0]);
        });

        // Add a login button
        Button loginButton = new Button("Login");
        loginButton.setMinWidth(100);
        Button forgotPasswordButton = new Button("Forgot Password?");
        forgotPasswordButton.setStyle("-fx-background-color: TRANSPARENT; -fx-text-fill: #000000; -fx-pref-width: 120; -fx-font-style: italic; -fx-underline: true;-fx-border-color: TRANSPARENT; ");
        forgotPasswordButton.setPadding(new Insets(0, 0, 0, 0));
        forgotPasswordButton.setMaxHeight(30);
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
        loginBox.setPadding(new javafx.geometry.Insets(20));
        loginBox.setAlignment(Pos.CENTER);
        loginBox.getChildren().addAll(loginButton);

        Label registerLabel = new Label("");
        registerLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-font-family: 'Roboto Light'; -fx-text-fill: #ff0000;");
        registerLabel.setAlignment(Pos.CENTER);

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
                    registerLabel.setText("Username or password is incorrect");
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
                    registerLabel.setText("Username or password is incorrect");
                }
            }

        });
        HBox hbox1 = new HBox();
        Button signUpButton = new Button("Not Registred? Sign Up");
        signUpButton.setStyle("-fx-background-color: TRANSPARENT; -fx-text-fill: #000000; -fx-pref-width: 150; -fx-font-style: italic; -fx-underline: true; -fx-border-color: TRANSPARENT;");
        signUpButton.setMaxHeight(20);

        //signUpButton.setAlignment(Pos.CENTER);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().addAll(signUpButton);
        signUpButton.setOnAction(e -> {
            signup signup = new signup();
            signup.start(new Stage());
            primaryStage.close();
        });

        VBox vBox = new VBox(logoImageView);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new javafx.geometry.Insets(0, 0, 50, 0));

        HBox hbox2 = new HBox();
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(registerLabel);


        vbox.getChildren().addAll( vBox, comboBox, usernameLabel, usernameTextField, passwordLabel, passwordField,forgotPasswordButton,hbox2, loginBox,hbox1);
        //HBox.setHgrow(vbox, Priority.ALWAYS);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(vbox);
        hbox.setPrefWidth(350);



       // borderPane.setTop(vBox);
        borderPane.setCenter(hbox);

        borderPane.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));


        Image image = new Image("/images/bg22.png");

        // Create the background image
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );

        // Create the background
        Background background = new Background(backgroundImage);

        // Set the background to the root layout
        borderPane.setBackground(background);


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

