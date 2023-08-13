package com.example.rental.repository.user;

import com.example.rental.model.user.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);

    List<User> findAll();

    List<User> findAll(Sort sort);
}
