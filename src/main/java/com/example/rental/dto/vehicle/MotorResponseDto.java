package com.example.rental.dto.vehicle;

import com.example.rental.enums.VehicleType;
import com.example.rental.model.motor.Feature;
import com.example.rental.model.motor.Rate;
import com.example.rental.model.user.User;
import com.example.rental.model.motor.Vehicle;
import org.springframework.data.geo.Point;

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
    protected double deposit;
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
    private int ratingTotal;
    private long offerNum;

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
        motorResponseDto.setDeposit(vehicle.getDeposit());
        motorResponseDto.setMinAge(vehicle.getMinAge());
        motorResponseDto.setMinDriving(vehicle.getMinDriving());
        motorResponseDto.setMinDur(vehicle.toMinDur());
        motorResponseDto.setMaxDur(vehicle.toMaxDur());
        motorResponseDto.setType(vehicle.getType());
        motorResponseDto.setNote(vehicle.getNote());
        motorResponseDto.setRadius(vehicle.getRadius());
        motorResponseDto.setRatings(vehicle.getRatings());
        motorResponseDto.setRatingTotal(vehicle.getRatingTotal());
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

    public long getOfferNum() {
        return offerNum;
    }

    public void setOfferNum(long offerNum) {
        this.offerNum = offerNum;
    }

    public int getRatingTotal() {
        return ratingTotal;
    }

    public void setRatingTotal(int ratingTotal) {
        this.ratingTotal = ratingTotal;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }
}
