package com.example.rental.Repository;

import com.example.rental.Model.RentalOffer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalOfferRepository extends MongoRepository<RentalOffer, Long> {
}
