package com.example.ecommerce.Service;

import com.example.ecommerce.Common.ApiResponse;
import com.example.ecommerce.Dto.ResponseDto;
import com.example.ecommerce.Dto.User.SignInDto;
import com.example.ecommerce.Dto.User.SignInResponseDto;
import com.example.ecommerce.Dto.User.SignupDto;
import com.example.ecommerce.Enums.Response;
import com.example.ecommerce.Enums.Role;
import com.example.ecommerce.Exception.AuthenticationFailException;
import com.example.ecommerce.Exception.CustomException;
import com.example.ecommerce.Model.AuthenticationToken;
import com.example.ecommerce.Model.User;
import com.example.ecommerce.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import static com.example.ecommerce.Exception.AuthenticationFailException.USER_CREATED;
import static com.example.ecommerce.Exception.AuthenticationFailException.WRONG_PASSWORD;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final AuthenticationService authenticationService;

    public UserService(UserRepository userRepository, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public User getUserById(long id) {
        Optional<User> u = userRepository.findById(id);
        return u.isEmpty() ? new User() : u.get();
    }

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

    public SignInResponseDto signIn(SignInDto signInDto) throws CustomException {
        // first find User by email
        User user = userRepository.findByEmail(signInDto.getEmail());
        if(user == null) {
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

    public List<User> findAll() {
        return userRepository.findAll();
    }

    protected String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }

    public ApiResponse changePassword(User user, String password, String newPassword) throws NoSuchAlgorithmException {
        if (user == null) {
            return new ApiResponse(false, "Error while authenticating user!");
        }
        if (!hashPassword(user.getPassword()).equals(password)) {
            return new ApiResponse(false, "Wrong password!");
        }
        user.setPassword(newPassword);
        userRepository.save(user);
        return new ApiResponse(true, "Change password successfully!");
    }
}
