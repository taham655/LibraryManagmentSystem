package com.example.librarymanagment.controls;

import com.example.librarymanagment.model.Book;
import com.example.librarymanagment.model.JDBC;
import com.example.librarymanagment.model.User;
import com.example.librarymanagment.model.borrow;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class adminController extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
            primaryStage.setTitle("Admin View");

            // Create the root BorderPane
            BorderPane root = new BorderPane();

            // Create a VBox for the navigation bar
            VBox navBar = new VBox();
            navBar.setPadding(new Insets(50, 10, 50, 10));
            navBar.setSpacing(20);
            navBar.setPrefWidth(230);
            navBar.setStyle("-fx-background-color: #1f1f1f;");


            // Create an ImageView for admin avatar
            ImageView adminAvatar = new ImageView(new Image("/images/programmer.png"));
            adminAvatar.setFitWidth(100);
            adminAvatar.setFitHeight(100);

            // Create a Label for admin name
            Label adminNameLabel = new Label("Imap Ussay");
            adminNameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

            // Create a VBox to hold the admin avatar and name
            VBox adminInfoBox = new VBox(adminAvatar, adminNameLabel);
            adminInfoBox.setAlignment(Pos.CENTER);

            // Create navigation buttons
            Button addBookButton = new Button("Add Book");
            Button removeBookButton = new Button("Remove Book");
            Button borrowedBooksButton = new Button("Borrowed Books");
            Button usersButton = new Button("Users");
            Button CreateAdminButton = new Button("Create Admin");
            Button logoutButton = new Button("Log Out");

            logoutButton.setPadding(new Insets(200, 20, 20, 20));
            logoutButton.setStyle("-fx-background-color: #910202; -fx-text-fill: white; -fx-font-size: 16px; -fx-pref-width: 170px; -fx-pref-height: 50px; -fx-border-radius: 10px; -fx-background-radius: 10px;");

            navBar.setAlignment(Pos.TOP_CENTER);


            // Create a VBox to hold the table views
            VBox tableViews = new VBox();
            tableViews.setSpacing(10);
            tableViews.setPadding(new Insets(10));

            // Add the navigation buttons to the navigation bar
            navBar.getChildren().addAll(adminInfoBox, addBookButton, removeBookButton, borrowedBooksButton, usersButton, CreateAdminButton, logoutButton);

            logoutButton.setOnAction(e -> {
                try {
                    new login().start(primaryStage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });



            ////Making add books table\\\\


            // Create the TableView for adding books
            Book book = new Book();
            TextField bookURL = new TextField();
            bookURL.setPrefWidth(300);
            TextField bookName = new TextField();
            TextField author = new TextField();
            TextField genre = new TextField();
            TextField rating = new TextField();
            TextArea description = new TextArea();

            ImageView bookImageView = new ImageView();
            bookImageView.setFitHeight(220);
            bookImageView.setFitWidth(180);
            bookImageView.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
            Label bookAddedLabel = new Label("");

                Button ADD = new Button("ADD BOOK");

                ADD.setOnAction(e -> {
                    if (bookURL.getText().isEmpty() || bookName.getText().isEmpty() || author.getText().isEmpty() || genre.getText().isEmpty() || rating.getText().isEmpty() || description.getText().isEmpty()){
                        bookAddedLabel.setTextFill(Color.RED);
                        bookAddedLabel.setText("Please fill all the fields!");
                    } else if(Double.parseDouble(rating.getText())  > 5 || (Double.parseDouble(rating.getText()) < 0) || !rating.getText().matches("[0-9]+(\\.[0-9]+)?")){
                        bookAddedLabel.setTextFill(Color.RED);
                        bookAddedLabel.setText("Please enter a valid rating!");
                    } else {
                        JDBC.addBook(bookName.getText(),author.getText(),genre.getText(),description.getText(), Double.parseDouble(rating.getText()),"yes", bookURL.getText());
                        bookAddedLabel.setTextFill(Color.GREEN);
                        bookAddedLabel.setText("Book Added Successfully!");

                    }
                   // JDBC.addBook(bookName.getText(),author.getText(),genre.getText(),description.getText(), Double.parseDouble(rating.getText()),"yes", bookURL.getText());
                });

        // Create the necessary containers
        VBox mainContainer = new VBox();
        mainContainer.setAlignment(Pos.CENTER);

        VBox topContainer = new VBox();
        topContainer.setAlignment(Pos.CENTER);

        HBox middleContainer = new HBox();
        middleContainer.setAlignment(Pos.CENTER);

        VBox descriptionContainer = new VBox();
        descriptionContainer.setAlignment(Pos.CENTER_LEFT);

        HBox bottomContainer = new HBox();
        bottomContainer.setAlignment(Pos.CENTER);
        // Set the spacing and padding for the containers
        mainContainer.setSpacing(20);
        mainContainer.setPadding(new Insets(50));

        topContainer.setSpacing(10);
        middleContainer.setSpacing(10);
        descriptionContainer.setSpacing(10);
        bottomContainer.setSpacing(10);

        // Set the prompt text for the input fields
        bookURL.setPromptText("Book URL here");
        bookName.setPromptText("Book Name here");
        author.setPromptText("Author here");
        rating.setPromptText("Rating here");
        genre.setPromptText("Genre here");
        description.setPromptText("Description here");


            // Create the search button
        Button searchButton = new Button("Search");

        searchButton.setOnAction(e -> {
            Image bookImage = new Image(bookURL.getText());
            bookImageView.setImage(bookImage);
        });
        searchButton.setStyle("-fx-background-color: #000000; -fx-text-fill: white; -fx-max-width: 100px ;-fx-font-size: 10px;");
        searchButton.setPrefHeight(11);
        searchButton.setMaxHeight(11);

            // Create the labels and input fields
        Label bookURLLabel = new Label("Book URL:");
        HBox addBookBox = new HBox(bookURLLabel, bookURL, searchButton);
        addBookBox.setAlignment(Pos.CENTER);
        addBookBox.setSpacing(10);

        Label bookNameLabel = new Label("Book Name:");
        HBox addbox1 = new HBox(bookNameLabel, bookName);
        addbox1.setSpacing(10);

        Label authorLabel = new Label("Author:");
        HBox addbox2 = new HBox(authorLabel, author);
        addbox2.setSpacing(10);

        Label genreLabel = new Label("Genre:");
        HBox addbox3 = new HBox(genreLabel, genre);
        addbox3.setSpacing(10);

        Label ratingLabel = new Label("Rating:");
        HBox addbox4 = new HBox(ratingLabel, rating);
        addbox4.setSpacing(10);

        Label descriptionLabel = new Label("Description:");
        descriptionContainer.getChildren().addAll(descriptionLabel, description);

        // Add components to the containers
        topContainer.getChildren().addAll(bookImageView, addBookBox);
        middleContainer.getChildren().addAll(addbox1, addbox2, addbox3, addbox4);
        bottomContainer.getChildren().add(ADD);


        bookAddedLabel.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");

        mainContainer.getChildren().addAll(topContainer, middleContainer, descriptionContainer, bottomContainer ,bookAddedLabel);


        addBookButton.setOnAction(e -> {
                tableViews.getChildren().clear();
                tableViews.getChildren().addAll(mainContainer);
            });




            //// Adding book ends here \\\\\


        ///REMOVE BOOK\\\

        ImageView bookImageViewRemove = new ImageView();
        bookImageViewRemove.setFitHeight(250);
        bookImageViewRemove.setFitWidth(170);

        // Create the book title label
        Label titleLabel = new Label();

        // Create the table view
        TableView<Book> tableView = new TableView<>();
        ObservableList<Book> books = FXCollections.observableArrayList();
        books.addAll(JDBC.getBooksData());
        tableView.setItems(books);

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBookTitle()));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAuthor()));

        TableColumn<Book, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategories()));

        TableColumn<Book, String> availColumn = new TableColumn<>("Availability");
        availColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAvailable()));

        tableView.getColumns().addAll(titleColumn, authorColumn, genreColumn, availColumn);

        // Set the table view selection listener
        ImageView finalBookImageViewRemove = bookImageViewRemove;
        final int[] selectedBookID = new int[1];
        Label removeBookLabel = new Label();
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                removeBookLabel.setText("");
                Book selectedBook = (Book) tableView.getSelectionModel().getSelectedItem();
                finalBookImageViewRemove.setImage(new Image(selectedBook.getThumbnail()));
                selectedBookID[0] = selectedBook.getBook_id();
                titleLabel.setText(selectedBook.getBookTitle());
            }
        });

        tableView.setStyle("-fx-background-color: TRANSPARENT;");
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefWidth(Region.USE_COMPUTED_SIZE);
        tableView.setPrefHeight(Region.USE_COMPUTED_SIZE);
        tableView.setPlaceholder(new Label("No books to display"));

        // Create the search bar
        TextField searchBookTextField = new TextField();
        searchBookTextField.setPromptText("Search Book");

        // Filtered list to hold the search results
        FilteredList<Book> filteredList = new FilteredList<>(books, p -> true);

        // Set the filter predicate based on the search text
        searchBookTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(book1 -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (book1.getBookTitle().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Title matches the search text
                } else{
                    return false; // No match found
                }
                // No match found
            });
        });
        searchBookTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                tableView.setItems(books); // Show all books if search text is empty
            } else {
                tableView.setItems(filteredList); // Show filtered books
            }
        });

        // Create the main layout
        VBox mainLayout = new VBox();
        mainLayout.setSpacing(20);
        mainLayout.setPadding(new Insets(40));
        mainLayout.setAlignment(Pos.CENTER);

        // Create the book details layout
        VBox bookDetailsLayout = new VBox();
        bookDetailsLayout.setSpacing(20);
        bookDetailsLayout.setAlignment(Pos.CENTER);
        bookDetailsLayout.getChildren().addAll(bookImageViewRemove, titleLabel);

        Button removeBook = new Button("Remove Book");

        removeBookLabel.setStyle("-fx-text-fill: green;");
        removeBook.setOnAction(e -> {
            Book selectedBook = (Book) tableView.getSelectionModel().getSelectedItem();
            JDBC.removebook(selectedBook.getBook_id());
            tableView.getItems().remove(selectedBook);
            removeBookLabel.setText("Book Removed");
            System.out.println("Book Removed");
                });

        mainLayout.getChildren().addAll(searchBookTextField, bookDetailsLayout, tableView, removeBook, removeBookLabel);

        removeBookButton.setOnAction(e -> {
                tableViews.getChildren().clear();
                tableViews.getChildren().add(mainLayout);
            });























//        TextField SearchBookToRemove = new TextField();
//        ObservableList<Book> books = FXCollections.observableArrayList();
//        books.addAll(JDBC.getBooksData());
//
//
//
//
//        // Create the book image view
//        ImageView bookImageViewRemove = new ImageView();
//        bookImageViewRemove = new ImageView();
//        bookImageViewRemove.setFitHeight(250);
//        bookImageViewRemove.setFitWidth(170);
//
//        // Create the book title label
//        Label titleLabel = new Label();
//
//        // Create the table view
//        TableView tableView = new TableView<>();
//        tableView.setItems(books);
//
//        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
//        titleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBookTitle()));
//
//        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
//        authorColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAuthor()));
//
//        TableColumn<Book, String> genreColumn = new TableColumn<>("Genre");
//        genreColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategories()));
//
//        TableColumn<Book, String> availColumn = new TableColumn<>("Availibility");
//        availColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAvailable()));
//
//
//        tableView.getColumns().addAll(titleColumn, authorColumn, genreColumn, availColumn);
//
//        // Set the table view selection listener
//        ImageView finalBookImageViewRemove = bookImageViewRemove;
//        final int[] selectedBookID = new int[1];
//        Label removeBookLabel = new Label();
//        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            if (newSelection != null) {
//                removeBookLabel.setText("");
//                Book selectedBook = (Book) tableView.getSelectionModel().getSelectedItem();
//                finalBookImageViewRemove.setImage(new Image(selectedBook.getThumbnail()));
//                selectedBookID[0] = selectedBook.getBook_id();
//                titleLabel.setText(selectedBook.getBookTitle());
//            }
//        });
//
//        //tableView.setPrefWidth(600);
//        tableView.setStyle("-fx-background-color: TRANSPARENT;");
//        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//        tableView.setPrefWidth(Region.USE_COMPUTED_SIZE);
//        tableView.setPrefHeight(Region.USE_COMPUTED_SIZE);
//        tableView.setPlaceholder(new Label("No books to display"));
//
//
//        // Create the main layout
//        VBox mainLayout = new VBox();
//        mainLayout.setSpacing(20);
//        mainLayout.setPadding(new Insets(40));
//        mainLayout.setAlignment(Pos.CENTER);
//
//        // Create the book details layout
//        VBox bookDetailsLayout = new VBox();
//        bookDetailsLayout.setSpacing(20);
//        bookDetailsLayout.setAlignment(Pos.CENTER);
//        bookDetailsLayout.getChildren().addAll(bookImageViewRemove, titleLabel);
//
//        Button removeBook = new Button("Remove Book");
//
//        removeBookLabel.setStyle("-fx-text-fill: green;");
//        removeBook.setOnAction(e -> {
//            Book selectedBook = (Book) tableView.getSelectionModel().getSelectedItem();
//            JDBC.removebook(selectedBook.getBook_id());
//            tableView.getItems().remove(selectedBook);
//            removeBookLabel.setText("Book Removed");
//            System.out.println("Book Removed");
//                });
//
//
//
//        mainLayout.getChildren().addAll(bookDetailsLayout, tableView, removeBook, removeBookLabel);
//
//
//
//
//
//            // Set the action for the "Remove Book" button
//            removeBookButton.setOnAction(e -> {
//                tableViews.getChildren().clear();
//                tableViews.getChildren().add(mainLayout);
//            });


        ///// REMOVE BOOKS ENDS HERE \\\\\\

        VBox mainLayoutBorrowed = new VBox();
        mainLayoutBorrowed.setSpacing(20);
        mainLayoutBorrowed.setPadding(new Insets(40));
        mainLayoutBorrowed.setAlignment(Pos.CENTER);

        VBox titleContainer = new VBox();
        titleContainer.setSpacing(20);
        titleContainer.setAlignment(Pos.CENTER);
        Label title = new Label("Borrowed Books");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
        titleContainer.getChildren().add(title);

        ObservableList<borrow> borrowedBooks = FXCollections.observableArrayList();
        borrowedBooks.addAll(JDBC.totalBorrowed());

        // Create the table view
        TableView borrowedBooksTable = new TableView<>();
        borrowedBooksTable.setItems(borrowedBooks);


        //int borrow_id, int user_id, String username, int book_id, String title, int days_left, Date borrow_date, Date expected_date
        TableColumn<borrow, Integer> bookIDColumn = new TableColumn<>("Book ID");
        bookIDColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getBook_id()));
        TableColumn<borrow, String> bookTitleColumn = new TableColumn<>("Book Title");
        bookTitleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));
        TableColumn<borrow, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUsername()));
        TableColumn<borrow, String> borrowedDate = new TableColumn<>("Issue Date");
        borrowedDate.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBorrow_date()));
        TableColumn<borrow, String> expectedDate = new TableColumn<>("Expected Return Date");
        expectedDate.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getExpected_date()));
        TableColumn<borrow, Integer> daysLeft = new TableColumn<>("Days Left");
        daysLeft.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getDay_left()));

        borrowedBooksTable.getColumns().addAll(bookIDColumn, bookTitleColumn, usernameColumn, borrowedDate, expectedDate, daysLeft);

        //borrowedBooksTable.setPrefWidth(600);

        borrowedBooksTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        borrowedBooksTable.setPrefWidth(Region.USE_COMPUTED_SIZE);
        borrowedBooksTable.setPrefHeight(Region.USE_COMPUTED_SIZE);


        // Create the main layout


        mainLayoutBorrowed.getChildren().addAll(titleContainer, borrowedBooksTable);

            borrowedBooksButton.setOnAction(e -> {
                tableViews.getChildren().clear();
                tableViews.getChildren().add(mainLayoutBorrowed);

            });



            //borrowed books end here\\




        VBox mainLayoutUser = new VBox();
        mainLayoutUser.setSpacing(20);
        mainLayoutUser.setPadding(new Insets(40));
        mainLayoutUser.setAlignment(Pos.CENTER);

        VBox titleContainerUser = new VBox();
        titleContainerUser.setSpacing(20);
        titleContainerUser.setAlignment(Pos.CENTER);
        Label titleUSer = new Label("Users");
        titleUSer.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
        titleContainerUser.getChildren().add(titleUSer);

        ObservableList<User> users = FXCollections.observableArrayList();
        users.addAll(JDBC.getUserData());

        // Create the table view
        TableView usersTable = new TableView<>();
        usersTable.setItems(users);


        //int borrow_id, int user_id, String username, int book_id, String title, int days_left, Date borrow_date, Date expected_date
        TableColumn<User, Integer> userID = new TableColumn<>("User ID");
        userID.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getUser_id()));
        TableColumn<User, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        TableColumn<User, String> username = new TableColumn<>("Username");
        username.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUsername()));
        TableColumn<User, String> password = new TableColumn<>("Password");
        password.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPassword()));
        TableColumn<User, String> phone = new TableColumn<>("Phone");
        phone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPhone()));

        usersTable.getColumns().addAll(userID, name, username, password, phone);


        usersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        usersTable.setPrefWidth(Region.USE_COMPUTED_SIZE);
        usersTable.setPrefHeight(Region.USE_COMPUTED_SIZE);

        Button RemoveUserButton = new Button("Remove User");
        RemoveUserButton.setStyle("-fx-background-color: #8c1010; -fx-text-fill: #ffffff; -fx-font-size: 15px;");
        RemoveUserButton.setPrefWidth(150);
        RemoveUserButton.setPrefHeight(50);
        Label removingMsg = new Label();
        removingMsg.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #36c700;");




        RemoveUserButton.setOnAction(e -> {
            User selectedUser = (User) usersTable.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
                removingMsg.setText("");
                JDBC.removeUser(selectedUser.getUser_id());
                usersTable.getItems().remove(selectedUser);
                removingMsg.setText("User Removed");
            }
        });



        mainLayoutUser.getChildren().addAll(titleContainerUser, usersTable, RemoveUserButton, removingMsg);

            // Set the action for the "Users" button
            usersButton.setOnAction(e -> {
                tableViews.getChildren().clear();
                tableViews.getChildren().add(mainLayoutUser);
            });

                    /////USER ENDS HERE\\\\\

            VBox mainLayoutCreateAdmin = new VBox();
            mainLayoutCreateAdmin.setSpacing(20);
            mainLayoutCreateAdmin.setPadding(new Insets(40));

            Label titleCreateAdmin = new Label("Create Admin");
            titleCreateAdmin.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
            titleCreateAdmin.setAlignment(Pos.CENTER);

            Label nameCreateAdmin = new Label("Name");
            TextField nameCreateAdminField = new TextField();
            nameCreateAdminField.setPrefWidth(300);
            nameCreateAdminField.setPrefHeight(50);
            nameCreateAdminField.setPromptText("Enter Name");

            Label usernameCreateAdmin = new Label("Username");
            TextField usernameCreateAdminField = new TextField();
            usernameCreateAdminField.setPrefWidth(300);
            usernameCreateAdminField.setPrefHeight(50);
            usernameCreateAdminField.setPromptText("Enter Username");

            Label passwordCreateAdmin = new Label("Password");
            PasswordField passwordCreateAdminField = new PasswordField();
            passwordCreateAdminField.setPrefWidth(300);
            passwordCreateAdminField.setPrefHeight(50);
            passwordCreateAdminField.setPromptText("Enter Password");

            Label phoneCreateAdmin = new Label("Phone");
            TextField phoneCreateAdminField = new TextField();
            phoneCreateAdminField.setPrefWidth(300);
            phoneCreateAdminField.setPrefHeight(50);
            phoneCreateAdminField.setPromptText("Enter Phone");

            Button createAdminButton = new Button("Create Admin");
            createAdminButton.setStyle("-fx-background-color: #8c1010; -fx-text-fill: #ffffff; -fx-font-size: 15px;");
            createAdminButton.setPrefWidth(150);
            createAdminButton.setPrefHeight(50);

            Label creatingMsg = new Label();
            creatingMsg.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #36c700;");

            createAdminButton.setOnAction(e -> {
                JDBC.createAdmin(nameCreateAdminField.getText(), usernameCreateAdminField.getText(), passwordCreateAdminField.getText(), phoneCreateAdminField.getText());
                creatingMsg.setText("Admin Created");
                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                        creatingMsg.setText("");
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                });

            });



            mainLayoutCreateAdmin.getChildren().addAll(titleCreateAdmin, nameCreateAdmin, nameCreateAdminField, usernameCreateAdmin, usernameCreateAdminField, passwordCreateAdmin, passwordCreateAdminField, phoneCreateAdmin, phoneCreateAdminField, createAdminButton, creatingMsg);


            CreateAdminButton.setOnAction(e -> {
                tableViews.getChildren().clear();
                tableViews.getChildren().add(mainLayoutCreateAdmin);
            });

            // Add the navigation bar and table views to the root BorderPane
            root.setLeft(navBar);
            root.setCenter(tableViews);

            // Set the background color of the root BorderPane
            root.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, null, null)));


            // Create a Scene and show the Stage
            Scene scene = new Scene(root, 1315, 890);
            scene.getStylesheets().add(getClass().getResource("/CSS/admin.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();



        }


    public static void main(String[] args) {
        launch();
    }
}
