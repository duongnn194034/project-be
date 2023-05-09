package com.example.rental.Controller;

import com.example.rental.Dto.Rental.OfferDto;
import com.example.rental.Exception.AuthenticationFailException;
import com.example.rental.Model.RentalOffer;
import com.example.rental.Service.AuthenticationService;
import com.example.rental.Service.RentalOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.geo.GeoResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/motor/loc")
    public ResponseEntity<GeoResults<RentalOffer>> getMotorsByLoc(@RequestParam("lng") double lng, @RequestParam("lat") double lat) {
        GeoResults<RentalOffer> res = rentalOfferService.findMotorByLoc(lng, lat);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
