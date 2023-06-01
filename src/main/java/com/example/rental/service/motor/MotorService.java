package com.example.rental.service.motor;

import com.example.rental.dto.vehicle.MotorDto;
import com.example.rental.model.Motor;
import com.example.rental.model.Rate;
import com.example.rental.model.User;
import org.springframework.data.geo.GeoResults;

import java.util.List;

public interface MotorService {
    Motor getById(String id);

    Motor save(MotorDto motorDto, User owner);

    List<Motor> getTopRating(int limit);

    GeoResults<Motor> findByLocNear(double lat, double lng, long start, long end);

    void rateMotor(String id, Rate rate);

    List<Motor> findByOwner(User owner);
}
