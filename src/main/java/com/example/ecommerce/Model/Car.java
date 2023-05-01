package com.example.ecommerce.Model;

import javax.persistence.*;

@Entity
@Table
public class Car extends Vehicle {
    private int seats;
    @Column(name = "air_conditioner")
    private boolean airConditioner;

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
