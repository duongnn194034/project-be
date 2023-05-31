package com.example.rental.controller;

import com.example.rental.common.ApiResponse;
import com.example.rental.dto.rental.OfferDto;
import com.example.rental.exception.OfferException;
import com.example.rental.model.Offer;
import com.example.rental.model.User;
import com.example.rental.service.token.AuthenticationService;
import com.example.rental.service.offer.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/offer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class OfferController {
    @Autowired
    OfferService offerService;
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<Object> saveOffer(@RequestHeader("token") String token, @RequestBody OfferDto offerDto) {
        try {
            authenticationService.authenticate(token);
            User user = authenticationService.getUser(token);
            Offer offer = offerService.save(user, offerDto);
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

    @GetMapping("")
    public ResponseEntity<List<Offer>> findOfferAvailable(@RequestParam(name = "start", defaultValue = "-1") long start,
                                                          @RequestParam(name = "end", defaultValue = "-1") long end) {
        return null;
    }
}
