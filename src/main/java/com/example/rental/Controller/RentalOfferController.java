package com.example.rental.Controller;

import com.example.rental.Common.ApiResponse;
import com.example.rental.Dto.Rental.OfferDto;
import com.example.rental.Exception.AuthenticationFailException;
import com.example.rental.Exception.RentalOfferException;
import com.example.rental.Model.RentalOffer;
import com.example.rental.Service.AuthenticationService;
import com.example.rental.Service.RentalMotorService;
import org.springframework.beans.factory.annotation.Autowired;
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
    RentalMotorService rentalMotorService;
    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/motor/{id}")
    public ResponseEntity<RentalOffer> getMotorOffer(@PathVariable("id") String id) {
        RentalOffer rentalOffer = rentalMotorService.findById(id);
        return new ResponseEntity<>(rentalOffer, HttpStatus.OK);
    }

    @PostMapping("/motor")
    public ResponseEntity<RentalOffer> createMotorRental(@RequestHeader("token") String token,
                                                         @RequestBody OfferDto offerDto) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        RentalOffer rentalOffer = rentalMotorService.createMotorOffer(offerDto, offerDto.getId());
        return new ResponseEntity<>(rentalOffer, HttpStatus.CREATED);
    }

    @GetMapping("/motor/loc")
    public ResponseEntity<GeoResults<RentalOffer>> getMotorsByLoc(@RequestParam("lng") double lng, @RequestParam("lat") double lat) {
        GeoResults<RentalOffer> res = rentalMotorService.findMotorByLoc(lng, lat);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/motor")
    public ResponseEntity<List<RentalOffer>> getMotors() {
        List<RentalOffer> list = rentalMotorService.getMotorByRatingDesc();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PatchMapping("/motor/{id}")
    public ApiResponse rateMotor(@PathVariable("id") String id, @RequestParam("rating") double rating) {
        try {
            double res = rentalMotorService.rateMotor(id, rating);
            return new ApiResponse(true, String.valueOf(res));
        } catch (RentalOfferException e) {
            return new ApiResponse(false, e.getMessage());
        }
    }
}
