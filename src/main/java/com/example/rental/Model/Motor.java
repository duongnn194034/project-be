package com.example.rental.Model;

import com.example.rental.Enums.VehicleType;

import javax.persistence.*;

@Entity
@Table(name = "Motor")
public class Motor extends Vehicle {
    private int cylinder;

    public Motor(String name, String model, int cylinder, User owner, VehicleType type) {
        this.name = name;
        this.model = model;
        this.cylinder = cylinder;
        this.owner = owner;
        this.type = type;
    }

    public int getCylinder() {
        return cylinder;
    }

    public void setCylinder(int cylinder) {
        this.cylinder = cylinder;
    }
}
