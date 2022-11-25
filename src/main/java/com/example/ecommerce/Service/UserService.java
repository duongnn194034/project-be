package com.example.ecommerce.Service;

import com.example.ecommerce.Model.User;
import com.example.ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return (User) this.userRepository.save(user);
    }


    public boolean checkLogin(String email, String password) {
        User u = userRepository.findByEmail(email);
        if (u != null && u.getPassword().equals(String.valueOf(password.hashCode()))) {
            return true;
        } else {
            return false;
        }
    }



}
