package com.example.rental.controller;

import com.example.rental.common.ApiResponse;
import com.example.rental.dto.rate.RateDto;
import com.example.rental.dto.vehicle.MotorDto;
import com.example.rental.dto.vehicle.MotorResponseDto;
import com.example.rental.exception.AuthenticationFailException;
import com.example.rental.exception.MotorException;
import com.example.rental.model.Motor;
import com.example.rental.model.User;
import com.example.rental.service.token.AuthenticationService;
import com.example.rental.service.motor.MotorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/motor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MotorController {
    @Autowired
    MotorService motorService;
    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/{id}")
    public ResponseEntity<MotorResponseDto> getMotor(@PathVariable("id") String id) {
        try {
            MotorResponseDto motor = motorService.getById(id);
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
        Motor motorRes = motorService.save(motorDto, user.getId());
        return new ResponseEntity<>(motorRes, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> editMotor(@RequestBody MotorDto motorDto, @PathVariable("id") String motorId,
                                           @RequestHeader("token") String token) throws AuthenticationFailException {
        try {
            authenticationService.authenticate(token);
            User user = authenticationService.getUser(token);
            Motor motor = motorService.patch(motorDto, motorId, user.getId());
            return new ResponseEntity<>(motor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteMotor(@PathVariable("id") String motorId, @RequestHeader("token") String token)
            throws AuthenticationFailException {
        try {
            authenticationService.authenticate(token);
            User user = authenticationService.getUser(token);
            motorService.delete(motorId, user.getId());
            return new ApiResponse(true, "Delete successfully");
        } catch (Exception e) {
            return new ApiResponse(false, e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Motor>> getByRating(@RequestParam(name = "limit", defaultValue = "100") int limit) {
        List<Motor> motors = motorService.getTopRating(limit);
        return new ResponseEntity<>(motors, HttpStatus.OK);
    }

    @GetMapping("/loc")
    public ResponseEntity<GeoResults<Motor>> getGeoNear(@RequestParam("lat") double lat, @RequestParam("lng") double lng,
                                                        @RequestParam(name = "start", required = false, defaultValue = "-1") long start,
                                                        @RequestParam(name = "end", required = false, defaultValue = "-1") long end) {
        GeoResults<Motor> motorGeoResults = motorService.findByLocNear(lat, lng, start, end);
        return new ResponseEntity<>(motorGeoResults, HttpStatus.OK);
    }

    @PatchMapping("/rate/{id}")
    public ApiResponse rateMotor(@PathVariable("id") String motorId, @RequestHeader("token") String token, @RequestBody RateDto rateDto) {
        try {
            authenticationService.authenticate(token);
            User user = authenticationService.getUser(token);
            motorService.rateMotor(motorId, user, rateDto);
            return new ApiResponse(true, "Rated.");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse(false, e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getByUser(@RequestHeader("token") String token,
                                            @RequestParam(name = "limit", defaultValue = "-1") int limit) {
        try {
            User user = authenticationService.getUser(token);
            List<Motor> motors = motorService.findByOwner(user.getId(), limit);
            if (motors.isEmpty()) {
                return new ResponseEntity<>(motors, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(motors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("You must log in first.", HttpStatus.BAD_REQUEST);
        }
    }

}
