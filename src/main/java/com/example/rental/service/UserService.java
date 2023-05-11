package com.example.rental.service;

import com.example.rental.common.ApiResponse;
import com.example.rental.dto.ResponseDto;
import com.example.rental.dto.user.*;
import com.example.rental.exception.CustomException;
import com.example.rental.model.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    List<User> findAll();

    ResponseDto signUp(SignupDto signupDto)  throws CustomException;

    SignInResponseDto signIn(SignInDto signInDto) throws CustomException;

    ApiResponse changePassword(User user, ChangePasswordDto changePasswordDto) throws NoSuchAlgorithmException;

    ApiResponse updateUser(String id, UserUpdateDto userUpdateDto);
}
