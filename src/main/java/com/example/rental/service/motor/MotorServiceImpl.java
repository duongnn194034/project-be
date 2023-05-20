package com.example.rental.service.motor;

import com.example.rental.dto.vehicle.MotorDto;
import com.example.rental.exception.MotorException;
import com.example.rental.model.Motor;
import com.example.rental.model.Rate;
import com.example.rental.model.User;
import com.example.rental.repository.motor.MotorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotorServiceImpl implements MotorService {
    @Autowired
    MotorRepository motorRepository;
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
    public List<Motor> getTopRating(int limit) {
        List<Motor> motors = motorRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));
        return motors.subList(0, Math.min(limit, motors.size()));
    }

    @Override
    public GeoResults<Motor> findByLocNear(double lng, double lat) {
        return motorRepository.findByLocationNear(new Point(lng, lat), new Distance(MAX_DIST, Metrics.KILOMETERS));
    }

    @Override
    public void rateMotor(String id, Rate rate) throws MotorException {
        Motor motor = getById(id);
        motor.addRating(rate);
        motor.setId(id);
        motorRepository.save(motor);
    }
}
