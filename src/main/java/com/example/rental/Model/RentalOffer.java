package com.example.rental.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document("RentalOffer")
public class RentalOffer {
    @Id
    private String id;
    @Indexed(unique = true)
    private Vehicle vehicle;
    private double price;
    private String note;
    private double rating;
    private int ratingTotal;
    @GeoSpatialIndexed
    private Point location;

    private Date startTime;

    private Date endTime;
    private Feature feature;

    public RentalOffer(Vehicle vehicle, double price, String note, Point location, Date startTime, Date endTime, Feature feature) {
        this.vehicle = vehicle;
        this.price = price;
        this.note = note;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.feature = feature;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getRatingTotal() {
        return ratingTotal;
    }

    public void setRatingTotal(int ratingTotal) {
        this.ratingTotal = ratingTotal;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public void increaseRatingTotal() {
        this.ratingTotal = this.ratingTotal + 1;
    }
}
