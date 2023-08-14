package com.example.rental.dto.offer;

import com.example.rental.dto.user.MiniUserDto;
import com.example.rental.model.motor.Vehicle;
import com.example.rental.enums.Status;

import java.util.Date;

public class OfferResponseDto {
    private String id;
    private String userName;

    private MiniUserDto user;
    private Vehicle vehicle;
    private Date startTime;
    private Date endTime;
    private Status status;
    private double price;
    private Date createdDate;

    public OfferResponseDto(String id, Date startTime, Date endTime, Status status, double price, Date createdDate) {
        this.id = id;
        this.vehicle = vehicle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.status = status;
        this.createdDate = createdDate;
    }

    public OfferResponseDto(String id, Vehicle vehicle, Date startTime, Date endTime, Status status, double price, Date createdDate) {
        this.id = id;
        this.vehicle = vehicle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.status = status;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public MiniUserDto getUser() {
        return user;
    }

    public void setUser(MiniUserDto user) {
        this.user = user;
    }

    public double calcIncome() {
        double income = this.price;
        long delta = System.currentTimeMillis() - this.endTime.getTime();
        if (delta > 6 * 3600 * 1000 && this.status != Status.COMPLETED) {
            income += Math.floor(delta / 3600000) * vehicle.getPrice() * 1.5;
        }
        return income;
    }
}
