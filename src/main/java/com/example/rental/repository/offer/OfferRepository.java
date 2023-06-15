package com.example.rental.repository.offer;

import com.example.rental.model.Offer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends MongoRepository<Offer, String> {
    List<Offer> findAllByVehicleId(String vehicleId);
}
