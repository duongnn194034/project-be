package com.example.rental.service.impl;

import com.example.rental.common.ApiResponse;
import com.example.rental.dto.ResponseDto;
import com.example.rental.dto.user.*;
import com.example.rental.enums.Response;
import com.example.rental.enums.Role;
import com.example.rental.exception.AuthenticationFailException;
import com.example.rental.exception.CustomException;
import com.example.rental.model.AuthenticationToken;
import com.example.rental.model.User;
import com.example.rental.repository.UserRepository;
import com.example.rental.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import static com.example.rental.exception.AuthenticationFailException.USER_CREATED;
import static com.example.rental.exception.AuthenticationFailException.WRONG_PASSWORD;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final AuthenticationServiceImpl authenticationService;

    public UserServiceImpl(UserRepository userRepository, AuthenticationServiceImpl authenticationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public User getUserById(String id) {
        Optional<User> u = userRepository.findById(id);
        return u.isEmpty() ? new User() : u.get();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }

    @Override
    public ResponseDto signUp(SignupDto signupDto)  throws CustomException {
        // Check to see if the current email address has already been registered.
        if (userRepository.findByEmail(signupDto.getEmail()) != null) {
            // If the email address has been registered then throw an exception.
            throw new CustomException("User already exists");
        }
        // first encrypt the password
        String encryptedPassword = signupDto.getPassword();
        try {
            encryptedPassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
        }


        User user = new User(signupDto.getFullName(), signupDto.getEmail(), Role.USER, encryptedPassword);

        User createdUser;
        try {
            createdUser = userRepository.save(user);
            final AuthenticationToken authenticationToken = new AuthenticationToken(createdUser);
            authenticationService.saveConfirmationToken(authenticationToken);
            return new ResponseDto(Response.SUCCESS.toString(), USER_CREATED);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public SignInResponseDto signIn(SignInDto signInDto) throws CustomException {
        // first find User by email
        User user = userRepository.findByEmail(signInDto.getEmail());
        if (user == null) {
            throw new AuthenticationFailException("user not present");
        }
        try {
            // check if password is right
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                throw new AuthenticationFailException(WRONG_PASSWORD);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
            throw new CustomException(e.getMessage());
        }

        AuthenticationToken token = authenticationService.getToken(user);

        if (token == null) {
            throw new CustomException("token not present");
        }

        return new SignInResponseDto ("success", token.getToken());
    }

    @Override
    public ApiResponse changePassword(User user, ChangePasswordDto changePasswordDto) throws NoSuchAlgorithmException {
        if (user == null) {
            return new ApiResponse(false, "Error while authenticating user!");
        }
        if (!hashPassword(user.getPassword()).equals(changePasswordDto.getPassword())) {
            return new ApiResponse(false, "Wrong password!");
        }
        user.setPassword(changePasswordDto.getNewPassword());
        userRepository.save(user);
        return new ApiResponse(true, "Change password successfully!");
    }

    @Override
    public ApiResponse updateUser(String id, UserUpdateDto userUpdateDto) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return new ApiResponse(false, "User is not exist");
        }
        User user1 = user.get();
        user1.setId(id);
        user1.setEmail(userUpdateDto.getEmail());
        user1.setPhoneNumber(userUpdateDto.getPhoneNumber() != null ? userUpdateDto.getPhoneNumber() : user1.getPhoneNumber());
        user1.setRole(userUpdateDto.getRole());
        userRepository.save(user1);
        return new ApiResponse(true, "User has been updated");
    }
}
