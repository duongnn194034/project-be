package com.example.rental.service.offer;

import com.example.rental.dto.rental.OfferDto;
import com.example.rental.exception.OfferException;
import com.example.rental.model.Motor;
import com.example.rental.model.Offer;
import com.example.rental.model.User;
import com.example.rental.repository.motor.MotorRepository;
import com.example.rental.repository.offer.OfferRepository;
import com.example.rental.repository.offer.OfferRepositoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    OfferRepositoryUtil rentalOfferRepositoryUtil;
    @Autowired
    MotorRepository motorRepository;

    @Override
    public Offer save(User user, OfferDto offerDto) {
        Date startDate = new Date(offerDto.getStart());
        Date endDate = new Date(offerDto.getEnd());
        List<Offer> list = rentalOfferRepositoryUtil.findByDateBetween(startDate, endDate);
        if (!list.isEmpty()) {
            throw new OfferException("Vehicle is busy this time.");
        }
        Optional<Motor> motor = motorRepository.findById(offerDto.getId());
        if (motor.isEmpty()) {
            throw new OfferException("Motor id is not found.");
        }
        Offer offer = new Offer(motor.get(),startDate, endDate);
        offer.setUser(user);
        return offerRepository.save(offer);
    }

    @Override
    public List<Offer> getOffer(String motorId) {
        Optional<Motor> motor = motorRepository.findById(motorId);
        return motor.map(value -> offerRepository.findAllByVehicle(value)).orElse(Collections.emptyList());
    }
}
