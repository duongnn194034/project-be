package com.example.rental.dto.vehicle;

import com.example.rental.enums.VehicleType;
import com.example.rental.model.Feature;
import com.example.rental.model.Rate;
import com.example.rental.model.User;
import com.example.rental.model.Vehicle;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MotorResponseDto {
    protected String id;
    protected String production;
    protected String model;
    protected User owner;
    protected VehicleType type;
    protected List<String> imageUrl = new ArrayList<>();
    protected String note;
    protected String engineSize;
    protected String fuel;
    protected double price;
    protected String licensePlate;
    protected List<Rate> ratings = new ArrayList<>();
    protected Point location;
    protected double radius;
    protected String address;
    protected int year;
    protected Feature feature;
    private int minAge;
    private int minDriving;
    private long minDur;
    private long maxDur;

    public static MotorResponseDto getInstance(Vehicle vehicle, User owner) {
        MotorResponseDto motorResponseDto = new MotorResponseDto();
        motorResponseDto.setId(vehicle.getId());
        motorResponseDto.setAddress(vehicle.getAddress());
        motorResponseDto.setEngineSize(vehicle.getEngineSize());
        motorResponseDto.setFuel(vehicle.getFuel());
        motorResponseDto.setLocation(vehicle.getLocation());
        motorResponseDto.setLicensePlate(vehicle.getLicensePlate());
        motorResponseDto.setFeature(vehicle.getFeature());
        motorResponseDto.setImageUrl(vehicle.getImageUrl());
        motorResponseDto.setOwner(owner);
        motorResponseDto.setYear(vehicle.getYear());
        motorResponseDto.setModel(vehicle.getModel());
        motorResponseDto.setProduction(vehicle.getProduction());
        motorResponseDto.setPrice(vehicle.getPrice());
        motorResponseDto.setMinAge(vehicle.getMinAge());
        motorResponseDto.setMinDriving(vehicle.getMinDriving());
        motorResponseDto.setMinDur(vehicle.toMinDur());
        motorResponseDto.setMaxDur(vehicle.toMaxDur());
        motorResponseDto.setType(vehicle.getType());
        motorResponseDto.setNote(vehicle.getNote());
        motorResponseDto.setRadius(vehicle.getRadius());
        motorResponseDto.setRatings(vehicle.getRatings());
        return motorResponseDto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(String engineSize) {
        this.engineSize = engineSize;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public List<Rate> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rate> ratings) {
        this.ratings = ratings;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
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
        return minDur;
    }

    public void setMinDur(long minDur) {
        this.minDur = minDur;
    }

    public long getMaxDur() {
        return maxDur;
    }

    public void setMaxDur(long maxDur) {
        this.maxDur = maxDur;
    }
}
