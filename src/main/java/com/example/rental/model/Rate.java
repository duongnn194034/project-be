package com.example.rental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class Rate {
    @JsonIgnore
    private String userId;
    private String username;
    private int rating;
    private String comment;

    private Date createdDate;

    public Rate(String userId, String username, int rating, String comment) {
        this.userId = userId;
        this.username = username;
        this.rating = rating;
        this.comment = comment;
        this.createdDate = new Date();
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getCreatedDate() {
        return createdDate;
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
