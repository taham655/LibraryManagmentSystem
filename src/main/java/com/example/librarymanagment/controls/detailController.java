package com.example.librarymanagment.controls;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class detailController implements Initializable {
    @FXML
    private Label title;
    @FXML
    private Label author;
    @FXML
    private Label rating;
    @FXML
    private ImageView image;

    public void setBook(String bookTitle, String author, String description, Image image, Double avgRating, String categories) {
        if (bookTitle == null) {
            title.setText("Title of the book");
        } else {
            this.title.setText(bookTitle);
            this.author.setText(author);
            this.rating.setText(avgRating.toString());
            this.image.setImage(image);

        }

    }

//    public void setStuff(String title, String author, String desc, Image image, Double rating, String genre ){
//        this.title.setText(title);
//        this.author.setText(author);
//        this.rating.setText(rating.toString());
//        this.image.setImage(image);
//    }
    //

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
