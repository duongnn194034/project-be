package com.example.rental.Model;

import javax.persistence.*;

@Entity
@Table
public class RentalOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;
    @OneToOne(targetEntity = Motor.class)
    @JoinColumn(name = "motor_id", referencedColumnName = "id")
    private Motor motor;
    private double latPos;
    private double lngPos;
    private double price;
    private String note;

    public long getId() {
        return id;
    }

    public double getLatPos() {
        return latPos;
    }

    public void setLatPos(double latPos) {
        this.latPos = latPos;
    }

    public double getLngPos() {
        return lngPos;
    }

    public void setLngPos(double lngPos) {
        this.lngPos = lngPos;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
