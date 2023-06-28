package com.example.rental.service.motor;

import com.example.rental.dto.rate.RateDto;
import com.example.rental.dto.rate.RateResponseDto;
import com.example.rental.dto.vehicle.MotorDto;
import com.example.rental.dto.vehicle.MotorResponseDto;
import com.example.rental.model.Motor;
import com.example.rental.model.Rate;
import com.example.rental.model.User;
import org.springframework.data.geo.GeoResults;

import java.util.List;

public interface MotorService {
    Motor findById(String id);
    MotorResponseDto getById(String id);

    Motor save(MotorDto motorDto, String ownerId);

    List<Motor> getTopRating(int limit);

    GeoResults<Motor> findByLocNear(double lat, double lng, long start, long end);

    void rateMotor(String id, User user, RateDto rateDto);

    List<Motor> findByOwner(String id, int limit);

    List<RateResponseDto> getAllUserRating(String userId);
}
