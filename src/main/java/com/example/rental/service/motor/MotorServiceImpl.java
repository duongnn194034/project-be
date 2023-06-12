package com.example.rental.service.motor;

import com.example.rental.dto.vehicle.MotorDto;
import com.example.rental.exception.MotorException;
import com.example.rental.model.Motor;
import com.example.rental.model.Rate;
import com.example.rental.model.User;
import com.example.rental.repository.motor.MotorRepository;
import com.example.rental.repository.motor.MotorRepositoryUtil;
import com.example.rental.repository.offer.OfferRepositoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.GeoResults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MotorServiceImpl implements MotorService {
    @Autowired
    MotorRepository motorRepository;
    @Autowired
    MotorRepositoryUtil motorRepositoryUtil;
    @Autowired
    OfferRepositoryUtil offerRepositoryUtil;


    @Override
    public Motor getById(String id) throws MotorException {
        Optional<Motor> motor = motorRepository.findById(id);
        if (motor.isEmpty()) {
            throw new MotorException("Id is not exist.");
        }
        return motor.get();
    }

    @Override
    public Motor save(MotorDto motorDto, String ownerId) {
        return motorRepository.save(Motor.getInstance(motorDto, ownerId));
    }

    @Override
    public List<Motor> getTopRating(int limit) {
        List<Motor> motors = motorRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));
        return motors.subList(0, Math.min(limit, motors.size()));
    }

    @Override
    public GeoResults<Motor> findByLocNear(double lat, double lng, long start, long end) {
        List<String> ids = new ArrayList<>();
        if (start >= 0 || end >= 0) {
            Date startDate = new Date(start < 0 ? 0 : start);
            Date endDate = new Date(end < 0 ? System.currentTimeMillis() : end);
            ids.addAll(offerRepositoryUtil.findIdsByDateBetween(startDate, endDate));
        }
        return motorRepositoryUtil.findByQuery(lat, lng, ids);
    }

    @Override
    public void rateMotor(String id, Rate rate) throws MotorException {
        Motor motor = getById(id);
        motor.addRating(rate);
        motor.setId(id);
        motorRepository.save(motor);
    }

    @Override
    public List<Motor> findByOwner(String ownerId) {
        return motorRepository.findByOwnerId(ownerId);
    }
}
