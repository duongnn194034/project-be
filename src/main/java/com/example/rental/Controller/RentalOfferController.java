package com.example.rental.Controller;

import com.example.rental.Dto.Rental.OfferDto;
import com.example.rental.Exception.AuthenticationFailException;
import com.example.rental.Model.RentalOffer;
import com.example.rental.Service.AuthenticationService;
import com.example.rental.Service.RentalOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/rental")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class RentalOfferController {
    @Autowired
    RentalOfferService rentalOfferService;
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/motor/{id}")
    public ResponseEntity<RentalOffer> createMotorRental(@PathVariable("id") String motorId, @RequestHeader("token") String token,
                                                         @RequestBody OfferDto offerDto) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        RentalOffer rentalOffer = rentalOfferService.createMotorOffer(offerDto, motorId);
        return new ResponseEntity<>(rentalOffer, HttpStatus.CREATED);
    }
}
