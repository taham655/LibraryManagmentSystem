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
        List<Book> found = JDBC.getBooksData();
        FlowPane root = new FlowPane();

        root.setPadding(new Insets(10, 10, 10, 10));
        root.setHgap(30);
        root.setVgap(30);
        root.setAlignment(Pos.CENTER);
        root.setOrientation(Orientation.HORIZONTAL);
        ExecutorService executor = Executors.newFixedThreadPool(15);
        int counter = 0;
        for (Book book : found) {
            if (book.getBookTitle().toLowerCase().contains(name.toLowerCase()) && book.getThumbnail() != null && book.getDescription() != null) {
                    counter++;
                    if (counter > 50) break;
                    // Submit a new task to the executor
                    executor.execute(() -> {
                        try {
                            // Download the image from the URL
                            Image image = new Image(book.getThumbnail(), true);

                            // Wait for the image to load
                            image.progressProperty().addListener((observable, oldValue, newValue) -> {
                                if (newValue.doubleValue() >= 1.0) {
                                    // Create ImageView with image
                                    ImageView imageView = new ImageView(image);
                                    //new Image(image, 208,279, false, true));
                                    //imageView.setPreserveRatio(true);
                                    imageView.setFitWidth(120);
                                    imageView.setFitHeight(180);
                                    //240
                                    //360
                                    // Create Label with title
                                    Label titleLabel = new Label(book.getBookTitle());
                                    titleLabel.setAlignment(Pos.CENTER);

                                    // Create VBox with ImageView and Label
                                    VBox box = new VBox(imageView, titleLabel);
                                    box.setAlignment(Pos.CENTER);

                                    titleLabel.setStyle("-fx-text-fill: black; -fx-font-size: 12px;  -fx-font-family:'Verdana';");


                                    // Create button with ImageView as graphic
                                    Button button = new Button("", box);
                                    button.setPrefSize(120, 160);
                                    button.setOnAction(e -> {
                                        bookInfoController bookInfoController = new bookInfoController();
                                        // get the primary stage
                                        Stage primaryStage = new Stage();
                                       // call the start method of BookInfoController with the primary stage
                                        try {
                                            bookInfoController.setStage(primaryStage,book.getBook_id(),image);
                                        } catch (Exception ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    });

                                    // Add button to flow pane on the JavaFX application thread
                                    Platform.runLater(() -> root.getChildren().add(button));
                                }
                            });
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                }

        }
        return root;
    }

}



