package com.example.rental.service.impl;

import com.example.rental.dto.vehicle.MotorDto;
import com.example.rental.exception.MotorException;
import com.example.rental.model.Motor;
import com.example.rental.model.User;
import com.example.rental.repository.MotorRepository;
import com.example.rental.service.MotorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MotorServiceImpl implements MotorService {
    @Autowired
    MotorRepository motorRepository;

    private static final int PAGE_LIMIT = 3;
    private static final double MAX_DIST = 10.0;

    @Override
    public Motor getById(String id) throws MotorException {
        Optional<Motor> motor = motorRepository.findById(id);
        if (motor.isEmpty()) {
            throw new MotorException("Id is not exist.");
        }
        return motor.get();
    }

    @Override
    public Motor save(MotorDto motorDto, User owner) {
        return motorRepository.save(Motor.getInstance(motorDto, owner));
    }

    @Override
    public Page<Motor> getTopRating(int index) {
        return motorRepository.findAll(PageRequest.of(index, PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "rating")));
    }

    @Override
    public GeoResults<Motor> findByLocNear(double lng, double lat) {
        return motorRepository.findByLocationNear(new Point(lng, lat), new Distance(MAX_DIST, Metrics.KILOMETERS));
    }

    @Override
    public double rateMotor(String id, double rate) throws MotorException {
        Motor motor = getById(id);
        double rating = motor.updateRating(rate);
        motor.setId(id);
        motorRepository.save(motor);
        return rating;
    }
}