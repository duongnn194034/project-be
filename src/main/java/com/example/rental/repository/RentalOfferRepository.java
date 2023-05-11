package com.example.rental.repository;

import com.example.rental.model.RentalOffer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalOfferRepository extends MongoRepository<RentalOffer, String> {
}
