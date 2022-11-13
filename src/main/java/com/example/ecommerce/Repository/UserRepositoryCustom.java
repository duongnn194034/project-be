package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.User;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findByEmail(String email);
}
