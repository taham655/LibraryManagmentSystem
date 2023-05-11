package com.example.librarymanagment.controls;

import com.example.librarymanagment.model.EMAIL;
import com.example.librarymanagment.model.JDBC;
import com.example.librarymanagment.model.User;
import com.example.librarymanagment.model.Utils;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;


public class signup extends Application {

        List<User> users = JDBC.getUserData();
        public void start(Stage primaryStage) {
                primaryStage.setTitle("Sign Up");

                // Create UI elements
                Label titleLabel = new Label("Sign Up");
                titleLabel.setStyle("-fx-font-size: 200px;-fx-font-family: Poppins; -fx-text-fill: #000000;");
                titleLabel.setAlignment(Pos.CENTER);
                //titleLabel.getStyleClass().add("title"); // add the "title" style class

                Label errorLabel = new Label();
                errorLabel.setPadding(new Insets(0, 0, 10, 0));
                errorLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13px; ");
                //errorLabel.setTextFill(Color.RED);

                TextField usernameTextField = new TextField();
                usernameTextField.setPromptText("Enter your username"); // add prompt text
                usernameTextField.setStyle("-fx-background-color: #ffffff; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000; -fx-border-color: #000000; -fx-border-width: 1px; -fx-font-family: 'Roboto Light'; -fx-border-radius: 2px; -fx-border-insets: 1px; -fx-min-height: 50px;");
                Label usernameLabel = new Label("Username:");
                usernameTextField.setMinWidth(350);
                //usernameLabel.setPadding(new Insets(0, 10, 0, 0));



                TextField nameTextField = new TextField();
                nameTextField.setPromptText("Enter your name"); // add prompt text
                nameTextField.setStyle("-fx-background-color: #ffffff; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000; -fx-border-color: #000000; -fx-border-width: 1px; -fx-font-family: 'Roboto Light'; -fx-border-radius: 2px; -fx-border-insets: 1px; -fx-min-height: 50px;");
                Label nameLabel = new Label("Name:");
                //nameLabel.setPadding(new Insets(0, 57, 0, 0));
                nameTextField.setPromptText("Enter your name");



                TextField phoneTextField = new TextField();
                Label phoneLabel = new Label("Email:");
                phoneTextField.setStyle("-fx-background-color: #ffffff; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000; -fx-border-color: #000000; -fx-border-width: 1px; -fx-font-family: 'Roboto Light'; -fx-border-radius: 2px; -fx-border-insets: 1px; -fx-min-height: 50px;");

                phoneTextField.setPromptText("Enter your email address");


                PasswordField passwordField = new PasswordField();
                passwordField.setPromptText("Enter your password"); // add prompt text
                passwordField.setStyle("-fx-background-color: #ffffff; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000; -fx-border-color: #000000; -fx-border-width: 1px; -fx-font-family: 'Roboto Light'; -fx-border-radius: 2px; -fx-border-insets: 1px; -fx-min-height: 50px;");
                Label passwordLabel = new Label("Password:");


                PasswordField confirmPasswordField = new PasswordField();
                confirmPasswordField.setPromptText("Confirm password"); // add prompt text
                confirmPasswordField.setStyle("-fx-background-color: #ffffff; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000; -fx-border-color: #000000; -fx-border-width: 1px; -fx-font-family: 'Roboto Light'; -fx-border-radius: 2px; -fx-border-insets: 1px; -fx-min-height: 50px;");
                Label confirmPasswordLabel = new Label("Confirm Password:");



                Button signUpButton = new Button("Sign Up");
                signUpButton.setDefaultButton(true);

                Button aldMemberButton = new Button("Already Registered? Login");
                //aldMemberButton.setDefaultButton(true);
                aldMemberButton.setStyle("-fx-background-color: TRANSPARENT; -fx-text-fill: #000000; -fx-pref-width: 150; -fx-font-style: italic; -fx-underline: true; -fx-border-color: TRANSPARENT;");
                aldMemberButton.setMinWidth(250);
                aldMemberButton.setOnAction(e -> {

                        login login = new login();
                        login.start(primaryStage);
                });

                HBox buttonBox = new HBox();
                buttonBox.setAlignment(Pos.CENTER);
                buttonBox.getChildren().add(signUpButton);

                // Set action for the sign-up button
                signUpButton.setOnAction(e -> {
                        if (usernameTextField.getText().isEmpty() || passwordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty() || nameTextField.getText().isEmpty() || phoneTextField.getText().isEmpty()) {
                                errorLabel.setText("Please fill all the fields");
                                errorLabel.setTextFill(Color.RED);
                        } else if (!Objects.equals(passwordField.getText(), confirmPasswordField.getText())) {
                                errorLabel.setText("Passwords do not match");
                                errorLabel.setTextFill(Color.RED);
                        } else if(JDBC.checkUser(usernameTextField.getText())){
                                errorLabel.setText("Username already exists");
                                errorLabel.setTextFill(Color.RED);

                        } else if(JDBC.checkUserEmail(phoneTextField.getText())){
                                errorLabel.setText("Email already exists");
                                errorLabel.setTextFill(Color.RED);
                        } else if (!Utils.isValidEmailAddress(phoneTextField.getText())) {
                                errorLabel.setText("Invalid Email");
                                errorLabel.setTextFill(Color.RED);
                        } else {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation Dialog");
                                alert.setHeaderText("Are you sure you want to sign up?");
                                alert.setContentText("Click OK to continue");
                                if (alert.showAndWait().get() == ButtonType.OK){
                                        JDBC.signUp(usernameTextField.getText(), passwordField.getText(), confirmPasswordField.getText(), nameTextField.getText(), phoneTextField.getText());
                                        login login = new login();
                                        EMAIL.email(phoneTextField.getText(), "Welcome to the Kutab Khana", "Welcome to the Kutab Khana Library, " + nameTextField.getText() +"\n"+  "\nWe would like to extend a warm welcome to you as a new member of our library! \n" +
                                                "We are thrilled to have you join our community of learners and readers, and we look forward to providing you with an enriching experience.\n\n" +
                                                "Our library has a wide selection of books, ranging from popular fiction and non-fiction titles to scholarly works and research materials. \n" +
                                                "We hope that you will take advantage of everything our library has to offer, and we look forward to seeing you soon. \n" +
                                                "If you have any questions or need any assistance, please do not hesitate to contact us.\n\n" +
                                                "Thank you for choosing our library as your go-to place for knowledge and entertainment. \n" +
                                                "We can't wait to get to know you better and to help you achieve your goals.\n\n" + "Regards,\n" + "Kutab Khana Team");
                                        login.start(primaryStage);
                                }

                        }

                });



                VBox v2 = new VBox();
                v2.setSpacing(10);
                v2.getChildren().addAll(usernameLabel, usernameTextField, passwordLabel, passwordField, confirmPasswordLabel, confirmPasswordField, nameLabel, nameTextField, phoneLabel, phoneTextField);

                // Create VBox to hold form elements
                HBox formBox = new HBox(10);
                formBox.setAlignment(Pos.CENTER);
                formBox.setPadding(new Insets(10));
                formBox.getChildren().addAll(v2);

                VBox main = new VBox();
                main.setAlignment(Pos.CENTER);
                main.setPadding(new Insets(10));

                titleLabel.setPadding(new Insets(10, 0, 50, 0));
                titleLabel.setStyle("-fx-font-size: 30; -fx-font-weight: bold;");
                main.getChildren().addAll(titleLabel,aldMemberButton,  formBox, errorLabel, buttonBox);

                BorderPane borderPane = new BorderPane(main);
                //borderPane.setTop(titleLabel);
                borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

                // Load the CSS file and apply it to the scene
                Scene scene = new Scene(borderPane, 1315, 890);
                scene.getStylesheets().add(getClass().getResource("/CSS/signup.css").toExternalForm());

                // Show the scene
                primaryStage.setScene(scene);
                primaryStage.setResizable(false);
                primaryStage.show();
        }








        public static void main(String[] args) {
            launch(args);
        }
    }

