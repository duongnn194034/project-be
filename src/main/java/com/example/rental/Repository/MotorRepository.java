package com.example.rental.Repository;

import com.example.rental.Model.Motor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorRepository extends MongoRepository<Motor, Long> {
}
