package com.example.rental.Service.impl;

import com.example.rental.Dto.Rental.OfferDto;
import com.example.rental.Exception.RentalOfferException;
import com.example.rental.Model.Motor;
import com.example.rental.Model.RentalOffer;
import com.example.rental.Repository.MotorRepository;
import com.example.rental.Repository.RentalOfferRepository;
import com.example.rental.Service.RentalOfferService;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RentalOfferServiceImpl implements RentalOfferService {
    @Autowired
    RentalOfferRepository rentalOfferRepository;
    @Autowired
    MotorRepository motorRepository;

    @Override
    public RentalOffer createMotorOffer(OfferDto offerDto, String motorId) {
        Point location = new Point(new Position(offerDto.getLng(), offerDto.getLat()));
        Optional<Motor> motor = motorRepository.findById(motorId);
        if (motor.isEmpty()) {
            throw new RentalOfferException("Motor Id is not exist.");
        }
        RentalOffer rentalOffer = new RentalOffer(motor.get(), offerDto.getPrice(), offerDto.getNote(),
                location, new Date(offerDto.getStart()), new Date(offerDto.getEnd()));
        return rentalOfferRepository.save(rentalOffer);
    }
}
