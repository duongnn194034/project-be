package com.example.rental.Repository;

import com.example.rental.Model.RentalOffer;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalOfferRepository extends MongoRepository<RentalOffer, String> {
    GeoResults<RentalOffer> findByLocationNear(Point location, Distance distance);
}
