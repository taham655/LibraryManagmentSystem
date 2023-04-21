package com.example.librarymanagment.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBC {
    public static void main(String[] args) {
        getConnection();

    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8080/library", "root", "");
            System.out.println("Database connected");

            return connection;

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<Book> getBooksData () {
        List<Book> books = new ArrayList<>();
        Connection connectDB = getConnection();

        String data = "SELECT book_id, title, authors, categories, description, published_year, average_rating, num_pages,thumbnail FROM `Books` ";

        try {
            PreparedStatement statement = connectDB.prepareStatement(data);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                books.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getDouble(7), rs.getDouble(8),rs.getString(9)));
            }
            connectDB.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return books;
    }


}
