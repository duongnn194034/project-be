package com.example.rental.service.user;

import com.example.rental.common.ApiResponse;
import com.example.rental.dto.ResponseDto;
import com.example.rental.dto.user.*;
import com.example.rental.exception.CustomException;
import com.example.rental.model.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    List<User> findAll();

    User getUserById(String id);

    ResponseDto signUp(SignupDto signupDto)  throws CustomException;

    SignInResponseDto signIn(SignInDto signInDto) throws CustomException;

    ApiResponse changePassword(User user, ChangePasswordDto changePasswordDto) throws NoSuchAlgorithmException;

    ApiResponse updateUser(String token, UserUpdateDto userUpdateDto);

    ApiResponse updateUser(String token, Address address);

    ApiResponse updateUser(String token, Bank bank);

    ApiResponse updateUser(String token, License license);

    ApiResponse updateUser(String token, IdCard idCard);
}
