package com.example.librarymanagment.model;

public class Reviews {
    private int review_id;
    private int book_id;
    private String review;
    private int user_id;
    private Double rating;

    //private Timestamp timestamp;

    public Reviews(int book_id,int user_id, Double rating, String review) {
        setBook_id(book_id);
        setReview(review);
        setRating(rating);
        setUser_id(user_id);
    }

    public Reviews() {

    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }



    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public String getReview() {
        return review;
    }


}
