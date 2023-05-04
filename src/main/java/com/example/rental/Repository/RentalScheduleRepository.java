package com.example.rental.Repository;

import com.example.rental.Model.RentalSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalScheduleRepository extends JpaRepository<RentalSchedule, Long> {
}
