package com.example.librarymanagment.controls;

import com.example.librarymanagment.model.Book;
import com.example.librarymanagment.model.JDBC;
import com.example.librarymanagment.model.Reviews;
import com.example.librarymanagment.model.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class bookInfoController extends Application {
    static User user = new User();
    static String usernames;
    static int user_id;
    public void setStage(Stage stage, int book_id,  Image image) throws Exception {
        Book getBook = JDBC.getUpdated(book_id);
        login login = new login();
        String username = login.getUserName();
        User user = JDBC.getUserData(username);

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Book Info");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);

        Button borrowButton = new Button("Borrow");
        borrowButton.setId("borrowButton");
        //stage.setScene(scene);

        VBox vbox = new VBox();



        ImageView imageView = new ImageView(image);
        imageView.setId("imageView");
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(200);
        imageView.setFitHeight(250);
        imageView.setStyle("-fx-border-width: 10px; -fx-border-radius: 5px; -fx-border-color: black; -fx-background-color: white;");


        Label titleLabel = new Label(getBook.getBookTitle());
        titleLabel.setId("titleLabel");
        Label authorLabel = new Label(getBook.getAuthor());
        authorLabel.setId("authorLabel");
        authorLabel.setStyle("-fx-text-fill: black; -fx-font-size: 13; -fx-font-family: 'Arial Narrow';");

        Label ratingLabel = new Label("Rating : "+getBook.getAvg_rating().toString() + "/5");
        ratingLabel.setId("ratingLabel");
        ratingLabel.setStyle("-fx-text-fill: black; -fx-font-size: 13px; -fx-font-family: 'Arial Narrow';");

        Label availableLabel = new Label("Available : "+getBook.getAvailable());

        if(getBook.getAvailable().equals("yes")){
            availableLabel.setStyle("-fx-text-fill: green; -fx-font-size: 13px;-fx-font-weight: bold;-fx-font-family: 'Arial Narrow';");
        } else {
            availableLabel.setStyle("-fx-text-fill: red; -fx-font-size: 13px;-fx-font-weight: bold;-fx-font-family: 'Arial Narrow';");
            borrowButton.setDisable(true);

        }


        Label descField = new Label(getBook.getDescription());
        //descField.setPrefSize(600, 400);
        descField.setWrapText(true);
        descField.setText(getBook.getDescription());

        descField.setStyle("-fx-text-fill: black; -fx-font-size: 13px;-fx-font-family: 'Arial Narrow';-fx-border-color: black; -fx-background-color: white;-fx-border-radius: 10px;-fx-alignment: center;");
        descField.setAlignment(Pos.CENTER);
        descField.setPadding(new Insets(25, 25, 25, 25));

        VBox bookInfoBox = new VBox();
        bookInfoBox.setSpacing(5);
        bookInfoBox.setAlignment(Pos.CENTER);
        bookInfoBox.setPadding(new Insets(20, 20, 20, 20));

        borrowButton.setOnAction(e ->
        {
            DatePicker d = new DatePicker();
            d.setValue(LocalDate.now());
            d.setDayCellFactory(picker -> new DateCell() {
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    LocalDate maxDate = today.plusWeeks(3);
                    setDisable(empty || date.compareTo(today) < 0 || date.compareTo(maxDate) > 0);
                }
            });

            Label l = new Label("Select the return date");
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    LocalDate i = d.getValue();

                }

            };
            Button submitButton = new Button("Submit");
            submitButton.setOnAction(event1 -> {
                LocalDate currentDate = LocalDate.now();
                d.setOnAction(event);
                borrowBook(book_id, user_id, currentDate, d.getValue());
                availableLabel.setText("Available : no");
                availableLabel.setStyle("-fx-text-fill: red; -fx-font-size: 13px;-fx-font-weight: bold;-fx-font-family: 'Arial Narrow';");
                submitButton.setDisable(true);

            });

            d.setStyle("-fx-font-size: 13px;-fx-font-family: 'Arial Narrow';-fx-border-color: black; -fx-background-color: white;-fx-background-radius: 11px; -fx-tab-min-width: 100px ;-fx-border-radius: 10px;-fx-alignment: center;");

            VBox v = new VBox();
            v.getChildren().addAll(l,d, submitButton);
            v.setSpacing(5);
            v.setAlignment(Pos.CENTER);
            ((Pane)borrowButton.getParent()).getChildren().add(v);
            borrowButton.setDisable(true);

        });

        bookInfoBox.getChildren().addAll(imageView, titleLabel, authorLabel, ratingLabel, availableLabel,descField);

        vbox.getChildren().addAll(bookInfoBox);

        Button reviewButton = new Button("Leave a Review !");
        reviewButton.setId("reviewButton");

        reviewButton.setOnAction(e ->{
            try {
                // Create a text field and a submit button
                TextField reviewField = new TextField();
                reviewField.setPromptText("Enter your review here");
                Button submitButton = new Button("Submit");

                // Set the action of the submit button

                    submitButton.setOnAction(event -> {

                        JDBC.addReview(user.getUser_id(), book_id, 5, "'"+reviewField.getText() +"'"+ " - " + usernames, Timestamp.valueOf("2020-12-12 12:12:12"));
                        reviewField.clear();
                        submitButton.setDisable(true);
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
                reviewButton.setDisable(true);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });


        VBox borrowingBox= new VBox();
        borrowingBox.setAlignment(Pos.CENTER);
        borrowingBox.setSpacing(10);
        borrowingBox.getChildren().add(borrowButton);

        VBox reviewBox= new VBox();
        reviewBox.setAlignment(Pos.CENTER);
        reviewBox.setSpacing(10);
        reviewBox.getChildren().add(reviewButton);



        VBox reviews = new VBox();
        reviews.setPadding(new Insets(20, 20, 20, 20));
        Label reviewHeading = new Label("Reviews");
        reviewHeading.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold; -fx-font-family: 'Arial Narrow';");
        reviews.getChildren().add(reviewHeading);



        List<Reviews> review = JDBC.getReviews(book_id);
        if(review.size() == 0){
            Label noReviews = new Label("No Reviews Yet !");
            noReviews.setStyle("-fx-text-fill: black; -fx-font-size: 10px;-fx-alignment: center; -fx-font-style: italic;-fx-font-family: Arial");
            noReviews.setAlignment(Pos.CENTER);
            noReviews.setPadding(new Insets(10, 0, 0, 0));
            reviews.getChildren().add(noReviews);
        }
        for (Reviews r: review) {
            Label reviewLabel = new Label(r.getReview());
            Label name = new Label(user.getUsername());
            name.setStyle("-fx-text-fill: grey ;-fx-font-size: 10px;-fx-font-family: 'Trebuchet MS';-fx-alignment: center; -fx-font-weight: bold;");
            reviewLabel.setWrapText(true);
            reviewLabel.setText(r.getReview());

            reviewLabel.setStyle("-fx-text-fill: black; -fx-font-size: 13px;fx-font-family: 'Arial Narrow';");
            reviewLabel.setAlignment(Pos.CENTER);
            reviewLabel.setPadding(new Insets(10, 0, 10, 0));
            reviews.getChildren().add(reviewLabel);
        }


        vbox.setPadding(new Insets(20, 20, 20, 20));
        vbox.setSpacing(20);
        vbox.getChildren().addAll(borrowingBox,reviewBox,reviews);


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(vbox);


        root.setCenter(scrollPane);

        scene.getStylesheets().add(getClass().getResource("/CSS/bookInfo.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    public static void getUserInfo(int id , String username){
        usernames = username;
        user_id = id;
    }

    public void borrowBook(int book_id, int user_id, LocalDate borrowDate, LocalDate returnDate) {

        JDBC.borrowBook(book_id, user_id, borrowDate.toString(), returnDate.toString());
        JDBC.updateAvailable(book_id, "borrow");

    }


    @Override
    public void start(Stage stage) throws Exception {

    }
}
