package com.example.rental.Model;

import com.example.rental.Enums.VehicleType;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Motor")
public class Motor extends Vehicle {

    public Motor(String production, String model, int engineSize, VehicleType type, String imageUrl, String summary,  User owner) {
        this.production = production;
        this.model = model;
        this.engineSize = engineSize;
        this.owner = owner;
        this.type = type;
        this.imageUrl = imageUrl;
        this.summary = summary;
    }

}
