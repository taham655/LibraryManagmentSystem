package com.example.librarymanagment.controls;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class bookInfoController extends Application {
    public void setStage(Stage stage, String title, String author, String desc, Image image, Double rating, String genre ) throws Exception {
        //book.getBookTitle(), book.getBookAuthor(), book.getBookDescription(), book.getThumbnail(), book.getAvg_rating(),book.getCategories()
//        BorderPane root = new BorderPane();
//        FlowPane flowPane = new FlowPane();
//
//        ImageView imageView = new ImageView(image);
//        imageView.setId("bookImage");
//
//        //Image processing
//        //imageView.setPreserveRatio(true);
//        imageView.setFitWidth(100);
//        imageView.setFitHeight(150);
//        imageView.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px;");
//        //Image processing end
//
//        Label titleLabel = new Label(title);
//        Label authorLabel = new Label(author);
//        Label descLabel = new Label(desc);
//        Label ratingLabel = new Label(rating.toString());
//        VBox box = new VBox();
//        box.setAlignment(Pos.CENTER);
//        box.getChildren().addAll(imageView, titleLabel, authorLabel, ratingLabel);
//
//        //flowPane.getChildren().addAll(box , descLabel);
//        //flowPane.setAlignment(Pos.CENTER);
//        root.setLeft(box);
//        root.setCenter(descLabel);
//        Scene scene = new Scene(root, 600  , 400);
//        titleLabel.setStyle("-fx-text-fill: black;");
//        stage.setTitle("Book Info");
//        scene.getStylesheets().add(getClass().getResource("/CSS/bookInfo.css").toExternalForm());
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Book Info");
        stage.setScene(scene);

        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(150);
        imageView.setFitHeight(200);

        Label titleLabel = new Label(title);
        titleLabel.setId("titleLabel");
        titleLabel.setStyle("-fx-text-fill: black; -fx-font-size: 18px; -fx-font-weight: bold;");

        Label authorLabel = new Label(author);
        authorLabel.setId("authorLabel");
        authorLabel.setStyle("-fx-text-fill: black; -fx-font-size: 16px;");

        Label ratingLabel = new Label(rating.toString());
        ratingLabel.setId("ratingLabel");
        ratingLabel.setStyle("-fx-text-fill: black; -fx-font-size: 14px;");

        Button borrowButton = new Button("Borrow");
        borrowButton.setId("borrowButton");
        borrowButton.setOnAction(e -> borrowBook());

        Button returnButton = new Button("Return");
        returnButton.setId("returnButton");
        returnButton.setOnAction(e -> returnBook());

        VBox bookInfoBox = new VBox();
        bookInfoBox.setSpacing(10);
        bookInfoBox.setAlignment(Pos.CENTER_LEFT);
        bookInfoBox.getChildren().addAll(titleLabel, authorLabel, ratingLabel, borrowButton, returnButton);

        HBox leftBox = new HBox();
        leftBox.setAlignment(Pos.CENTER_LEFT);
        leftBox.setSpacing(20);
        leftBox.getChildren().addAll(imageView, bookInfoBox);

        Label descLabel = new Label(desc);
        descLabel.setId("descLabel");
        descLabel.setStyle("-fx-text-fill: black; -fx-font-size: 14px;");

        VBox rightBox = new VBox();
        rightBox.setAlignment(Pos.CENTER_LEFT);
        rightBox.setSpacing(10);
        rightBox.getChildren().addAll(descLabel, borrowButton, returnButton);

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(20, 20, 20, 20));
        hbox.setSpacing(20);
        hbox.getChildren().addAll(leftBox, rightBox);

        root.setCenter(hbox);

        stage.show();
    }

    private void borrowBook() {
        // Code to borrow the book goes here
    }

    private void returnBook() {
        // Code to return the book goes here
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }
}
