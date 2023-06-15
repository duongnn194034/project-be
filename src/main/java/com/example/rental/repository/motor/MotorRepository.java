package com.example.rental.repository.motor;

import com.example.rental.model.Motor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotorRepository extends MongoRepository<Motor, String> {
    List<Motor> findByOwnerId(String ownerId);
}
