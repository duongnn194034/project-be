package com.example.rental.Repository;

import com.example.rental.Model.RentalSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalScheduleRepository extends MongoRepository<RentalSchedule, Long> {
}
