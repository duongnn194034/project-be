package com.example.ecommerce.Service;

import com.example.ecommerce.Dto.ResponseDto;
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
import java.security.NoSuchAlgorithmException;
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


    public boolean checkLogin(String email, String password) {
        User u = userRepository.findByEmail(email);
        if (u != null && u.getPassword().equals(String.valueOf(password.hashCode()))) {
            return true;
        } else {
            return false;
        }
    }


    public User getUserById(Long id) {
        Optional<User> u = userRepository.findById(id);
        return u.isEmpty() ? new User() : u.get();
    }

    public ResponseDto signUp(SignupDto signupDto) throws Exception {
        if (userRepository.findByEmail(signupDto.getEmail()) != null) {
            // If the email address has been registered then throw an exception.
            throw new CustomException("User already exists");
        }
        // first encrypt the password
        String encryptedPassword = String.valueOf(signupDto.getPassword().hashCode());


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
}
