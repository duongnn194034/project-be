package com.example.rental.repository.motor;

import com.example.rental.model.Motor;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorRepository extends MongoRepository<Motor, String> {
    GeoResults<Motor> findByLocationNear(Point point, Distance distance);
}
