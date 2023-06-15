package com.example.rental.model;

import com.example.rental.enums.VehicleType;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {
    @Id
    protected String id;
    protected String production;
    protected String model;
    protected String ownerId;
    protected VehicleType type;
    protected List<String> imageUrl = new ArrayList<>();
    protected String note;
    protected String engineSize;
    protected String fuel;
    protected double price;
    protected String licensePlate;
    protected List<Rate> ratings = new ArrayList<>();
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    protected Point location;
    protected double radius;
    protected String address;
    protected int year;
    protected Feature feature;
    private int minAge;
    private int minDriving;
    private Duration minDur;
    private Duration maxDur;

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

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
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
        if (ratings.isEmpty()) {
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

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMinDriving() {
        return minDriving;
    }

    public void setMinDriving(int minDriving) {
        this.minDriving = minDriving;
    }

    public long getMinDur() {
        return minDur.toMillis();
    }

    public void setMinDur(Duration minDur) {
        this.minDur = minDur;
    }

    public long getMaxDur() {
        return maxDur.toMillis();
    }

    public void setMaxDur(Duration maxDur) {
        this.maxDur = maxDur;
    }

    public long toMinDur() {
        if (minDur == null) {
            return 0;
        }
        return minDur.toMillis();
    }

    public long toMaxDur() {
        if (maxDur == null) {
            return Long.MAX_VALUE;
        }
        return maxDur.toMillis();
    }
}
