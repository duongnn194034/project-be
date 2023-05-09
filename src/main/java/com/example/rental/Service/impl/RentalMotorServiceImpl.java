package com.example.rental.Service.impl;

import com.example.rental.Dto.Rental.OfferDto;
import com.example.rental.Exception.RentalOfferException;
import com.example.rental.Model.Motor;
import com.example.rental.Model.RentalOffer;
import com.example.rental.Repository.MotorRepository;
import com.example.rental.Repository.RentalOfferRepository;
import com.example.rental.Service.RentalMotorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RentalMotorServiceImpl implements RentalMotorService {
    @Autowired
    RentalOfferRepository rentalOfferRepository;
    @Autowired
    MotorRepository motorRepository;

    private final int PAGE_LIMIT = 10;
    private final double MAX_DIST = 10.0;

    @Override
    public RentalOffer findById(String id) {
        Optional<RentalOffer> offer = rentalOfferRepository.findById(id);
        if (offer.isEmpty()) {
            throw new RentalOfferException("Id is not available");
        }
        return offer.get();
    }

    @Override
    public RentalOffer createMotorOffer(OfferDto offerDto, String motorId) {
        Point location = new Point(offerDto.getLng(), offerDto.getLat());
        Optional<Motor> motor = motorRepository.findById(motorId);
        if (motor.isEmpty()) {
            throw new RentalOfferException("Motor Id is not exist.");
        }
        RentalOffer rentalOffer = new RentalOffer(motor.get(), offerDto.getPrice(), offerDto.getNote(),
                location, new Date(offerDto.getStart()), new Date(offerDto.getEnd()));
        return rentalOfferRepository.save(rentalOffer);
    }

    @Override
    public GeoResults<RentalOffer> findMotorByLoc(double lng, double lat) {
        return rentalOfferRepository.findByLocationNear(new Point(lng, lat), new Distance(MAX_DIST, Metrics.KILOMETERS));
    }

    @Override
    public List<RentalOffer> getMotorByRatingDesc() {
        List<RentalOffer> rentalOfferList = rentalOfferRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));
        return rentalOfferList.subList(0, Math.min(PAGE_LIMIT, rentalOfferList.size()));
    }

    @Override
    public double rateMotor(String id, double rating) {
        Optional<RentalOffer> offer = rentalOfferRepository.findById(id);
        if (offer.isEmpty()) {
            throw new RentalOfferException("Id is not available");
        }
        RentalOffer clone = offer.get();
        double newRating = (clone.getRating() * clone.getRatingTotal() + rating) / (clone.getRatingTotal() + 1);
        clone.setRating(newRating);
        clone.increaseRatingTotal();
        clone.setId(id);
        rentalOfferRepository.save(clone);
        return newRating;
    }
}
