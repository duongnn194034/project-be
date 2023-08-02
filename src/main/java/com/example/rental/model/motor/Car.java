package com.example.rental.model.motor;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Car extends Vehicle {
    private int seats;
    private int storages;
    private boolean airConditioner;
    private String radio;
    private boolean cruiseControl;
    private boolean bluetooth;
    private boolean airBag;

    public int getStorages() {
        return storages;
    }

    public void setStorages(int storages) {
        this.storages = storages;
    }

    public boolean isAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public boolean isCruiseControl() {
        return cruiseControl;
    }

    public void setCruiseControl(boolean cruiseControl) {
        this.cruiseControl = cruiseControl;
    }

    public boolean isBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean isAirBag() {
        return airBag;
    }

    public void setAirBag(boolean airBag) {
        this.airBag = airBag;
    }
}
