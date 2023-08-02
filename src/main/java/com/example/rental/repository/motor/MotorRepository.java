package com.example.rental.repository.motor;

import com.example.rental.model.motor.Motor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotorRepository extends MongoRepository<Motor, String> {
    List<Motor> findByOwnerId(String ownerId);

    List<Motor> findByOwnerId(String ownerId, Pageable pageable);
}
