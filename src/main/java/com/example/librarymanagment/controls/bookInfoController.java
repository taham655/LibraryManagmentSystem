package com.example.librarymanagment.controls;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class bookInfoController extends Application {
    public void setStage(Stage stage, String title, String author, String desc, Image image, Double rating, String genre ) throws Exception {
        //book.getBookTitle(), book.getBookAuthor(), book.getBookDescription(), book.getThumbnail(), book.getAvg_rating(),book.getCategories()
        BorderPane root = new BorderPane();
        FlowPane flowPane = new FlowPane();

        ImageView imageView = new ImageView(image);
        imageView.setId("bookImage");

        //Image processing
        //imageView.setPreserveRatio(true);
        imageView.setFitWidth(100);
        imageView.setFitHeight(150);
        imageView.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px;");
        //Image processing end

        Label titleLabel = new Label(title);
        Label authorLabel = new Label(author);
        Label descLabel = new Label(desc);
        Label ratingLabel = new Label(rating.toString());
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(imageView, titleLabel, authorLabel, ratingLabel);

        //flowPane.getChildren().addAll(box , descLabel);
        //flowPane.setAlignment(Pos.CENTER);
        root.setLeft(box);
        root.setCenter(descLabel);
        Scene scene = new Scene(root, 600  , 400);
        titleLabel.setStyle("-fx-text-fill: black;");
        stage.setTitle("Book Info");
        scene.getStylesheets().add(getClass().getResource("/CSS/bookInfo.css").toExternalForm());
        stage.setScene(scene);
        start(stage);

    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }
}
