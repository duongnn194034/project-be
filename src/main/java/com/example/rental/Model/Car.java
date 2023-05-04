package com.example.rental.Model;

import javax.persistence.*;

@Entity
@Table
public class Car extends Vehicle {
    private int seats;
    private int storages;
    @Column(name = "air_conditioner")
    private boolean airConditioner;
    private String radio;
    @Column(name = "cruise_control")
    private boolean cruiseControl;
    private boolean automatic;
    private boolean bluetooth;

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

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
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
}
