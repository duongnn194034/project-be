package com.example.rental.service.motor;

import com.example.rental.dto.rate.RateDto;
import com.example.rental.dto.rate.RateResponseDto;
import com.example.rental.dto.vehicle.MotorDto;
import com.example.rental.dto.vehicle.MotorResponseDto;
import com.example.rental.exception.MotorException;
import com.example.rental.model.Motor;
import com.example.rental.model.Rate;
import com.example.rental.model.User;
import com.example.rental.repository.motor.MotorRepository;
import com.example.rental.repository.motor.MotorRepositoryUtil;
import com.example.rental.repository.offer.OfferRepositoryUtil;
import com.example.rental.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.GeoResults;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MotorServiceImpl implements MotorService {
    @Autowired
    MotorRepository motorRepository;
    @Autowired
    MotorRepositoryUtil motorRepositoryUtil;
    @Autowired
    OfferRepositoryUtil offerRepositoryUtil;
    @Autowired
    UserRepository userRepository;


    @Override
    public Motor findById(String id) {
        Optional<Motor> motor = motorRepository.findById(id);
        if (motor.isEmpty()) {
            throw new MotorException("Id is not exist.");
        }
        return motor.get();
    }

    @Override
    public MotorResponseDto getById(String id) throws MotorException {
        Optional<Motor> motor = motorRepository.findById(id);
        if (motor.isEmpty()) {
            throw new MotorException("Id is not exist.");
        }
        Optional<User> user = userRepository.findById(motor.get().getOwnerId());
        if (user.isEmpty()) {
            throw new MotorException("Owner is not exists");
        }
        MotorResponseDto motorResponseDto = MotorResponseDto.getInstance(motor.get(), user.get());
        return motorResponseDto;
    }

    @Override
    public Motor save(MotorDto motorDto, String ownerId) {
        return motorRepository.save(Motor.getInstance(motorDto, ownerId));
    }

    @Override
    public List<Motor> getTopRating(int limit) {
        List<Motor> motors = motorRepository.findAll(Sort.by(Sort.Direction.DESC, "ratings"));
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
    public void rateMotor(String motorId, User user, RateDto rateDto) throws MotorException {
        Motor motor = findById(motorId);
        Rate rate = new Rate(user.getId(), user.getFullName(), rateDto.getRating(), rateDto.getComment());
        motor.addRating(rate);
        motor.setId(motorId);
        motorRepository.save(motor);
    }

    @Override
    public List<Motor> findByOwner(String ownerId) {
        return motorRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<RateResponseDto> getAllUserRating(String userId) {
        List<Motor> motors = findByOwner(userId);
        List<RateResponseDto> result = new ArrayList<>();
        motors.forEach(motor -> {
            result.addAll(motor.getRatings().stream()
                    .map(rate -> RateResponseDto.getInstance(rate, motor.getModel()))
                    .collect(Collectors.toList()));
        });
        return result;
    }
}
