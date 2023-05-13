package com.example.rental.model;

import com.example.rental.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {
    @Id
    protected String id;
    protected String production;
    protected String model;
    @JsonIgnore
    protected User owner;
    protected VehicleType type;
    protected String imageUrl;
    protected String note;
    protected String engineSize;
    protected double price;
    protected List<Rate> ratings = new ArrayList<>();
    @GeoSpatialIndexed
    protected Point location;
    protected String address;
    protected Feature feature;

    public String getId() {
        return id;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(String engineSize) {
        this.engineSize = engineSize;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public List<Rate> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rate> ratings) {
        this.ratings = ratings;
    }

    public void addRating(Rate rating) {
        this.ratings.add(rating);
    }

    public double getRatingScore() {
        if (ratings.size() == 0) {
            return 0;
        }
        double sum = 0;
        for (Rate rate : ratings) {
            sum += rate.getRating();
        }
        return sum / ratings.size();
    }

    public int getRatingTotal() {
        return ratings.size();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
