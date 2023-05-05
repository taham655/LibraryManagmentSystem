package com.example.librarymanagment.model;


import com.example.librarymanagment.controls.bookInfoController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Utils {


    public static FlowPane searchBooks(String name) {
            List<Book> foundBooks = JDBC.getBooksData();
            FlowPane root = new FlowPane();

            // Set padding, gaps and alignment for the FlowPane
            root.setPadding(new Insets(10));
            root.setHgap(30);
            root.setVgap(30);
            root.setAlignment(Pos.CENTER);
            root.setOrientation(Orientation.HORIZONTAL);


            boolean booksFound = false; // flag to track if any books are found
            int counter = 0; // counter to limit number of books displayed
            ExecutorService executor = Executors.newFixedThreadPool(15);
            for (Book book : foundBooks) {
                if (counter > 45) {
                    break; // limit number of books displayed to 45
                }
                if (book.getBookTitle().toLowerCase().contains(name.toLowerCase()) && book.getThumbnail() != null && book.getDescription() != null) {
                    booksFound = true; // set the flag to true when a book is found
                    counter++; // increment counter for displayed books
                    executor.execute(() -> {
                        try {
                            // Download the image from the URL
                            Image image = new Image(book.getThumbnail(), true);

                            // Wait for the image to load
                            image.progressProperty().addListener((observable, oldValue, newValue) -> {
                                if (newValue.doubleValue() >= 1.0) {

                                    // Create ImageView with size and add title
                                    ImageView imageView = new ImageView(image);
                                    imageView.setFitWidth(140);
                                    imageView.setFitHeight(220);
                                    Label titleLabel = new Label(book.getBookTitle());
                                    titleLabel.setAlignment(Pos.CENTER);

                                    // Create VBox with ImageView and Label
                                    VBox box = new VBox(imageView, titleLabel);
                                    box.setAlignment(Pos.CENTER);
                                    titleLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-font-family:'Arial Narrow'; -fx-font-weight: bold;");

                                    // Create button with VBox as graphic
                                    Button button = new Button("", box);
                                    button.setPrefSize(120, 160);

                                    button.setOnAction(e -> {
                                        bookInfoController bookInfoController = new bookInfoController();
                                        // get the primary stage
                                        Stage primaryStage = new Stage();
                                        // call the start method of BookInfoController with the primary stage
                                        try {
                                            bookInfoController.setStage(primaryStage, book.getBook_id(), image);
                                        } catch (Exception ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    });

                                    // Add button to FlowPane on the JavaFX application thread
                                    Platform.runLater(() -> root.getChildren().add(button));
                                }
                            });
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                }
            }

            executor.shutdown();

            // If no books were found, show a label saying "no books found"
            if (!booksFound) {
                Label label = new Label("No books found!");
                label.setAlignment(Pos.CENTER);
                label.setStyle("-fx-text-fill: black; -fx-font-size: 16px; -fx-font-family: 'Verdana'");
                root.getChildren().add(label);
            }

            return root;
    }


}

//else if(JDBC.getbalance(user_id) > 0){
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Error");
//                    alert.setHeaderText("BALANCE DUE");
//                    alert.setContentText("Please pay the balance to borrow another book");
//                    alert.showAndWait();

