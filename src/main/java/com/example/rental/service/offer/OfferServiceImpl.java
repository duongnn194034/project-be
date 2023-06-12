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
    OfferRepositoryUtil offerRepositoryUtil;
    @Autowired
    MotorRepository motorRepository;

    @Override
    public Offer save(User user, OfferDto offerDto) {
        Optional<Motor> motor = motorRepository.findById(offerDto.getId());
        if (motor.isEmpty()) {
            throw new OfferException("Motor id is not found.");
        }
        long span = offerDto.getEnd() - offerDto.getStart();
        if (span < motor.get().toMinDur() || (motor.get().toMaxDur() > 0 && span > motor.get().toMaxDur())) {
            throw new OfferException("Duration length is not valid.");
        }
        Date startDate = new Date(offerDto.getStart());
        Date endDate = new Date(offerDto.getEnd());
        List<Offer> list = offerRepositoryUtil.findByVehicleAndDateBetween(motor.get(), startDate, endDate);
        if (!list.isEmpty()) {
            throw new OfferException("Vehicle is busy this time.");
        }
        Offer offer = new Offer(motor.get().getId(),startDate, endDate);
        offer.setUserId(user.getId());
        return offerRepository.save(offer);
    }

    @Override
    public List<Offer> getOffer(String motorId) {
        Optional<Motor> motor = motorRepository.findById(motorId);
        return motor.map(value -> offerRepository.findAllByVehicle(value)).orElse(Collections.emptyList());
    }

    @Override
    public List<String> test(long start, long end) {
        return offerRepositoryUtil.findIdsByDateBetween(new Date(start), new Date(end));
    }
}
