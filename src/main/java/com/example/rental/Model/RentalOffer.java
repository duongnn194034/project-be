package com.example.rental.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document("RentalOffer")
public class RentalOffer {
    @Id
    private String id;
    private Vehicle vehicle;
    private double price;
    private String note;
    private double rating;
    @GeoSpatialIndexed
    private Point location;

    private Date startTime;

    private Date endTime;
    private Feature feature;

    public RentalOffer(Vehicle vehicle, double price, String note, Point location, Date startTime, Date endTime) {
        this.vehicle = vehicle;
        this.price = price;
        this.note = note;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getId() {
        return id;
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
}
