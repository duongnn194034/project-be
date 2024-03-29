package com.example.rental.dto.rate;

import com.example.rental.model.motor.Rate;

import java.util.Date;

public class RateResponseDto {
    private String username;
    private String vehicleName;
    private int rating;
    private String comment;
    private Date createdDate;

    public RateResponseDto(String username, String vehicleName, int rating, String comment, Date createdDate) {
        this.username = username;
        this.vehicleName = vehicleName;
        this.rating = rating;
        this.comment = comment;
        this.createdDate = createdDate;
    }

    public static RateResponseDto getInstance(Rate rate, String vehicleName) {
        return new RateResponseDto(rate.getUsername(), vehicleName, rate.getRating(), rate.getComment(), rate.getCreatedDate());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
