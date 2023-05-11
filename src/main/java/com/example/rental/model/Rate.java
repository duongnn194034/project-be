package com.example.rental.model;

public class Rate {
    private User user;
    private int rating;
    private String comment;

    public String getUserId() {
        return user.getId();
    }

    public String getUserName() {
        return user.getFullName();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
