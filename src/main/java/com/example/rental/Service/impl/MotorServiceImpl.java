package com.example.rental.Service.impl;

import com.example.rental.Dto.Vehicle.MotorDto;
import com.example.rental.Model.Motor;
import com.example.rental.Model.User;
import com.example.rental.Repository.MotorRepository;
import com.example.rental.Service.MotorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotorServiceImpl implements MotorService {
    @Autowired
    MotorRepository motorRepository;

    @Override
    public Motor save(MotorDto motorDto, User owner) {
        Motor motor = new Motor(motorDto.getProduction(), motorDto.getModel(), motorDto.getCylinder(), motorDto.getType(),
                motorDto.getImageUrl(), motorDto.getSummary(), owner);
        return motorRepository.save(motor);
    }
}
