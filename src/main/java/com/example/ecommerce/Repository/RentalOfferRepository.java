package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.RentalOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalOfferRepository extends JpaRepository<RentalOffer, Long> {
}
