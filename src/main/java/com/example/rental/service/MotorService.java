package com.example.rental.service;

import com.example.rental.dto.vehicle.MotorDto;
import com.example.rental.model.Motor;
import com.example.rental.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.geo.GeoResults;

public interface MotorService {
    Motor getById(String id);

    Motor save(MotorDto motorDto, User owner);

    Page<Motor> getTopRating();

    GeoResults<Motor> findByLocNear(double lng, double lat);

    double rateMotor(String id, double rate);
}
