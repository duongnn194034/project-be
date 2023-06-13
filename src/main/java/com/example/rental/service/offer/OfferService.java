package com.example.rental.service.offer;

import com.example.rental.dto.rental.OfferDto;
import com.example.rental.model.Offer;
import com.example.rental.model.User;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import java.util.List;

public interface OfferService {

    Offer save(User user, OfferDto offerDto, String sessionId);

    List<Offer> getOffer(String motorId);

    List<String> test(long start, long end);

    Session createSession(OfferDto offerDto) throws StripeException;
}
