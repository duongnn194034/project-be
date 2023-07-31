package com.example.rental.service.user;

import com.example.rental.common.ApiResponse;
import com.example.rental.dto.ResponseDto;
import com.example.rental.dto.user.*;
import com.example.rental.enums.Response;
import com.example.rental.enums.Role;
import com.example.rental.exception.AuthenticationFailException;
import com.example.rental.exception.CustomException;
import com.example.rental.model.*;
import com.example.rental.repository.motor.MotorRepository;
import com.example.rental.repository.user.UserRepository;
import com.example.rental.service.token.AuthenticationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    private static final String UPDATE_MESSAGE = "User has been updated";
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationServiceImpl authenticationService;

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User getUserById(String id) {
        Optional<User> u = userRepository.findById(id);
        return u.orElseGet(User::new);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter
                .printHexBinary(digest).toUpperCase();
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
    public ApiResponse updateUser(String token, UserUpdateDto userUpdateDto) {
        try {
            User user = authenticationService.getUser(token);
            AuthenticationToken authenticationToken = authenticationService.getToken(user);
            user.setEmail(userUpdateDto.getEmail() != null ? userUpdateDto.getEmail() : user.getEmail());
            user.setPhoneNumber(userUpdateDto.getPhoneNumber() != null ? userUpdateDto.getPhoneNumber() : user.getPhoneNumber());
            user.setRole(userUpdateDto.getRole() != null ? userUpdateDto.getRole() : user.getRole());
            user.setAvatarUrl(userUpdateDto.getAvatarUrl() != null ? userUpdateDto.getAvatarUrl() : user.getAvatarUrl());
            user.setRole(userUpdateDto.getRole() != null ? userUpdateDto.getRole() : user.getRole());
            user.setBiography(userUpdateDto.getBiography() != null ? userUpdateDto.getBiography() : user.getBiography());
            user.setFacebookUrl(userUpdateDto.getFbUrl() != null ? userUpdateDto.getFbUrl() : user.getFacebookUrl());
            user.setTwitterUrl(userUpdateDto.getTwitterUrl() != null ? userUpdateDto.getTwitterUrl() : user.getTwitterUrl());
            user.setZaloUrl(userUpdateDto.getZaloUrl() != null ? userUpdateDto.getZaloUrl() : user.getZaloUrl());
            userRepository.save(user);
            authenticationToken.setUser(user);
            authenticationService.saveConfirmationToken(authenticationToken);
            return new ApiResponse(true, UPDATE_MESSAGE);
        } catch (Exception e) {
            return new ApiResponse(false, e.getMessage());
        }
    }

    @Override
    public ApiResponse updateUser(String token, Address address) {
        try {
            User user = authenticationService.getUser(token);
            AuthenticationToken authenticationToken = authenticationService.getToken(user);
            user.setAddress(address);
            userRepository.save(user);
            authenticationToken.setUser(user);
            authenticationService.saveConfirmationToken(authenticationToken);
            return new ApiResponse(true, UPDATE_MESSAGE);
        } catch (Exception e) {
            return new ApiResponse(false, e.getMessage());
        }
    }

    @Override
    public ApiResponse updateUser(String token, Bank bank) {
        try {
            User user = authenticationService.getUser(token);
            AuthenticationToken authenticationToken = authenticationService.getToken(user);
            user.setBank(bank);
            userRepository.save(user);
            authenticationToken.setUser(user);
            authenticationService.saveConfirmationToken(authenticationToken);
            return new ApiResponse(true, UPDATE_MESSAGE);
        } catch (Exception e) {
            return new ApiResponse(false, e.getMessage());
        }
    }

    @Override
    public ApiResponse updateUser(String token, License license) {
        try {
            User user = authenticationService.getUser(token);
            AuthenticationToken authenticationToken = authenticationService.getToken(user);
            user.setLicense(license);
            userRepository.save(user);
            authenticationToken.setUser(user);
            authenticationService.saveConfirmationToken(authenticationToken);
            return new ApiResponse(true, UPDATE_MESSAGE);
        } catch (Exception e) {
            return new ApiResponse(false, e.getMessage());
        }
    }

    @Override
    public ApiResponse updateUser(String token, IdCard idCard) {
        try {
            User user = authenticationService.getUser(token);
            AuthenticationToken authenticationToken = authenticationService.getToken(user);
            user.setIdCard(idCard);
            userRepository.save(user);
            authenticationToken.setUser(user);
            authenticationService.saveConfirmationToken(authenticationToken);
            return new ApiResponse(true, UPDATE_MESSAGE);
        } catch (Exception e) {
            return new ApiResponse(false, e.getMessage());
        }
    }
}
