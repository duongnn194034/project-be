package com.example.rental.controller;

import com.example.rental.service.AuthenticationService;
import com.example.rental.service.RentalMotorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/rental")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class RentalOfferController {
    @Autowired
    RentalMotorService rentalMotorService;
    @Autowired
    AuthenticationService authenticationService;
}
