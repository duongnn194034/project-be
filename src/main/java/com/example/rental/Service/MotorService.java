package com.example.rental.Service;

import com.example.rental.Dto.Vehicle.MotorDto;
import com.example.rental.Model.Motor;
import com.example.rental.Model.User;

public interface MotorService {
    Motor save(MotorDto motorDto, User owner);
}
