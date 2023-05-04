package com.example.librarymanagment.model;

public class borrow {
    private int borrow_id;
    private int user_id;
    private int book_id;

    private String title;

    private int day_left;

    private String username;

    private String borrow_date;

    private String expected_date;


        public borrow(int borrow_id, int user_id, String username, int book_id, String title, int days_left, String borrow_date, String expected_date) {
        this.borrow_id = borrow_id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.title = title;
        this.day_left = days_left;
        this.username = username;
        this.borrow_date = borrow_date;
        this.expected_date = expected_date;
    }





    public borrow(int borrow_id, int user_id, String username, int book_id, String title, int days_left) {
        this.borrow_id = borrow_id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.title = title;
        this.day_left = days_left;
        this.username = username;
    }

    public borrow() {
    }

    public int getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(int borrow_id) {
        this.borrow_id = borrow_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public String getTitle() {
        return title;
    }

    public int getDay_left() {
        return day_left;
    }

    public String getUsername() {
        return username;
    }

    public String getBorrow_date() {
        return borrow_date;
    }

    public String getExpected_date() {
        return expected_date;
    }
}
