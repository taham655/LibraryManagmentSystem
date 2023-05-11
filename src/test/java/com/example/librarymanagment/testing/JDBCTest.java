package com.example.librarymanagment.testing;

import com.example.librarymanagment.model.Book;
import com.example.librarymanagment.model.JDBC;
import com.example.librarymanagment.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JDBCTest {

    @Test
    void getBooksData() {
        List<Book> books = JDBC.getBooksData();
        //assertEquals(6799, books.size()); // Change 3 to the expected size of the list

        // Add more assertions to check the properties of the books in the list
        assertEquals("Rich dad Poor dad", books.get(0).getBookTitle());
        assertEquals("Robert Kiyosaki", books.get(0).getAuthor());

        assertEquals("Friends, Lovers, and the Big Terrible Thing: A Memoir", books.get(1).getBookTitle());
        assertEquals("Matthew Perry", books.get(1).getAuthor());
    }

    @Test
    void getReviews() {
    }

    @Test
    void login() {
        int userId = JDBC.login("taha", "asd123");
        assertEquals(2, userId); // Expecting login success, so return value should be 17

        // Test with invalid credentials
        userId = JDBC.login("taha", "wrongpassword");
        assertEquals(-1, userId); // Expecting login failure, so return value should be -1
    }

    @Test
    void getUserData() {
        List<User> users = JDBC.getUserData();
        assertEquals("TBDM", users.get(2).getUsername());
        assertEquals("DEATHMASK DIVINE", users.get(2).getPassword());
    }


}