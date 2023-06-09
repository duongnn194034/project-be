package com.example.rental.dto.offer;

import com.example.rental.model.Vehicle;
import com.example.rental.enums.Status;

import java.util.Date;

public class OfferResponseDto {
    private String id;
    private Vehicle vehicle;
    private Date startTime;
    private Date endTime;
    private Status status;
    private double price;
    private Date createdDate;

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
}
