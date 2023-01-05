package com.example.ecommerce.Service;

import com.example.ecommerce.Dto.ResponseDto;
import com.example.ecommerce.Dto.User.SignInDto;
import com.example.ecommerce.Dto.User.SignInResponseDto;
import com.example.ecommerce.Dto.User.SignupDto;
import com.example.ecommerce.Enums.Response;
import com.example.ecommerce.Enums.Role;
import com.example.ecommerce.Exception.CustomException;
import com.example.ecommerce.Model.User;
import com.example.ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }


    public SignInResponseDto signIn(SignInDto signInDto) throws NoSuchAlgorithmException {
        User u = userRepository.findByEmail(signInDto.getEmail());
        if (u != null && u.getPassword().equals(hashPassword(String.valueOf(signInDto.getPassword())))) {
            return new SignInResponseDto("success");
        } else {
            return new SignInResponseDto("failed");
        }
    }


    public User getUserById(long id) {
        Optional<User> u = userRepository.findById(id);
        return u.isEmpty() ? new User() : u.get();
    }

    public ResponseDto signUp(SignupDto signupDto) throws Exception {
        if (userRepository.findByEmail(signupDto.getEmail()) != null) {
            // If the email address has been registered then throw an exception.
            throw new CustomException("User already exists");
        }
        // first encrypt the password
        String encryptedPassword = String.valueOf(hashPassword(signupDto.getPassword()));


        User user = new User(signupDto.getFullName(), signupDto.getEmail(), Role.USER, encryptedPassword );
        try {
            // save the User
            userRepository.save(user);
            // success in creating
            return new ResponseDto("SUCCESS", "User created");
        } catch (Exception e) {
            // handle signup error
            throw new CustomException(e.getMessage());
        }
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
}
