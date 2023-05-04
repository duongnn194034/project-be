package com.example.rental.Controller;


import com.example.rental.Common.ApiResponse;
import com.example.rental.Dto.ResponseDto;
import com.example.rental.Dto.User.*;
import com.example.rental.Exception.AuthenticationFailException;
import com.example.rental.Exception.CustomException;
import com.example.rental.Model.User;
import com.example.rental.Service.AuthenticationService;
import com.example.rental.Service.UserService;
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
    public ApiResponse updateUser(@RequestParam("token") String token, @RequestBody UserUpdateDto userUpdateDto) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        long id = authenticationService.getUser(token).getId();
        return userService.updateUser(id, userUpdateDto);
    }
}
