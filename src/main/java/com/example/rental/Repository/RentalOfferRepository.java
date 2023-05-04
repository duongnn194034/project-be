package com.example.rental.Repository;

import com.example.rental.Model.RentalOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalOfferRepository extends JpaRepository<RentalOffer, Long> {
}
