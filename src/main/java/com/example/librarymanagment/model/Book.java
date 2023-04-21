package com.example.librarymanagment.model;

public class Book {
    private int book_id;
    private String bookTitle;
    private String author;
    private String categories;
    private String description;
    private int published_year;
    private Double avg_rating;
    private Double num_pages;
    private String thumbnail;

    public Book(int book_id, String bookTitle, String author, String categories, String description, int published_year, Double avg_rating, Double num_pages, String thumbnail) {
        setBook_id(book_id);
        setAuthor(author);
        setBookTitle(bookTitle);
        setCategories(categories);
        setDescription(description);
        setPublished_year(published_year);
        setAvg_rating(avg_rating);
        setNum_pages(num_pages);
        setThumbnail(thumbnail);
    }

    public Book() {

    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublished_year(int published_year) {
        this.published_year = published_year;
    }

    public void setAvg_rating(Double avg_rating) {
        this.avg_rating = avg_rating;
    }

    public void setNum_pages(Double num_pages) {
        this.num_pages = num_pages;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getBook_id() {
        return book_id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategories() {
        return categories;
    }

    public String getDescription() {
        return description;
    }

    public int getPublished_year() {
        return published_year;
    }

    public Double getAvg_rating() {
        return avg_rating;
    }

    public Double getNum_pages() {
        return num_pages;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
