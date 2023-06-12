package com.example.rental.model;

import com.example.rental.dto.vehicle.MotorDto;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;

@Document("Motor")
public class Motor extends Vehicle {

     public static Motor getInstance(MotorDto motorDto, String id) {
         Motor motor = new Motor();
         motor.setOwnerId(id);
         motor.setProduction(motorDto.getProduction());
         motor.setModel(motorDto.getModel());
         motor.setPrice(motorDto.getPrice());
         motor.setEngineSize(motorDto.getEngineSize());
         motor.setNote(motorDto.getNote());
         motor.setImageUrl(motorDto.getImageUrl());
         motor.setFuel(motorDto.getFuel());
         motor.setLicensePlate(motorDto.getLicense());
         motor.setLocation(new Point(motorDto.getLng(), motorDto.getLat()));
         motor.setAddress(motorDto.getAddress());
         motor.setYear(motorDto.getYear());
         motor.setRadius(motorDto.getRadius());
         motor.setFeature(motorDto.getFeature());
         motor.setType(motorDto.getType());
         motor.setMinAge(motorDto.getMinAge());
         motor.setMinDriving(motorDto.getMinDriving());
         motor.setMinDur(Duration.ofMillis(motorDto.getMinDur()));
         motor.setMaxDur(Duration.ofMillis(motorDto.getMaxDur()));
         return motor;
     }
}
