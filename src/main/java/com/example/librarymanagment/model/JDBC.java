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
            System.out.println("Data retieved");

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

    public static void signUp(String username, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Password and confirm password do not match.");
        }

        String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, username);
            statement.setString(2, password);
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
}




