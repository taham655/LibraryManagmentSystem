package com.example.librarymanagment.controls;

import com.example.librarymanagment.model.Book;
import com.example.librarymanagment.model.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Connection;

public class AdminController implements Initializable{

//#5d8dc7
    //-fx-background-color: #806649;
    //#d7d3cc
    @FXML
    private TableColumn<Book, String> ab_author_col;

    @FXML
    private TableColumn<Book, String> ab_genre_col;

    @FXML
    private TableColumn<Book, Double> ab_py_col;

    @FXML
    private TableColumn<Book, Float> ab_rating_col;

    @FXML
    private TableView<Book> ab_table;

    @FXML
    private Label ab_title;
    @FXML
    private Label ab_author;

    @FXML
    private TableColumn<Book, String> ab_title_col;

    @FXML
    private Button add_books;

    @FXML
    private Button available_books;

    @FXML
    private ImageView book_image;

    @FXML
    private Button remove_books;

    @FXML
    private Button signout_button;

    ObservableList<Book> bookList = FXCollections.observableArrayList();


    public void BooksData () {
        ab_title_col.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        ab_author_col.setCellValueFactory(new PropertyValueFactory<>("author"));
        ab_genre_col.setCellValueFactory(new PropertyValueFactory<>("categories"));
        ab_py_col.setCellValueFactory(new PropertyValueFactory<>("published_year"));
        ab_rating_col.setCellValueFactory(new PropertyValueFactory<>("avg_rating"));

        JDBC connectNow = new JDBC();
        Connection connectDB = connectNow.getConnection();

        String data = "SELECT book_id, title, authors, categories, description, published_year, average_rating, num_pages,thumbnail FROM `Books`";

//        try {
//            PreparedStatement statement = connectDB.prepareStatement(data);
//            ResultSet rs = statement.executeQuery();
//
//            while (rs.next()) {
//                Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getDouble(7), rs.getDouble(8),rs.getString(9));
//                bookList.add(book);
//            }
//            ab_table.setItems(bookList);
//            connectDB.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }

    public void selectedBook () {
        Book book = ab_table.getSelectionModel().getSelectedItem();
        int num = ab_table.getSelectionModel().getFocusedIndex();
        if ((num - 1) < -1) {
            return;
        }
        ab_title.setText(book.getBookTitle());
        ab_author.setText(book.getAuthor());

        String image = book.getThumbnail();
        book_image.setImage(new Image(image, 208,279, false, true));

    }


    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        BooksData();
    }
}
