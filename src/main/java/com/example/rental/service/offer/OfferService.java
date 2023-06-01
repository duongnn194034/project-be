package com.example.rental.service.offer;

import com.example.rental.dto.rental.OfferDto;
import com.example.rental.model.Offer;
import com.example.rental.model.User;

import java.util.List;

public interface OfferService {
    Offer save(User user, OfferDto offerDto);

    List<Offer> getOffer(String motorId);

    List<String> test(long start, long end);
}
