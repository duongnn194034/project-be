package com.example.ecommerce.Service;

import com.example.ecommerce.Common.ApiResponse;
import com.example.ecommerce.Dto.ResponseDto;
import com.example.ecommerce.Dto.User.SignInDto;
import com.example.ecommerce.Dto.User.SignInResponseDto;
import com.example.ecommerce.Dto.User.SignupDto;
import com.example.ecommerce.Dto.User.UserUpdateDto;
import com.example.ecommerce.Exception.CustomException;
import com.example.ecommerce.Model.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    ResponseDto signUp(SignupDto signupDto)  throws CustomException;

    SignInResponseDto signIn(SignInDto signInDto) throws CustomException;

    ApiResponse changePassword(User user, String password, String newPassword) throws NoSuchAlgorithmException;

    ApiResponse updateUser(long id, UserUpdateDto userUpdateDto);
}
