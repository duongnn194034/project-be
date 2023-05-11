package com.example.rental.controller;

import com.example.rental.common.ApiResponse;
import com.example.rental.dto.vehicle.MotorDto;
import com.example.rental.exception.AuthenticationFailException;
import com.example.rental.exception.MotorException;
import com.example.rental.model.Motor;
import com.example.rental.model.User;
import com.example.rental.service.AuthenticationService;
import com.example.rental.service.MotorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.geo.GeoResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/motor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MotorController {
    @Autowired
    MotorService motorService;
    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/{id}")
    public ResponseEntity<Motor> getMotor(@PathVariable("id") String id) {
        try {
            Motor motor = motorService.getById(id);
            return new ResponseEntity<>(motor, HttpStatus.OK);
        } catch (MotorException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Motor> addMotor(@RequestBody MotorDto motorDto,
                                                @RequestHeader("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Motor motorRes = motorService.save(motorDto, user);
        return new ResponseEntity<>(motorRes, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<Page<Motor>> getByRating() {
        Page<Motor> motors = motorService.getTopRating();
        return new ResponseEntity<>(motors, HttpStatus.OK);
    }

    @GetMapping("/loc")
    public ResponseEntity<GeoResults<Motor>> getGeoNear(@RequestParam("lng") double lng, @RequestParam("lat") double lat) {
        GeoResults<Motor> motorGeoResults = motorService.findByLocNear(lng, lat);
        return new ResponseEntity<>(motorGeoResults, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ApiResponse rateMotor(@PathVariable("id") String id, @RequestHeader("token") String token, @RequestParam("rating") double rate) {
        try {
            authenticationService.authenticate(token);
            double rating = motorService.rateMotor(id, rate);
            return new ApiResponse(true, String.valueOf(rating));
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse(false, e.getMessage());
        }
    }



}
