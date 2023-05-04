package com.example.rental.Controller;

import com.example.rental.Common.ApiResponse;
import com.example.rental.Dto.Vehicle.MotorDto;
import com.example.rental.Exception.AuthenticationFailException;
import com.example.rental.Model.Motor;
import com.example.rental.Model.User;
import com.example.rental.Service.AuthenticationService;
import com.example.rental.Service.MotorService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/add")
    public ResponseEntity<Motor> addMotor(@RequestBody MotorDto motorDto,
                                                @RequestHeader("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Motor motor = motorService.save(motorDto, user);
        return new ResponseEntity<>(motor, HttpStatus.CREATED);
    }
}
