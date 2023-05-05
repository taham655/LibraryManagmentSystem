package com.example.librarymanagment.controls;

import com.example.librarymanagment.model.*;
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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class bookInfoController extends Application {
    static User user = new User();
    static String usernames;
    static int user_id;
    public void setStage(Stage stage, int book_id,  Image image) throws Exception {

        Book getBook = JDBC.getUpdated(book_id);
        System.out.println(getBook.getThumbnail());
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
        titleLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-font-family: Poppins;");
        Label authorLabel = new Label(getBook.getAuthor());
        authorLabel.setId("authorLabel");
        authorLabel.setStyle("-fx-text-fill: black; -fx-font-size: 13; -fx-font-family: Poppins;");

        Label ratingLabel = new Label("Rating : "+getBook.getAvg_rating().toString() + "/5");
        ratingLabel.setId("ratingLabel");
        ratingLabel.setStyle("-fx-text-fill: black; -fx-font-size: 13px; -fx-font-family: Poppins;");

        Label availableLabel = new Label("Available : "+getBook.getAvailable());

        if(getBook.getAvailable().equals("yes")){
            availableLabel.setStyle("-fx-text-fill: green; -fx-font-size: 13px;-fx-font-weight: bold;-fx-font-family: Poppins;");
        } else {
            availableLabel.setStyle("-fx-text-fill: red; -fx-font-size: 13px;-fx-font-weight: bold;-fx-font-family: Poppins;");
            borrowButton.setDisable(true);

        }


        Label descField = new Label(getBook.getDescription());

        descField.setWrapText(true);
        descField.setText(getBook.getDescription());

        descField.setStyle("-fx-text-fill: black; -fx-font-size: 13px;-fx-font-family: Poppins;-fx-border-color: black; -fx-background-color: #ffffff;-fx-border-radius: 4px;-fx-alignment: center;");
        descField.setAlignment(Pos.CENTER);
        descField.setPadding(new Insets(25, 25, 25, 25));

        VBox bookInfoBox = new VBox();
        bookInfoBox.setSpacing(5);
        bookInfoBox.setAlignment(Pos.CENTER);
        bookInfoBox.setPadding(new Insets(20, 20, 20, 20));

        borrowButton.setStyle("-fx-background-radius: 5; -fx-background-color: #000000; -fx-text-fill: #ffffff");
        borrowButton.setMinWidth(150);

        borrowButton.setOnAction(e ->
        {
            DatePicker d = new DatePicker();
            d.setValue(LocalDate.now());
            d.setStyle("-fx-background-color: #a38a7c; -fx-text-fill: #000000; -fx-font-size: 13px; -fx-font-family: Poppins;");
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

            Double debt = JDBC.getbalance(user_id);

            List<borrow> borrowList = JDBC.getBorrowed(user_id);
            Button submitButton = new Button("Submit");
            submitButton.setMinWidth(100);
            submitButton.setStyle("-fx-background-color: #000000; -fx-text-fill: #ffffff;");
            submitButton.setOnAction(event1 -> {
                System.out.println("The debt is " + debt);
                LocalDate currentDate = LocalDate.now();
                d.setOnAction(event);
                if (borrowList.size() >4 ){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("LIMIT EXCEEDED");
                    alert.setContentText("Please return a book to borrow another one");
                    alert.showAndWait();
                    return;
                } else if(debt > 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("BALANCE DUE");
                    alert.setContentText("Please pay the balance to borrow another book");
                    alert.showAndWait();
                }else {
                    borrowBook(book_id, user_id, currentDate, d.getValue());
                    availableLabel.setText("Available : no");
                    availableLabel.setStyle("-fx-text-fill: red; -fx-font-size: 13px;-fx-font-weight: bold;-fx-font-family: 'Arial Narrow';");
                    submitButton.setDisable(true);
                }


            });

            d.setStyle("-fx-font-size: 13px;-fx-font-family: Poppins; -fx-background-color: black;-fx-alignment: center;-fx-min-width: 213;");

            VBox v = new VBox();
            v.getChildren().addAll(l,d, submitButton);
            v.setSpacing(10);
            v.setAlignment(Pos.CENTER);
            ((Pane)borrowButton.getParent()).getChildren().add(v);
            borrowButton.setDisable(true);

        });

        bookInfoBox.getChildren().addAll(imageView, titleLabel, authorLabel, ratingLabel, availableLabel,descField);

        vbox.getChildren().addAll(bookInfoBox);

        Button reviewButton = new Button("Leave a Review !");
        reviewButton.setStyle("-fx-background-radius: 5; -fx-background-color: #000000; -fx-text-fill: #ffffff");
        reviewButton.setMinWidth(150);
        reviewButton.setId("reviewButton");

        reviewButton.setOnAction(e ->{
            try {
                // Create a text field and a submit button
                TextField reviewField = new TextField();
                reviewField.setPromptText("Enter your review here");
                Button submitButton = new Button("Submit");
                submitButton.setStyle("-fx-background-radius: 3; -fx-background-color: #000000; -fx-text-fill: #ffffff");
                submitButton.setMinWidth(150);
                // Set the action of the submit button

                    submitButton.setOnAction(event -> {
                        if (JDBC.getbalance(user_id) > 0) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("BALANCE DUE");
                            alert.setContentText("Please pay to leave a review");
                            alert.showAndWait();
                        } else if (!reviewField.getText().isEmpty()) {
                            JDBC.addReview(user.getUser_id(), book_id, 5, "'" + reviewField.getText() + "'" + " - " + usernames, Timestamp.valueOf(LocalDateTime.now()));
                            reviewField.clear();
                            stage.close();
                            Stage primaryStage = new Stage();
                            try {
                                setStage(primaryStage, book_id, image);
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                            submitButton.setDisable(true);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("EMPTY REVIEW");
                            alert.setContentText("Please enter a review");
                            alert.showAndWait();
                        }
                    });

//                     Listen for the Enter key on the text field
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
        reviewHeading.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold; -fx-font-family: Poppins;");
        reviews.getChildren().add(reviewHeading);



        List<Reviews> review = JDBC.getReviews(book_id);
        if(review.size() == 0){
            Label noReviews = new Label("No Reviews Yet !");
            noReviews.setStyle("-fx-text-fill: black; -fx-font-size: 10px;-fx-alignment: center; -fx-font-style: italic;-fx-font-family: Poppins;");
            noReviews.setAlignment(Pos.CENTER);
            noReviews.setPadding(new Insets(10, 0, 0, 0));
            reviews.getChildren().add(noReviews);
        }
        for (Reviews r: review) {
            Label reviewLabel = new Label(r.getReview());
            Label name = new Label(user.getUsername());
            name.setStyle("-fx-text-fill: grey ;-fx-font-size: 10px;-fx-font-family: Poppins;-fx-alignment: center; -fx-font-weight: bold;");
            reviewLabel.setWrapText(true);
            reviewLabel.setText(r.getReview());

          //  reviewLabel.setStyle("-fx-text-fill: white; -fx-font-size: 13px;fx-font-family: 'Arial Narrow';");
            reviewLabel.setAlignment(Pos.CENTER_LEFT);
            reviewLabel.setPadding(new Insets(10, 0, 10, 0));
            reviewLabel.setStyle("-fx-border-color: black; -fx-background-color:  #ffffff;-fx-background-radius:2px; -fx-tab-min-width: 100px ;-fx-border-radius: 4px; -fx-padding: 10px 10px 10px 10;-fx-text-fill: black; -fx-font-size: 13px;fx-font-family: Poppins;");
            reviewLabel.setPrefWidth(1000);
            reviews.setSpacing(5);
            reviews.getChildren().add(reviewLabel);
        }


        vbox.setPadding(new Insets(20, 20, 20, 20));
        vbox.setSpacing(20);
        vbox.getChildren().addAll(borrowingBox,reviewBox,reviews);


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(vbox);

        scrollPane.setBackground(new Background(new BackgroundFill(Color.rgb(73, 45, 45), CornerRadii.EMPTY, Insets.EMPTY)));
        root.setCenter(scrollPane);
        //root.setBackground(new Background(new BackgroundFill(Color.rgb(73, 45, 45), CornerRadii.EMPTY, Insets.EMPTY)));

        scene.getStylesheets().add(getClass().getResource("/CSS/bookInfo.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    public static void getUserInfo(int id , String username){
        usernames = username;
        user_id = id;
        System.out.println(user_id);
    }

    public void borrowBook(int book_id, int user_id, LocalDate borrowDate, LocalDate returnDate) {

        JDBC.borrowBook(book_id, user_id, borrowDate.toString(), returnDate.toString());
        JDBC.updateAvailable(book_id, "borrow");

    }


    @Override
    public void start(Stage stage) throws Exception {

    }
}
