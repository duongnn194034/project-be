package com.example.rental.Service;

import com.example.rental.Dto.Rental.OfferDto;
import com.example.rental.Model.RentalOffer;

public interface RentalOfferService {
    RentalOffer createMotorOffer(OfferDto offerDto, String motorId);
}
