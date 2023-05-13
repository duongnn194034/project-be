package com.example.rental.service;

import com.example.rental.dto.vehicle.MotorDto;
import com.example.rental.model.Motor;
import com.example.rental.model.Rate;
import com.example.rental.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.geo.GeoResults;

public interface MotorService {
    Motor getById(String id);

    Motor save(MotorDto motorDto, User owner);

    Page<Motor> getTopRating(int index);

    GeoResults<Motor> findByLocNear(double lat, double lng);

    void rateMotor(String id, Rate rate);
}
