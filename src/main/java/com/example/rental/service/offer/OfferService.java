package com.example.rental.service.offer;

import com.example.rental.dto.offer.OfferDto;
import com.example.rental.dto.offer.OfferResponseDto;
import com.example.rental.enums.Status;
import com.example.rental.model.Offer;
import com.example.rental.model.User;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import java.util.List;

public interface OfferService {

    Offer save(User user, OfferDto offerDto, String sessionId);

    List<Offer> getOffer(String motorId);
    OfferResponseDto changeOfferStatus(String offerId, String userId, Status status);
    OfferResponseDto getOfferById(String id, String userId);

    /**
     * get offers which user offer
     * @param userId offering userId
     * @return list offer
     */
    List<OfferResponseDto> getOfferByUserId(String userId);

    /**
     * get offers which user's vehicle are offered
     * @param userId offered userId
     * @return list offer
     */
    List<OfferResponseDto> getOfferVehicleByUserId(String userId);

    Session createSession(OfferDto offerDto) throws StripeException;
}
