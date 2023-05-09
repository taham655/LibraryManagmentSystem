package com.example.librarymanagment.testing;

import com.example.librarymanagment.model.Book;
import com.example.librarymanagment.model.JDBC;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JDBCTest {

    @Test
    void getBooksData() {
        List<Book> books = JDBC.getBooksData();
        assertEquals(6799, books.size()); // Change 3 to the expected size of the list

        // Add more assertions to check the properties of the books in the list
        assertEquals("The Problem of Pain", books.get(0).getBookTitle());
        assertEquals("Clive Staples Lewis", books.get(0).getAuthor());
        assertEquals(2002, books.get(0).getPublished_year());

        assertEquals("Empires of the Monsoon", books.get(1).getBookTitle());
        assertEquals("Richard Hall", books.get(1).getAuthor());
        assertEquals(1998, books.get(1).getPublished_year());
    }

    @Test
    void getReviews() {
    }

    @Test
    void login() {
    }

    @Test
    void getUserData() {
    }

    @Test
    void getUpdated() {
    }

    @Test
    void getBorrowed() {
    }

    @Test
    void totalBorrowed() {
    }

    @Test
    void testGetUserData() {
    }

    @Test
    void adminLogin() {
    }

    @Test
    void checkUser() {
    }

    @Test
    void getbalance() {
    }

    @Test
    void updateBalance() {
    }

    @Test
    void getEmail() {
    }
}