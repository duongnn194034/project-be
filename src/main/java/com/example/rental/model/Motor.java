package com.example.rental.model;

import com.example.rental.dto.vehicle.MotorDto;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Motor")
public class Motor extends Vehicle {

     public static Motor getInstance(MotorDto motorDto, User owner) {
         Motor motor = new Motor();
         motor.setOwner(owner);
         motor.setProduction(motorDto.getProduction());
         motor.setModel(motorDto.getModel());
         motor.setPrice(motorDto.getPrice());
         motor.setEngineSize(motorDto.getEngineSize());
         motor.setNote(motorDto.getNote());
         motor.setImageUrl(motorDto.getImageUrl());
         motor.setLocation(new Point(motorDto.getLng(), motorDto.getLat()));
         motor.setAddress(motorDto.getAddress());
         motor.setFeature(motorDto.getFeature());
         motor.setType(motorDto.getType());
         return motor;
     }
}
