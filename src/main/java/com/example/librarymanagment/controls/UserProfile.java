package com.example.librarymanagment.controls;

import com.example.librarymanagment.model.JDBC;
import com.example.librarymanagment.model.borrow;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class UserProfile extends Application {

    static int userID;


    @Override
    public void start(Stage primaryStage) throws Exception {
        double debt = 0.0;
        ArrayList<Integer> removing = new ArrayList<>();

        BorderPane layout = new BorderPane();


        Image logo = new Image("/images/logowhite.png");
        ImageView logoView = new ImageView(logo);
        logoView.setFitHeight(20);
        logoView.setFitWidth(60);


        Button goBack = new Button("", logoView);
        goBack.setStyle("-fx-background-color: TRANSPARENT;-fx-effect: dropshadow(gaussian, rgba(0,0,0,0), 0, 0, 0, 0);");
        //goBack.setStyle("-fx-font-family: Verdana; -fx-background-color: #3a2c23; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-text-fill: #ffffff;");
        goBack.setOnAction(e -> {
            try {
                new Controller().start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            primaryStage.close();
        });

        Button logOut = new Button("Log out");
        logOut.setStyle("-fx-font-family: Verdana; -fx-background-color: #af0303; -fx-text-fill: #ffffff;");
        logOut.setOnAction(e -> {
            try {
                new login().start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            primaryStage.close();
        });

        HBox navBar = new HBox();

        HBox LogoutBox = new HBox(10, logOut);
        LogoutBox.setAlignment(Pos.CENTER_RIGHT);
        LogoutBox.setPadding(new Insets(10, 10,10,1130));

        HBox goBackBox = new HBox(10, goBack);
        goBackBox.setAlignment(Pos.CENTER_LEFT);
        goBackBox.setPadding(new Insets(10));
        navBar.getChildren().addAll(goBackBox, LogoutBox);

        navBar.setStyle("-fx-background-color: #000000;-fx-effect: dropshadow(gaussian, rgba(70,68,68,0.3), 8, 0.4, 0, 0.1)");


        layout.setTop(navBar);
        List<borrow> borrows = JDBC.getBorrowed(userID);



        Image userImage = new Image("/images/programmer.png");

        ImageView imageView = new ImageView(userImage);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        VBox bookBox = new VBox(10);
        bookBox.setPadding(new Insets(10));

        Image avatar = new Image("/images/avatar.png");
        ImageView avatarView = new ImageView(avatar);
        avatarView.setFitHeight(100);
        avatarView.setFitWidth(100);
        Label avatarLabel = new Label("Avatar");

        VBox avatarBox = new VBox(10);
        avatarBox.setPadding(new Insets(10));
        avatarBox.getChildren().addAll(avatarView, avatarLabel);



        if (borrows.size() > 0) {

            for (borrow borrow : borrows) {

                // Create labels for book information
                Label bookIdLabel = new Label("Book ID: " + borrow.getBook_id());
                Label bookNameLabel = new Label("Book Name: " + borrow.getTitle());
                Label daysLeftLabel = new Label("Days Left to Return: " + borrow.getDay_left());


                bookIdLabel.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-padding: 0 0 0 10; -fx-font-family: Verdana; -fx-text-fill: #ffffff;");
                bookNameLabel.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-padding: 0 0 0 10; -fx-font-family: Verdana; -fx-text-fill: #ffffff;");
                daysLeftLabel.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-padding: 0 0 0 10; -fx-font-family: Verdana; -fx-text-fill: #ffffff;");


                // Create a button to return the book
                Button returnButton = new Button("Return Book");
                returnButton.setMinWidth(100);
                if (borrow.getDay_left() < 0) {
                    returnButton.setText("Pay Debt");
                    returnButton.setStyle("-fx-background-color: #831717; -fx-text-fill: #ffffff");
                } else{
                    returnButton.setStyle("-fx-background-color: #204f24; -fx-text-fill: #ffffff");
                }
                //returnButton.setStyle("-fx-font-family: Verdana; -fx-background-color: #3a2c23; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-text-fill: #ffffff;");
               if (borrow.getDay_left() < 0) {
                   returnButton.setOnAction(e -> {
                       Stage debtStage = new Stage();
                          debtStage.setTitle("Pay Debt");
                            debtStage.setResizable(false);
                            debtStage.initModality(Modality.APPLICATION_MODAL);
                            Image debtImage = new Image("/images/qrcode.jpeg");
                            ImageView debtView = new ImageView(debtImage);

                            VBox debtBox = new VBox(10);
                            debtBox.setPadding(new Insets(10));
                            debtView.setFitHeight(300);
                            debtView.setFitWidth(300);
                            Button payButton = new Button("Pay");

                            //VBox debtButtonBox = new VBox(10);
                            debtBox.getChildren().addAll(debtView,payButton);
                            debtBox.setAlignment(Pos.CENTER);
                            debtBox.setSpacing(10);
                            payButton.setStyle("-fx-font-family: Verdana; -fx-background-color: #3a2c23; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-text-fill: #ffffff;");
                            Scene debtScene = new Scene(debtBox, 300, 400);
                            payButton.setOnAction(e1 -> {
                                JDBC.returnBook(borrow.getBorrow_id());
                                JDBC.updateAvailable(borrow.getBook_id(), "return");
                                try {
                                    start(primaryStage);
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                                debtStage.close();
                            });

                            debtStage.setScene(debtScene);
                            debtStage.show();

                   });
               } else {

                   returnButton.setOnAction(e -> {
                       JDBC.returnBook(borrow.getBorrow_id());
                       JDBC.updateAvailable(borrow.getBook_id(), "return");
                       System.out.println("Book returned");
                       try {
                           start(primaryStage);
                       } catch (Exception exception) {
                           exception.printStackTrace();
                       }
                   });
               }

                if(borrow.getDay_left() < 0){
                    debt += (-1 *borrow.getDay_left());
                    removing.add(borrow.getBook_id());
                }

                // Create a layout pane for book information
                HBox bookInfoBox = new HBox();
                bookInfoBox.setPadding(new Insets(10));
                bookInfoBox.setStyle("-fx-background-color: #000000; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 8, 0.4, 0, 1);");
                bookInfoBox.setPrefWidth(1500);
                bookInfoBox.setPrefHeight(30);
                bookInfoBox.setAlignment(Pos.CENTER);

                Region spacer1 = new Region();
                Region spacer2 = new Region();
                Region spacer3 = new Region();

                // Set preferred width for spacers
                spacer1.setPrefWidth(Region.USE_PREF_SIZE);
                spacer2.setPrefWidth(Region.USE_PREF_SIZE);
                spacer3.setPrefWidth(Region.USE_PREF_SIZE);

                // Set HGrow for spacers
                HBox.setHgrow(spacer1, Priority.ALWAYS);
                HBox.setHgrow(spacer2, Priority.ALWAYS);
                HBox.setHgrow(spacer3, Priority.ALWAYS);


                bookInfoBox.getChildren().addAll(bookIdLabel, spacer1, bookNameLabel, spacer2, daysLeftLabel, spacer3, returnButton);

                // Create a layout pane for the book rectangle and information
                HBox bookContainer = new HBox(10);

                bookContainer.getChildren().addAll(bookInfoBox);
                //Making the fine system

                // Add the book container to the book box
                bookBox.getChildren().add(bookContainer);

            }
        } else {
            Label noBookLabel = new Label("No books borrowed");
            bookBox.getChildren().add(noBookLabel);
            bookBox.setAlignment(Pos.CENTER);
        }


        if (debt != JDBC.getbalance(userID)){
            JDBC.updateBalance(userID, debt);
        }


        VBox fineBox = new VBox(10);
        fineBox.setPadding(new Insets(10));

            StackPane root = new StackPane();
            root.setPadding(new Insets(10));

            // Create a Rectangle to represent the card
            Rectangle card = new Rectangle(200, 100);
            card.setFill(Color.WHITE);
            card.setArcWidth(10);
            card.setArcHeight(10);
            card.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 8, 0.4, 0, 1);");
            root.getChildren().add(card);

            // Create a Text node to display the balance
            Text balanceText = new Text("$ " + debt); // Replace with the actual balance value
            balanceText.setFont(Font.font("Arial", 30));
            balanceText.setStyle("-fx-font-weight: bold; -fx-fill: #ffffff;");
            root.getChildren().add(balanceText);

            // Set the color of the card based on whether there is an outstanding balance or not
            if (debt > 0) { // Replace with the logic to check if there is an outstanding balance
                card.setFill(Color.RED);
            } else {
                card.setFill(Color.GREEN);

            }

            Label fineLabel = new Label("Outstanding Balance: ");
            fineLabel.setStyle("-fx-font-family: Verdana; -fx-font-size: 15px; -fx-text-fill: #000000; -fx-font-weight: bold;");
            //payFine.setMinWidth(150);
            fineBox.getChildren().addAll(fineLabel, root);
            fineBox.setAlignment(Pos.CENTER);

            VBox totalBox = new VBox(5);
            totalBox.getChildren().addAll(fineBox, bookBox);

            layout.setCenter(totalBox);

            Label warningLabel = new Label("Warning: You will be charged $1 for each day you are late to return the book");
            warningLabel.setStyle("-fx-font-family: Verdana; -fx-font-size: 12px; -fx-text-fill: #8c0303; -fx-font-weight: bold;");
            warningLabel.setPadding(new Insets(10));
            warningLabel.setAlignment(Pos.CENTER);
            layout.setBottom(warningLabel);


            //layout.setBackground(new Background(new BackgroundFill(Color.rgb(255, 241, 221), CornerRadii.EMPTY, Insets.EMPTY)));

            // Create the scene
            Scene scene = new Scene(layout, 1315, 890);

            scene.getStylesheets().add(getClass().getResource("/CSS/userProfile.css").toExternalForm());

            // Set the scene and show the stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("User Profile");
            primaryStage.show();
        }
    }


