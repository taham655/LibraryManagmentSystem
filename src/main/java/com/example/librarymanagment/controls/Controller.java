package com.example.librarymanagment.controls;

import com.example.librarymanagment.model.Utils;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller extends Application{

        public void start(Stage primaryStage) throws IOException {
                BorderPane borderPane = new BorderPane();


                Image profileImage = new Image("/images/avatar.png");
                ImageView profileImageView = new ImageView(profileImage);
                profileImageView.setFitHeight(20);
                profileImageView.setFitWidth(20);

                Button ProfileButton = new Button("", profileImageView);
                ProfileButton.setStyle("-fx-background-color: TRANSPARENT; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0), 0, 0, 0, 0);");

                Image homeImage = new Image("/images/home.png");
                ImageView homeImageView = new ImageView(homeImage);
                homeImageView.setFitHeight(20);
                homeImageView.setFitWidth(20);



                Button logOut = new Button("Log out");
                logOut.setStyle("-fx-font-family: Verdana; -fx-background-color: #af0303; -fx-text-fill: #ffffff;-fx-effect: dropshadow(gaussian, rgba(0,0,0,0), 0, 0, 0, 0);");

                ProfileButton.setOnAction(e -> {

                        UserProfile userProfile = new UserProfile();
                        try {
                                userProfile.start(new Stage());
                        } catch (Exception ex) {
                                throw new RuntimeException(ex);
                        }
                        primaryStage.close();

                });

                // Create an HBox to hold the navigation buttons on the left

                Image logo = new Image("/images/logo1.png");
                ImageView logoView = new ImageView(logo);
                logoView.setFitHeight(30);
                logoView.setFitWidth(60);
                HBox leftNavBar = new HBox();
                leftNavBar.setPadding(new Insets(10));
                leftNavBar.setSpacing(10);
                leftNavBar.setAlignment(Pos.CENTER_LEFT);
                leftNavBar.setStyle("-fx-background-color: #a38a7c;");
                Button HomeButton = new Button("", logoView);
                HomeButton.setStyle("-fx-background-color: TRANSPARENT;-fx-effect: dropshadow(gaussian, rgba(0,0,0,0), 0, 0, 0, 0);");
                leftNavBar.getChildren().addAll(HomeButton,ProfileButton);

                // Create a StackPane to hold the search bar in the center
                StackPane centerNavBar = new StackPane();
                centerNavBar.setPadding(new Insets(10));
                centerNavBar.setStyle("-fx-background-color: #a38a7c;");
                TextField searchField = new TextField();
                searchField.setPromptText("Search book title...");


                Image searchImage = new Image("/images/search1.png");
                ImageView searchImageView = new ImageView(searchImage);
                searchImageView.setFitHeight(22);
                searchImageView.setFitWidth(22);
                Button searchButton = new Button("", searchImageView);
                searchButton.setStyle("-fx-background-color: TRANSPARENT;-fx-effect: dropshadow(gaussian, rgba(0,0,0,0), 0, 0, 0, 0);");


                FlowPane root = Utils.searchBooks("");
                ScrollPane scrollPane = new ScrollPane();


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
                rightNavBar.setStyle("-fx-background-color: #a38a7c;");

                rightNavBar.getChildren().addAll(searchButton,logOut);

                // Create an HBox to hold the navigation buttons and search bar
                HBox navigationBar = new HBox();
                navigationBar.getChildren().addAll(leftNavBar, centerNavBar, rightNavBar);
                HBox.setHgrow(centerNavBar, javafx.scene.layout.Priority.ALWAYS);
                navigationBar.setAlignment(Pos.CENTER);
                navigationBar.setStyle("-fx-background-color: #1e1e1e;");


                scrollPane.setContent(root);

                HomeButton.setOnAction(e -> {
                        try {scrollPane.setContent(root);
                        } catch (Exception ex) {
                                throw new RuntimeException(ex);
                        }
                });

                scrollPane.setFitToHeight(true);
                scrollPane.setFitToWidth(true);
                scrollPane.setBackground(new Background(new BackgroundFill(Color.rgb(232, 211, 177), CornerRadii.EMPTY, Insets.EMPTY)));
                borderPane.setTop(navigationBar);
                borderPane.setCenter(scrollPane);


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