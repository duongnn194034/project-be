package com.example.rental.repository.motor;

import com.example.rental.model.Motor;
import com.example.rental.model.User;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotorRepository extends MongoRepository<Motor, String> {
    GeoResults<Motor> findByLocationNear(Point point, Distance distance);
    List<Motor> findByOwner(User owner);
}
