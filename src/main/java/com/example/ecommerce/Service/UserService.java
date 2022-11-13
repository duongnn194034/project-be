package com.example.ecommerce.Service;

import com.example.ecommerce.Model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);



    User save(User user);
    boolean checkLogin(String email, String password);
}
