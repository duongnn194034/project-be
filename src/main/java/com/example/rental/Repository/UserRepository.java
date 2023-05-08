package com.example.rental.Repository;

import com.example.rental.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Long> {
    User findByEmail(String email);

    List<User> findAll();
}
