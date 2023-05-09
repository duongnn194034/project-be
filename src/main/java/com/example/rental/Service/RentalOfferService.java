package com.example.rental.Service;

import com.example.rental.Dto.Rental.OfferDto;
import com.example.rental.Model.RentalOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.geo.GeoResults;

import java.util.List;

public interface RentalOfferService {
    RentalOffer createMotorOffer(OfferDto offerDto, String motorId);

    GeoResults<RentalOffer> findMotorByLoc(double lng, double lat);
}
