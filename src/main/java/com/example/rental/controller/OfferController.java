package com.example.rental.controller;

import com.example.rental.common.ApiResponse;
import com.example.rental.dto.rental.OfferDto;
import com.example.rental.dto.rental.StripeResponse;
import com.example.rental.exception.OfferException;
import com.example.rental.model.Offer;
import com.example.rental.model.User;
import com.example.rental.service.token.AuthenticationService;
import com.example.rental.service.offer.OfferService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("/offer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class OfferController {
    @Autowired
    OfferService offerService;
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody OfferDto offerDto, @RequestHeader("token") String token) throws StripeException {
        // create the stripe session
        Session session = offerService.createSession(offerDto);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<Object> saveOffer(@RequestHeader("token") String token, @RequestBody OfferDto offerDto) {
        try {
            authenticationService.authenticate(token);
            User user = authenticationService.getUser(token);
            Offer offer = offerService.save(user, offerDto, offerDto.getSessionId());
            return new ResponseEntity<>(offer, HttpStatus.CREATED);
        } catch (OfferException e) {
            return  new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Offer>> getOffer(@PathVariable("id") String id) throws OfferException {
        List<Offer> offers = offerService.getOffer(id);
        if (offers.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<Object> test(@RequestParam("start") long start, @RequestParam("end") long end) {
        return new ResponseEntity<>(offerService.test(start, end), HttpStatus.OK);
    }
}
