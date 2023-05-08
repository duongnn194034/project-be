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
    @JoinColumn(name = "motor_id", referencedColumnName = "id", unique = true)
    private Motor motor;
    private double price;
    private String note;

    private double rating;

    public long getId() {
        return id;
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
