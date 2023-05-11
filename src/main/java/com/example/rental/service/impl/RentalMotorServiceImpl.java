package com.example.rental.service.impl;

import com.example.rental.repository.MotorRepository;
import com.example.rental.repository.RentalOfferRepository;
import com.example.rental.service.RentalMotorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalMotorServiceImpl implements RentalMotorService {
    @Autowired
    RentalOfferRepository rentalOfferRepository;
    @Autowired
    MotorRepository motorRepository;
}
