package com.example.rental.Service;

import com.example.rental.Common.ApiResponse;
import com.example.rental.Dto.ResponseDto;
import com.example.rental.Dto.User.*;
import com.example.rental.Exception.CustomException;
import com.example.rental.Model.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    List<User> findAll();

    ResponseDto signUp(SignupDto signupDto)  throws CustomException;

    SignInResponseDto signIn(SignInDto signInDto) throws CustomException;

    ApiResponse changePassword(User user, ChangePasswordDto changePasswordDto) throws NoSuchAlgorithmException;

    ApiResponse updateUser(String id, UserUpdateDto userUpdateDto);
}
