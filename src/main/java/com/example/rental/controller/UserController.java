package com.example.rental.controller;


import com.example.rental.common.ApiResponse;
import com.example.rental.dto.ResponseDto;
import com.example.rental.dto.user.*;
import com.example.rental.exception.AuthenticationFailException;
import com.example.rental.exception.CustomException;
import com.example.rental.model.*;
import com.example.rental.service.token.AuthenticationService;
import com.example.rental.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<User> findAllUser(@RequestHeader("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userService.findAll();
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

    @PostMapping("/changePassword")
    public ApiResponse changePassword(@RequestHeader("token") String token,
                                      @RequestBody ChangePasswordDto changePasswordDto)
            throws AuthenticationFailException, NoSuchAlgorithmException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        return userService.changePassword(user, changePasswordDto);
    }

    @PatchMapping("/update")
    public ApiResponse updateUser(@RequestHeader("token") String token, @RequestBody UserUpdateDto userUpdateDto) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userService.updateUser(token, userUpdateDto);
    }

    @PatchMapping("/update/address")
    public ApiResponse updateUser(@RequestHeader("token") String token, @RequestBody Address address) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userService.updateUser(token, address);
    }

    @PatchMapping("/update/bank")
    public ApiResponse updateUser(@RequestHeader("token") String token, @RequestBody Bank bank) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userService.updateUser(token, bank);
    }

    @PatchMapping("/update/card")
    public ApiResponse updateUser(@RequestHeader("token") String token, @RequestBody IdCard idCard) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userService.updateUser(token, idCard);
    }

    @PatchMapping("/update/license")
    public ApiResponse updateUser(@RequestHeader("token") String token, @RequestBody License license) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userService.updateUser(token, license);
    }

}
