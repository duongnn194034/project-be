package com.example.rental.Model;

import com.example.rental.Enums.VehicleType;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Motor")
public class Motor extends Vehicle {

    public Motor(String name, String model, int cylinder, User owner, VehicleType type) {
        this.name = name;
        this.model = model;
        this.engineSize = cylinder;
        this.owner = owner;
        this.type = type;
    }

}
