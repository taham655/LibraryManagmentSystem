package com.example.librarymanagment.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBC {
    public static void main(String[] args) {
        //getConnection();
        List<Reviews> r = getReviews(2);
        for (   Reviews review : r) {
            System.out.println(review.getReview());
        }

    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8080/library", "root", "");
            System.out.println("Data retieved");

            return connection;

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<Book> getBooksData () {
        List<Book> books = new ArrayList<>();
        Connection connectDB = getConnection();

        String data = "SELECT book_id, title, authors, categories, description, published_year, average_rating, num_pages, thumbnail, available FROM `Books` ";
        //int book_id, String bookTitle, String author, String categories, String description, int published_year, Double avg_rating, Double num_pages, String thumbnail, String available
        try {
            PreparedStatement statement = connectDB.prepareStatement(data);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                books.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getDouble(7), rs.getDouble(8),rs.getString(9), rs.getString(10)));

                //int book_id, String bookTitle, String author, String categories, String description, int published_year, Double avg_rating, Double num_pages, String thumbnail, String available
            }
            connectDB.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return books;
    }

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
                //Timestamp createdAt = resultSet.getTimestamp("created_at");

                Reviews review = new Reviews(bookId,userId, rating, comment);
                reviews.add(review);
                //int book_id,String username, Double rating, String review
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




}
