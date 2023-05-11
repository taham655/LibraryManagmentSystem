package com.example.librarymanagment.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBC {
    public static void main(String[] args) {
        //getConnection();
        List<Reviews> r = getReviews(2);
        for (Reviews review : r) {
            System.out.println(review.getReview());
        }

    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8080/library", "root", "");

            return connection;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Book> getBooksData() {
        List<Book> books = new ArrayList<>();
        Connection connectDB = getConnection();

        String data = "SELECT book_id, title, authors, categories, description, published_year, average_rating, num_pages, thumbnail, available FROM `Books` ";
        try {
            PreparedStatement statement = connectDB.prepareStatement(data);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                books.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getDouble(7), rs.getDouble(8), rs.getString(9), rs.getString(10)));
            }
            connectDB.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return books;
    }


    public static void updateBook(int bookId, String title, String authors, String categories, Double rating, String description) {
        String updateQuery = "UPDATE Books SET title = ?, authors = ?, categories = ?, description = ?,average_rating = ? WHERE book_id = ?";

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, title);
            statement.setString(2, authors);
            statement.setString(3, categories);
            statement.setString(4, description);
            statement.setDouble(5,  rating);
            statement.setInt(6, bookId);

            statement.executeUpdate();

            statement.close();
            connection.close();
            System.out.println("Book updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //FOR REVIEWS

    public static void addReview(int userId, int bookId, int rating, String comment, Timestamp createdAt) {
        String insertQuery = "INSERT INTO review (user_id, book_id, rating, comment, created_at) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setInt(1, userId);
            statement.setInt(2, bookId);
            statement.setInt(3, rating);
            statement.setString(4, comment);
            statement.setTimestamp(5, createdAt);

            statement.executeUpdate();

            statement.close();
            connection.close();
            System.out.println("Review added");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Reviews> getReviews(int book_Id) {
        String selectQuery = "SELECT user_id, book_id, rating, comment FROM review WHERE book_id = ?";

        List<Reviews> reviews = new ArrayList<>();

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setInt(1, book_Id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                int bookId = resultSet.getInt("book_id");
                Double rating = resultSet.getDouble("rating");
                String comment = resultSet.getString("comment");
                Reviews review = new Reviews(bookId, userId, rating, comment);
                reviews.add(review);
            }

            resultSet.close();
            statement.close();
            connection.close();
            System.out.println("Reviews retrieved");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reviews;
    }


    //FOR USERS

    public static void signUp(String username, String password, String confirmPassword ,String name , String phone) {
        if (!password.equals(confirmPassword) && !username.isEmpty() && !password.isEmpty() && !name.isEmpty() && !phone.isEmpty()) {
            throw new IllegalArgumentException("Password and confirm password do not match.");
        }

        String insertQuery = "INSERT INTO users (username, password, name, email) VALUES (?, ?, ?,?)";

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, name);
            statement.setString(4, phone);
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int login(String username, String password) {
        String selectQuery = "SELECT id FROM users WHERE username = ? AND password = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getInt("id");
                } else {
                    return -1; // login failed
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static User getUserData(String userData) {
        String selectQuery = "SELECT * FROM users WHERE username = ?";

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setString(1, userData);
            ResultSet result = statement.executeQuery();
            result.next();
            int id = result.getInt("id");
            String user = result.getString("username");
            String pass = result.getString("password");
            User user1 = new User(id, user, pass);
            result.close();
            statement.close();
            connection.close();

            return user1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void borrowBook(int book_id, int user_id, String borrow_date, String expected_date) {
        String insertQuery = "INSERT INTO borrow (user_id, book_id,borrow_date,expected_date) VALUES (?, ?, ?, ?)";

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setInt(1, user_id);
            statement.setInt(2, book_id);
            statement.setDate(3, Date.valueOf(borrow_date));
            statement.setDate(4, Date.valueOf(expected_date));

            statement.executeUpdate();

            statement.close();
            connection.close();
            System.out.println("Book borrowed");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateAvailable(int book_id, String action) {
        String available = "";
        if (action.equals("borrow")) {
            available = "no";
        } else if (action.equals("return")) {
            available = "yes";
        }
        String updateQuery = "UPDATE books SET available = ? WHERE book_id = ?";

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, available);
            statement.setInt(2, book_id);

            statement.executeUpdate();

            statement.close();
            connection.close();
            System.out.println("Book updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Book getUpdated(int book_id){
        String selectQuery = "SELECT title, authors, categories, description, average_rating, available FROM `Books` WHERE book_id = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setInt(1, book_id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Book requiredBook = new Book();
            requiredBook.setBookTitle(resultSet.getString("title"));
            requiredBook.setAuthor(resultSet.getString("authors"));
            requiredBook.setCategories(resultSet.getString("categories"));
            requiredBook.setDescription(resultSet.getString("description"));
            requiredBook.setAvg_rating(resultSet.getDouble("average_rating"));
            requiredBook.setAvailable(resultSet.getString("available"));

            resultSet.close();
            statement.close();
            connection.close();

            return requiredBook;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void addBook(String title, String authors, String categories, String description, double average_rating, String available, String thumbnail) {
        //available = "yes";
        String insertQuery = "INSERT INTO books (title, authors, categories, description, average_rating, available, thumbnail) VALUES (?, ?, ?, ?, ?, ?, ?)";
        //JDBC.addBook(bookName.getText(),author.getText(),genre.getText(),description.getText(),rating.getText(),"yes, bookURL.getText());
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, title);
            statement.setString(2, authors);
            statement.setString(3, categories);
            statement.setString(4, description);
            statement.setDouble(5, average_rating);
            statement.setString(6, available);
            statement.setString(7, thumbnail);
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removebook(int book_id){
        String deleteQuery = "DELETE FROM books WHERE book_id = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, book_id);
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<borrow> getBorrowed(int user_id){
        String selectQuery = "  SELECT b.borrowID, u.id, u.username, b.book_id, bk.title,DATEDIFF(b.expected_date, CURDATE()) AS days_left FROM borrow b JOIN users u ON b.user_id = u.id JOIN books bk ON b.book_id = bk.book_id WHERE u.id = ? ;";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();
            List<borrow> borrowedBooks = new ArrayList<>();
            while (resultSet.next()) {
                int borrowID = resultSet.getInt("borrowID");
                int userID = resultSet.getInt("id");
                String username = resultSet.getString("username");
                int bookID = resultSet.getInt("book_id");
                String bookTitle = resultSet.getString("title");
                int daysLeft = resultSet.getInt("days_left");
                borrowedBooks.add(new borrow(borrowID, userID, username, bookID, bookTitle, daysLeft));
            }

            resultSet.close();
            statement.close();
            connection.close();

            return borrowedBooks;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static void returnBook(int borrowID){
        String deleteQuery = "DELETE FROM borrow WHERE borrowID = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, borrowID);
            statement.executeUpdate();
            System.out.println("Book returned");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<borrow> totalBorrowed(){
        String selectQuery = "  SELECT b.borrowID, u.id, u.username, b.book_id, bk.title,DATEDIFF(b.expected_date, CURDATE()) AS days_left , b.expected_date, b.borrow_date   FROM borrow b JOIN users u ON b.user_id = u.id JOIN books bk ON b.book_id = bk.book_id;";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();
            List<borrow> borrowedBooks = new ArrayList<>();
            while (resultSet.next()) {
                int borrowID = resultSet.getInt("borrowID");
                int userID = resultSet.getInt("id");
                String username = resultSet.getString("username");
                int bookID = resultSet.getInt("book_id");
                String bookTitle = resultSet.getString("title");
                int daysLeft = resultSet.getInt("days_left");
                String borrowDate = String.valueOf(resultSet.getDate("borrow_date"));
                String expectedDate = String.valueOf(resultSet.getDate("expected_date"));
                borrowedBooks.add(new borrow(borrowID, userID, username, bookID, bookTitle, daysLeft, borrowDate, expectedDate));
            }

            resultSet.close();
            statement.close();
            connection.close();

            return borrowedBooks;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<User> getUserData(){
        String selectQuery = "SELECT * FROM users";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");
                users.add(new User(id,name, username, password, email));
            }

            resultSet.close();
            statement.close();
            connection.close();

            return users;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeUser(int userID){
        String deleteQuery = "DELETE FROM users WHERE id = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, userID);
            statement.executeUpdate();
            System.out.println("User removed");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createAdmin (String name, String username, String password, String email){
        String insertQuery = "INSERT INTO admin (username, password, email) VALUES (?, ?, ?)";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Admin Login

    public static int adminLogin(String username, String password) {
        String selectQuery = "SELECT Id FROM admin WHERE username = ? AND password = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getInt("Id");
                } else {
                    return -1; // login failed
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkUser(String username){
        String selectQuery = "SELECT * FROM users WHERE username = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, username);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return true;
                } else {
                    return false; // login failed
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkAdmin(String username){
        String selectQuery = "SELECT * FROM admin WHERE username = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, username);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return true;
                } else {
                    return false; // login failed
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Double getbalance (int userID){
        String selectQuery = "SELECT outstanding_balance FROM users WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setInt(1, userID);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getDouble("outstanding_balance");
                } else {
                    return null; // login failed
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateBalance (int userID, double balance){
        String updateQuery = "UPDATE users SET outstanding_balance = ? WHERE id = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setDouble(1, balance);
            statement.setInt(2, userID);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUserEmail(String username){
        String selectQuery = "SELECT email FROM users WHERE username = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            String email = null;
            while (resultSet.next()) {
                email = resultSet.getString("email");
                System.out.println(email);
            }
            resultSet.close();
            statement.close();
            connection.close();
            return email;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getAdminEmail(String username){
        String selectQuery = "SELECT email FROM admin WHERE username = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            String email = null;
            while (resultSet.next()) {
                email = resultSet.getString("email");
                System.out.println(email);
            }
            resultSet.close();
            statement.close();
            connection.close();
            return email;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateUserPassword(String username, String password){
        String updateQuery = "UPDATE users SET password = ? WHERE username = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, password);
            statement.setString(2, username);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateAdminPassword(String username, String password){
        String updateQuery = "UPDATE admin SET password = ? WHERE username = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, password);
            statement.setString(2, username);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkUserEmail(String email){
        String selectQuery = "SELECT * FROM users WHERE email = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, email);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return true;
                } else {
                    return false; // login failed
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
