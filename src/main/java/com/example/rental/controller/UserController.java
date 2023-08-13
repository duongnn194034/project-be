package com.example.rental.controller;


import com.example.rental.common.ApiResponse;
import com.example.rental.dto.ResponseDto;
import com.example.rental.dto.rate.RateResponseDto;
import com.example.rental.dto.user.*;
import com.example.rental.enums.Role;
import com.example.rental.exception.AuthenticationFailException;
import com.example.rental.exception.CustomException;
import com.example.rental.model.user.*;
import com.example.rental.service.motor.MotorService;
import com.example.rental.service.token.AuthenticationService;
import com.example.rental.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserService userService;
    @Lazy
    @Autowired
    MotorService motorService;

    @GetMapping("/all")
    public List<User> findAllUser(@RequestHeader("token") String token,
                                  @RequestParam(name = "query", required = false, defaultValue = "") String query) throws AuthenticationFailException {
        authenticationService.authenticateAdmin(token);
        switch (query) {
            case "verify":
                return userService.findUserToVerify();
            default:
                return userService.findAll();
        }

    }

    @GetMapping("/get")
    public User getCurrentUser(@RequestHeader("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return authenticationService.getUser(token);
    }

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable("id") String userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/signUp")
    public ResponseDto signUp(@RequestBody SignupDto signupDto) throws Exception {
        return userService.signUp(signupDto);
    }

    @PostMapping("/signIn")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) throws CustomException, NoSuchAlgorithmException {
        return userService.signIn(signInDto);
    }

    @PutMapping("/changePassword")
    public ApiResponse changePassword(@RequestHeader("token") String token,
                                      @RequestBody ChangePasswordDto changePasswordDto)
            throws AuthenticationFailException, NoSuchAlgorithmException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        return userService.changePassword(user, changePasswordDto);
    }

    @PutMapping("/update")
    public ApiResponse updateUser(@RequestHeader("token") String token, @RequestBody UserUpdateDto userUpdateDto) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userService.updateUser(token, userUpdateDto);
    }

    @PutMapping("/update/address")
    public ApiResponse updateUser(@RequestHeader("token") String token, @RequestBody Address address) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userService.updateUser(token, address);
    }

    @PutMapping("/update/bank")
    public ApiResponse updateUser(@RequestHeader("token") String token, @RequestBody Bank bank) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userService.updateUser(token, bank);
    }

    @PutMapping("/update/card")
    public ApiResponse updateUser(@RequestHeader("token") String token, @RequestBody IdCard idCard) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userService.updateUser(token, idCard);
    }

    @PutMapping("/update/license")
    public ApiResponse updateUser(@RequestHeader("token") String token, @RequestBody License license) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userService.updateUser(token, license);
    }

    @GetMapping("/get/{id}/rating")
    public ResponseEntity<List<RateResponseDto>> getUserRating (@PathVariable("id") String userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
        List<RateResponseDto> rates = motorService.getAllUserRating(user);
        if (rates.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rates, HttpStatus.OK);
    }

    @PostMapping("/admin/{id}/notify")
    public ResponseEntity<ApiResponse> pushNotify(@PathVariable("id") String id, @RequestHeader("token") String token,
                                  @RequestBody NotifyDto notfyDto) throws AuthenticationFailException {
        authenticationService.authenticateAdmin(token);
        ApiResponse res = userService.addNotification(id, notfyDto.getNotify());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PatchMapping("/admin/promote")
    public ApiResponse promote(@RequestParam("user") String userId,
                               @RequestHeader("token") String token) throws AuthenticationFailException {
        authenticationService.authenticateAdmin(token);
        return userService.promote(userId, Role.ADMIN);
    }

    @PatchMapping("/admin/demote")
    public ApiResponse demote(@RequestParam("user") String userId,
                               @RequestHeader("token") String token) throws AuthenticationFailException {
        authenticationService.authenticateAdmin(token);
        return userService.promote(userId, Role.USER);
    }

    @PatchMapping("/admin/verify")
    public ApiResponse verify(@RequestParam("user") String userId,
                              @RequestHeader("token") String token,
                              @RequestBody IdCard idCard) throws AuthenticationFailException {
        authenticationService.authenticateAdmin(token);
        return userService.verifyUser(userId, idCard);
    }

    @DeleteMapping("/admin/delete")
    public ApiResponse delete(@RequestParam("user") String userId,
                              @RequestHeader("token") String token) throws AuthenticationFailException {
        authenticationService.authenticateAdmin(token);
        return userService.deleteUser(userId);
    }
}
