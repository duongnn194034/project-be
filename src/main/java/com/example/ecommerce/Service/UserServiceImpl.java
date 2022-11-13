package com.example.ecommerce.Service;

import com.example.ecommerce.Model.User;
import com.example.ecommerce.Repository.UserRepository;
import com.example.ecommerce.Repository.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserRepositoryCustom userRepositoryCustom;

    public UserServiceImpl(UserRepository userRepository, UserRepositoryCustom userRepositoryCustom) {
        this.userRepository = userRepository;
        this.userRepositoryCustom = userRepositoryCustom;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepositoryCustom.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return (User) this.userRepository.save(user);
    }

    @Override
    public boolean checkLogin(String email, String password) {
        return true;
    }

}
