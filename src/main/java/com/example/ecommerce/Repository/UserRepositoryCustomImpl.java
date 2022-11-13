package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }
}
