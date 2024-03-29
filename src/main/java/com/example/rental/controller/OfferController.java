package com.example.rental.controller;

import com.example.rental.common.ApiResponse;
import com.example.rental.dto.offer.OfferDto;
import com.example.rental.dto.offer.OfferResponseDto;
import com.example.rental.dto.offer.StatusDto;
import com.example.rental.dto.offer.StripeResponse;
import com.example.rental.dto.user.UserPriceDto;
import com.example.rental.exception.AuthenticationFailException;
import com.example.rental.exception.OfferException;
import com.example.rental.model.offer.Offer;
import com.example.rental.model.user.User;
import com.example.rental.service.token.AuthenticationService;
import com.example.rental.service.offer.OfferService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
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

    @PostMapping("/add")
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
    public ResponseEntity<OfferResponseDto> getOfferById(@RequestHeader("token") String token, @PathVariable("id") String id) throws OfferException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        OfferResponseDto offerResponseDto = offerService.getOfferById(id, user.getId());
        return new ResponseEntity<>(offerResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateOfferStatus(@RequestHeader("token") String token, @PathVariable("id") String id, @RequestBody StatusDto statusDto) {
        try {
            authenticationService.authenticate(token);
            User user = authenticationService.getUser(token);
            OfferResponseDto offerResponseDto = offerService.changeOfferStatus(id, user.getId(), statusDto.getStatus());
            return new ResponseEntity<>(offerResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/motor/{id}")
    public ResponseEntity<List<OfferResponseDto>> getOfferByMotor(@PathVariable("id") String id) throws OfferException {
        List<OfferResponseDto> offers = offerService.getOffer(id);
        if (offers.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<OfferResponseDto>> getListOffer(@RequestHeader("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        List<OfferResponseDto> offers = offerService.getOfferByUserId(user.getId());
        if (offers.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/listPrice")
    public ResponseEntity<List<UserPriceDto>> getListVehicleOffer(@RequestHeader("token") String token) throws AuthenticationFailException {
        authenticationService.authenticateAdmin(token);
        List<UserPriceDto> offers = offerService.statistic();
        if (offers.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }
}
