package com.example.rental.service.motor;

import com.example.rental.dto.vehicle.MotorDto;
import com.example.rental.dto.vehicle.MotorResponseDto;
import com.example.rental.model.Motor;
import com.example.rental.model.Rate;
import org.springframework.data.geo.GeoResults;

import java.util.List;

public interface MotorService {
    Motor findById(String id);
    MotorResponseDto getById(String id);

    Motor save(MotorDto motorDto, String ownerId);

    List<Motor> getTopRating(int limit);

    GeoResults<Motor> findByLocNear(double lat, double lng, long start, long end);

    void rateMotor(String id, Rate rate);

    List<Motor> findByOwner(String id);
}
