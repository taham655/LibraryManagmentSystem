package com.example.librarymanagment.controls;

import com.example.librarymanagment.model.JDBC;
import com.example.librarymanagment.model.Reviews;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.util.List;

public class bookInfoController extends Application {
    public void setStage(Stage stage, int book_id, String title, String author, String desc, Image image, Double rating, String genre, String available) throws Exception {

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Book Info");
        stage.setScene(scene);

        VBox vbox = new VBox();

        Button borrowButton = new Button("Borrow");
        borrowButton.setId("borrowButton");
        borrowButton.setOnAction(e -> borrowBook());





        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(150);
        imageView.setFitHeight(200);
        imageView.setStyle("-fx-border-width: 10px; -fx-border-radius: 5px; -fx-border-color: black; -fx-background-color: white;");


        Label titleLabel = new Label(title);
        titleLabel.setId("titleLabel");
        titleLabel.setStyle("-fx-text-fill: black; -fx-font-size: 15px; -fx-font-weight: bold; -fx-font-family: 'Trebuchet MS';");

        Label authorLabel = new Label(author);
        authorLabel.setId("authorLabel");
        authorLabel.setStyle("-fx-text-fill: black; -fx-font-size: 13px;-fx-font-family: 'monospace';");

        Label ratingLabel = new Label("Rating : "+rating.toString() + "/5");
        ratingLabel.setId("ratingLabel");
        ratingLabel.setStyle("-fx-text-fill: black; -fx-font-size: 13px;-fx-font-family: 'monospace';");

        Label availableLabel = new Label("Available : "+available);
        if(available.equals("yes")){
            availableLabel.setStyle("-fx-text-fill: green; -fx-font-size: 13px;-fx-font-family: 'monospace'; -fx-font-weight: bold;");
        } else {
            availableLabel.setStyle("-fx-text-fill: red; -fx-font-size: 13px;-fx-font-family: 'monospace'; -fx-font-weight: bold;");
            borrowButton.setDisable(true);

        }


        Label descField = new Label(desc);
        //descField.setPrefSize(600, 400);
        descField.setWrapText(true);
        descField.setText(desc);
        descField.setStyle("-fx-text-fill: black; -fx-font-size: 10px;-fx-font-family: 'Trebuchet MS'; -fx-padding: 5px;-fx-alignment: center;");
        descField.setAlignment(Pos.CENTER);
        descField.setPadding(new Insets(25, 25, 25, 25));

        VBox bookInfoBox = new VBox();
        bookInfoBox.setSpacing(2);
        bookInfoBox.setAlignment(Pos.CENTER);
        bookInfoBox.setPadding(new Insets(20, 20, 20, 20));
        bookInfoBox.getChildren().addAll(imageView, titleLabel, authorLabel, ratingLabel, availableLabel);

        vbox.getChildren().addAll(bookInfoBox,descField);

        Button reviewButton = new Button("Leave a Review !");
        reviewButton.setId("reviewButton");

//        reviewButton.setOnAction(e ->{
//            try {
//                TextField reviewField = new TextField();
//                reviewField.setPromptText("Enter your review here");
//                Button submitButton = new Button("Submit");
//                submitButton.setOnAction(event -> {
//                    //int userId, int bookId, int rating, String comment, Timestamp createdAt
//                    JDBC.addReview(1, book_id, rating.intValue(), reviewField.getText(), new Timestamp(System.currentTimeMillis()));
//                    reviewField.clear();
//                    vbox.getChildren().add(reviewField);
//                });
//
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//        });

        reviewButton.setOnAction(e ->{
            try {
                // Create a text field and a submit button
                TextField reviewField = new TextField();
                reviewField.setPromptText("Enter your review here");
                Button submitButton = new Button("Submit");

                // Set the action of the submit button
                submitButton.setOnAction(event -> {
                    //int userId, int bookId, int rating, String comment, Timestamp createdAt
                    JDBC.addReview(1, book_id, rating.intValue(), reviewField.getText(), Timestamp.valueOf("2020-12-12 12:12:12"));
                    reviewField.clear();
                });

                // Listen for the Enter key on the text field
                reviewField.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        submitButton.fire();
                    }
                });

                // Add the text field and submit button to the VBox
                VBox vbox1= new VBox();
                vbox1.getChildren().addAll(reviewField, submitButton);
                vbox1.setSpacing(5);
                vbox1.setAlignment(Pos.CENTER);
                ((Pane)reviewButton.getParent()).getChildren().add(vbox1);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });


        VBox rightBox = new VBox();
        rightBox.setAlignment(Pos.CENTER);
        rightBox.setSpacing(10);
        rightBox.getChildren().addAll(borrowButton, reviewButton);



        VBox reviews = new VBox();
        reviews.setPadding(new Insets(20, 20, 20, 20));
        Label reviewHeading = new Label("Reviews");
        reviewHeading.setStyle("-fx-text-fill: black; -fx-font-size: 15px;-fx-font-family: 'Trebuchet MS'; -fx-padding: 5px;-fx-alignment: center; -fx-font-weight: bold;");
        reviews.getChildren().add(reviewHeading);
        List<Reviews> review = JDBC.getReviews(book_id);
        if(review.size() == 0){
            Label noReviews = new Label("No Reviews Yet !");
            noReviews.setStyle("-fx-text-fill: black; -fx-font-size: 10px;-fx-font-family: 'Trebuchet MS'; -fx-padding: 5px;-fx-alignment: center;");
            noReviews.setAlignment(Pos.CENTER);
            noReviews.setPadding(new Insets(20, 20, 20, 20));
            reviews.getChildren().add(noReviews);
        }
        for (Reviews r: review) {
            Label reviewLabel = new Label(r.getReview());
            reviewLabel.setWrapText(true);
            reviewLabel.setText("'"+r.getReview()+"'");

            reviewLabel.setStyle("-fx-text-fill: black; -fx-font-size: 10px;-fx-font-family: 'Trebuchet MS'; -fx-padding: 5px;-fx-alignment: center; ");
            reviewLabel.setAlignment(Pos.CENTER);
            reviewLabel.setPadding(new Insets(20, 20, 20, 20));
            reviews.getChildren().add(reviewLabel);
        }


        vbox.setPadding(new Insets(20, 20, 20, 20));
        vbox.setSpacing(20);
        vbox.getChildren().addAll(rightBox,reviews);


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(vbox);


        root.setCenter(scrollPane);

        stage.show();
    }

    private void borrowBook() {
        // Code to borrow the book goes here
    }

    private void reviewBook(int book_id) {


    }
    @Override
    public void start(Stage stage) throws Exception {
        //stage.show();
    }
}
